package com.freelancingpeter.workers;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import com.freelancingpeter.data.AccountsInfo;
import com.freelancingpeter.data.Data;

public class SteamLoadingThread implements Runnable, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3954553679381960734L;
	private final String key;
	private final String steamId;
	private HttpGet httpget1 = null;
	private HttpGet httpget2 = null;
	private HttpGet httpget3 = null;
	private HttpClient client = null;
	private HttpContext localContext = null;
	private String userInfo = null;
	private String gameStats = null;
	private String recentGames = null;
	protected static int recentGamesCount = 0;

	private static SteamLoadingThread thread = null;

	public static SteamLoadingThread getInstance(AccountsInfo info) {
		if (thread == null) {
			thread = new SteamLoadingThread(info.getSTEAMKEY(), info.getSTEAMID());

		}
		return thread;
	}

	public SteamLoadingThread(String key, String id) {
		this.key = key;
		this.steamId = id;
	}

	@Override
	public void run() {
		System.out.println("runSteam");
		Data.INSTANCE.clearSteam();
		Data.INSTANCE.setSteam(getUserInfo());
		Data.INSTANCE.setSteam(getAllGames());
		Data.INSTANCE.setSteam(getRecentGames());
		System.out.println("doneSteam");
		userInfo = null;
		gameStats = null;
		recentGames = null;
	}

	protected synchronized String getRecentGames() {

		if (recentGames == null) {
			httpget1 = new HttpGet("https://api.steampowered.com/IPlayerService/GetRecentlyPlayedGames/v0001/" + "?key="
					+ key + "&steamid=" + steamId + "&format=json");

			HttpResponse response;

			try {
				client = HttpClientBuilder.create().build();
				localContext = new BasicHttpContext();
				response = client.execute(httpget1, localContext);

				HttpEntity entity = response.getEntity();
				recentGames = getASCIIContentFromEntity(entity);
				setNoGames();

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return recentGames;
	}

	private void setNoGames() {
		try {
			String a = recentGames.substring(recentGames.indexOf("total_count") + 12);
			Data.INSTANCE.setRecentGames(Integer.parseInt(a.substring(1, 2)));
			if (Data.INSTANCE.getRecentGames() > 3)
				Data.INSTANCE.setRecentGames(3);
		} catch (Exception x) {
			Data.INSTANCE.setRecentGames(0);
		}
	}

	protected synchronized String getAllGames() {

		if (gameStats == null) {
			httpget2 = new HttpGet("https://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=" + key
					+ "&steamid=" + steamId + "&include_appinfo=1&format=json&include_played_free_games=1");
			HttpResponse response;

			try {
				client = HttpClientBuilder.create().build();
				localContext = new BasicHttpContext();
				response = client.execute(httpget2, localContext);

				HttpEntity entity = response.getEntity();
				gameStats = getASCIIContentFromEntity(entity);

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return gameStats;
	}

	protected synchronized String getUserInfo() {

		if (userInfo == null) {
			httpget3 = new HttpGet("https://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?key=" + key
					+ "&steamids=" + steamId);
			HttpResponse response;

			try {
				client = HttpClientBuilder.create().build();
				localContext = new BasicHttpContext();
				response = client.execute(httpget3, localContext);

				HttpEntity entity = response.getEntity();
				userInfo = getASCIIContentFromEntity(entity);

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		return userInfo;
	}

	protected String getASCIIContentFromEntity(HttpEntity entity) throws IllegalStateException, IOException {
		InputStream in = entity.getContent();
		StringBuffer out = new StringBuffer();
		int n = 1;
		while (n > 0) {
			byte[] b = new byte[4096];
			n = in.read(b);
			if (n > 0)
				out.append(new String(b, 0, n));
		}
		return out.toString();
	}

	protected String getJsonValue(String key, String json, int order) {
		String value = null;
		if (order == 1)
			json = json.substring(json.indexOf(key) + 1);
		if (order == 2) {
			json = json.substring(json.indexOf(key) + 1);
			json = json.substring(json.indexOf(key) + 1);
		}
		try {
			int index1 = json.indexOf(key);
			int index2 = json.substring(index1).indexOf(":") + 1;
			int index3 = json.substring(index1 + index2).indexOf(",");
			value = json.substring(index1 + index2, index1 + index2 + index3);
			value = value.replaceAll("[\\s+\"]", "");

		} catch (Exception e) {
			return "0";
		}
		if (value != null) {
			if (value.contains(":")) {
				if (!value.contains("akamai"))
					value = value.replaceAll(":", " ");
			}
		}
		return value;
	}
}