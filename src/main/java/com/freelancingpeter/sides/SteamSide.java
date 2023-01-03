package com.freelancingpeter.sides;

import java.text.DecimalFormat;

import com.freelancingpeter.data.Data;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.progressbar.ProgressBarVariant;

@CssImport("./themes/freelancingpeter/styles.css")
public class SteamSide extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6482447500473270693L;

	VerticalLayout steamContent = null;
	Scroller steamScroller = null;

	public SteamSide() {

		if (Data.INSTANCE.getSteam().isEmpty()) {
			ProgressBar loading = new ProgressBar();
			loading.setIndeterminate(true);
			loading.addThemeVariants(ProgressBarVariant.LUMO_CONTRAST);
			add(loading);
			setAlignItems(Alignment.CENTER);
			setHorizontalComponentAlignment(Alignment.CENTER);
			setJustifyContentMode(JustifyContentMode.CENTER);
			setWidth("12vw");
		} else {
			// }
			steamContent = new VerticalLayout();

			steamContent.setAlignItems(Alignment.CENTER);
			/// end loading
			Button labelbtn = new Button("My Steam Info", VaadinIcon.GAMEPAD.create());
			labelbtn.addClassName("clearDisabled");
			labelbtn.setEnabled(false);
			add(labelbtn);
			setHorizontalComponentAlignment(Alignment.CENTER, labelbtn);

			Avatar av = new Avatar(getJsonValue("personaname", Data.INSTANCE.getSteam().get(0) /* getUserInfo() */, 0),
					getJsonValue("avatarfull", Data.INSTANCE.getSteam().get(0), 0));

			av.getStyle().set("width", "auto");
			av.getStyle().set("height", "auto");
			steamContent.add(av);

			Label username = new Label(getJsonValue("personaname", Data.INSTANCE.getSteam().get(0), 0));
			username.getStyle().set("text-shadow", "1px 1px 2px #000000aa");
			username.getStyle().set("font-family", "Motiva Sans, Sans-serif");
			username.getStyle().set("font-weight", "200");
			steamContent.add(username);
			Label gamesOwned = new Label(
					"Games owned: " + getJsonValue("game_count", Data.INSTANCE.getSteam().get(1), 0));
			gamesOwned.getStyle().set("text-shadow", "1px 1px 2px #000000aa");
			gamesOwned.getStyle().set("font-family", "Motiva Sans, Sans-serif");
			gamesOwned.getStyle().set("font-weight", "200");
			steamContent.add(gamesOwned);

			// getRecentGames();

			for (int i = 0; i < Data.INSTANCE.getRecentGames(); i++) {
				HorizontalLayout latestGame0 = new HorizontalLayout();
				latestGame0.addClassName("recentGames");

				String game0AvatarStr = "https://media.steampowered.com/steamcommunity/public/images/" + "apps/"
						+ getJsonValue("appid", Data.INSTANCE.getSteam().get(2), i) + "/"
						+ getJsonValue("img_icon_url", Data.INSTANCE.getSteam().get(2), i) + ".jpg";
				Avatar game0Avatar = new Avatar(getJsonValue("name", Data.INSTANCE.getSteam().get(2), i),
						game0AvatarStr);
				VerticalLayout game0Stats = new VerticalLayout();
				String gameNamePre = getJsonValue("name", Data.INSTANCE.getSteam().get(2), i);
				String[] r = gameNamePre.split("(?=\\p{Lu})");
				StringBuilder sb1 = new StringBuilder();
				for (String rs : r) {
					sb1.append(rs);
					sb1.append(" ");
				}

				String prel2 = sb1.toString();
				String[] r2 = prel2.split("(?=[–&0-9])");
				sb1 = new StringBuilder();
				for (String rs : r2) {
					sb1.append(rs);
					sb1.append(" ");
				}

				String prel3 = sb1.toString();
				sb1 = new StringBuilder();
				String[] r3 = prel3.split("(?=of|by)");
				for (String rs : r3) {
					sb1.append(rs);
					sb1.append(" ");
				}

				String finalGameName = sb1.toString().replaceAll("\\s+", " ");

				Label game0Name = new Label(finalGameName);
				game0Name.setWidthFull();
				game0Stats.addClassName("labelAdjust");

				game0Stats.add(game0Name);
				int playtimeGame0Min = Integer
						.parseInt(getJsonValue("playtime_2weeks", Data.INSTANCE.getSteam().get(2), i));
				float playtimeGame0Hours = (float) playtimeGame0Min / 60;
				DecimalFormat df = new DecimalFormat("#.#");
				Label game0Playtime = new Label(
						"Played in the last 2 weeks: " + String.valueOf(df.format(playtimeGame0Hours)) + " Hours");

				latestGame0.getStyle().set("border", "2px ridge white");
				game0Stats.add(game0Playtime);
				latestGame0.add(game0Avatar);
				latestGame0.add(game0Stats);
				latestGame0.setAlignItems(Alignment.CENTER);
				latestGame0.setWidthFull();
				latestGame0.setMargin(false);
				latestGame0.setSpacing(false);
				steamContent.add(latestGame0);

				setAlignItems(Alignment.CENTER);
				setAlignSelf(Alignment.CENTER, av);

			}

			if (steamContent != null) {
				steamContent.setJustifyContentMode(JustifyContentMode.CENTER);

				steamScroller = new Scroller(steamContent);
				steamScroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
				steamScroller.addClassName("scroll");

				add(steamScroller);

			}

			setHorizontalComponentAlignment(Alignment.CENTER);
			setJustifyContentMode(JustifyContentMode.START);

			getStyle().set("--lumo-space-m", "0.35em");
			getStyle().set("overflow-y", "scroll");
			addClassName("scroll");
			setWidth("15vw");
			UI.getCurrent().getPage().retrieveExtendedClientDetails(details -> {
				steamScroller.setHeight(0.76 * (details.getWindowInnerHeight()) + "px");
			});
			UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
				steamScroller.setHeight(0.76 * (e.getHeight()) + "px");
			});

		}

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