package com.freelancingpeter.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public final class AccountsInfo {

	private final String STEAMKEY;
	private final String STEAMID;
	private final String MASTODONTOKEN;
	private final String MASTODONID;

	private final String CONFIG_FILE = "info.config";

	private static AccountsInfo info = null;

	public static AccountsInfo getInstance() {
		if (info == null) {
			info = new AccountsInfo();

		}
		return info;
	}

	public AccountsInfo() {

		FileReader fr;
		BufferedReader br;
		Map<String, String> map = new HashMap<String, String>(3);
		try {
			String userhome = System.getProperty("user.home");
			File tokenFile = new File(userhome +File.separator+ CONFIG_FILE);
			System.out.println(tokenFile.getPath());
			if (!tokenFile.exists()) {
				FileWriter fw = new FileWriter(tokenFile);
				fw.write("<Token Name> : <value>");
				fw.close();
				System.out.println("No tokens provided. Add tokens in info.config ");
				System.exit(100);
			} else {
				fr = new FileReader(tokenFile);
				br = new BufferedReader(fr);
				String line;
				while ((line = br.readLine()) != null) {
					String[] data = line.split(":");
					switch (data[0]) {
					case "SteamKey":
						try {
							map.put(getClass().getDeclaredField("STEAMKEY").getName(), data[1]);
						} catch (NoSuchFieldException | SecurityException e) {

							System.out.println("Steam key fault");
						}

						break;
					case "SteamID":
						try {
							map.put(getClass().getDeclaredField("STEAMID").getName(), data[1]);
						} catch (NoSuchFieldException | SecurityException e) {

							System.out.println("Steam id fault");
						}
						break;
					case "MastodonToken":
						try {
							map.put(getClass().getDeclaredField("MASTODONTOKEN").getName(), data[1]);
						} catch (NoSuchFieldException | SecurityException e) {

							System.out.println("Mastodon token fault");
						}

						break;
					case "MastodonID":
						try {
							map.put(getClass().getDeclaredField("MASTODONID").getName(), data[1]);
						} catch (NoSuchFieldException | SecurityException e) {

							System.out.println("Mastodon ID fault");
						}
						break;
					}
				}

			}
		} catch (IOException e) {

			System.out.println(e.getLocalizedMessage());
		}

		this.MASTODONTOKEN = map.get("MASTODONTOKEN");
		this.MASTODONID = map.get("MASTODONID");
		this.STEAMKEY = map.get("STEAMKEY");
		this.STEAMID = map.get("STEAMID");

	}

	public String getSTEAMKEY() {
		return STEAMKEY;
	}

	public String getSTEAMID() {
		return STEAMID;
	}

	public String getMASTODONTOKEN() {
		return MASTODONTOKEN;
	}

	public String getMASTODONID() {
		return MASTODONID;
	}

}
