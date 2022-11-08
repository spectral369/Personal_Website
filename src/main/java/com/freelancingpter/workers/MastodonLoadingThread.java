package com.freelancingpter.workers;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import com.freelancingpeter.data.AccountsInfo;
import com.freelancingpeter.data.Data;

public class MastodonLoadingThread implements Runnable {

	private final String ACCESSTOKEN;

	private String ACCOUNTID;

	List<String> toots = null;
	private String json = null;

	private static MastodonLoadingThread thread = null;

	public static MastodonLoadingThread getInstance(AccountsInfo info) {
		if (thread == null) {
			thread = new MastodonLoadingThread(info.getMASTODONTOKEN(), info.getMASTODONID());

		}
		return thread;
	}

	public MastodonLoadingThread(String token, String id) {
		this.ACCESSTOKEN = token;
		this.ACCOUNTID = id;

	}

	@Override
	public void run() {
		// getToots();
		System.out.println("runMastodon");
		Data.INSTANCE.clearMasto();
		Data.INSTANCE.setMasto(getToots());
		System.out.println("doneMastodon");
		toots = null;
		json = null;

	}

	protected synchronized List<String> getToots() {
		if (toots == null) {

			toots = new LinkedList<String>();
			CloseableHttpClient client = HttpClients.custom()
					.setDefaultRequestConfig(RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build())
					.build();

			HttpUriRequest request = RequestBuilder.get()
					.setUri("https://mastodon.social/api/v1/accounts/" + ACCOUNTID + "/statuses")

					.setHeader(HttpHeaders.CONTENT_TYPE, "application/json")

					.addParameter("exclude_replies", "true").setHeader("exclude_replies", "true")
					.setHeader("Authorization", "Bearer " + ACCESSTOKEN)

					.build();

			try {

				CloseableHttpResponse response = client.execute(request);
				HttpEntity entity = response.getEntity();

				json = getASCIIContentFromEntity(entity);

			} catch (IOException e) {

				System.out.println(e.getMessage());
			}
		}

		try {
			toots.clear();
			if (!json.isBlank() || !json.isEmpty()) {

				JSONArray myArray = new JSONArray(json);

				for (int i = 0; i < myArray.length(); i++) {
					JSONObject obj = myArray.getJSONObject(i);
					JSONArray tag = obj.getJSONArray("tags");
					if (!tag.isEmpty()) {
						toots.add(Jsoup.parse(obj.get("content").toString()).text());
					}

				}

			}
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}

		return toots;
	}

	protected synchronized String getASCIIContentFromEntity(HttpEntity entity)
			throws IllegalStateException, IOException {
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

}