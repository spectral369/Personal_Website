package com.freelancingpeter.pages;

import com.freelancingpeter.sides.Intrests;
import com.freelancingpeter.sides.SteamSide;
import com.freelancingpeter.utils.Utils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.AnchorTarget;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class Github extends FlexLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 382102507552229499L;
	Button steamDisplay = null;
	VerticalLayout content = null;
	boolean isSteamDisplayed = false;
	SteamSide steamSide = null;

	Button tootsDisplay = null;
	boolean isTootsDisplayed = false;
	Intrests intrestsSide = null;

	public Github() {
		content = new VerticalLayout();

		steamSide = new SteamSide();
		intrestsSide = new Intrests();
		steamDisplay = new Button(new Icon(VaadinIcon.ARROW_CIRCLE_RIGHT_O));
		tootsDisplay = new Button(new Icon(VaadinIcon.ARROW_CIRCLE_LEFT_O));
		add(steamDisplay);
		steamDisplay.getStyle().set("display", "none");
		steamDisplay.getStyle().set("display", "none");
		steamDisplay.addClickListener(e -> {
			isSteamDisplayed = !isSteamDisplayed;
			Utils.setLeftListener(steamSide, isSteamDisplayed, intrestsSide, steamDisplay, content);
			if (isTootsDisplayed) {
				isTootsDisplayed = !isTootsDisplayed;
				tootsDisplay.setIcon(new Icon(VaadinIcon.ARROW_CIRCLE_LEFT_O));
			}
		});
		add(steamSide);

		H2 h2title = new H2("Github projects");
		h2title.getStyle().set("text-align", "center");
		content.add(h2title);

		H3 h3RhythmBot = new H3("Rhythm-Bot");
		content.add(h3RhythmBot);

		Span textcontent = new Span();
		textcontent.add("Rhythm-Bot is a NodeJS discord bot for playing music from YouTube.\n");
		textcontent.getStyle().set("text-align", "center");
		Span desc = new Span();
		desc.add(
				"It can play anything that is not age restriced, yes, it can play live streaming, quite complete set of commands that can range from adding in queue to pause/stop/repeat/skip/search and more. More info is available on the github page.\n ");
		desc.getStyle().set("text-align", "center");
		Span build = new Span("Build with: Node17, Typescript, custom compiled ffmpeg instead of ffmpeg-static\n");
		Span alternatives = new Span(
				"Also I created Java alternative to this Discord Bot *Tempo-DBot* and a Rust version of this *Aurras*.");
		HorizontalLayout linkLayout = new HorizontalLayout();

		Anchor githublink1 = new Anchor("https://github.com/spectral369/Rhythm-Bot", "Rhythm-Bot");
		Anchor githublink11 = new Anchor("https://github.com/spectral369/Tempo-DBot", "Tempo-DBot");
		Anchor githublink12 = new Anchor("https://github.com/spectral369/Aurras", "Aurras");

		githublink1.setTarget(AnchorTarget.BLANK);
		githublink11.setTarget(AnchorTarget.BLANK);
		githublink12.setTarget(AnchorTarget.BLANK);

		linkLayout.add(githublink1, githublink11, githublink12);
		linkLayout.setAlignItems(Alignment.CENTER);

		content.add(textcontent);
		content.add(desc);
		content.add(build);
		content.add(alternatives);
		content.add(linkLayout);
		content.setAlignItems(Alignment.CENTER);

		H3 pdvBirotica = new H3("\n\nPDV Birotica\n");
		Span textcontent21 = new Span();
		textcontent21.add("PDV Birotica is a Vaadin website for romanian declarations and certificates.\n");
		textcontent21.getStyle().set("text-align", "center");
		Span build23 = new Span("Build with: Java1 17, Vaadin 23 and IText 7.\n");
		Anchor githublink24 = new Anchor("https://github.com/spectral369/PDV_birotica", "PDV_Birotica");
		githublink24.setTarget(AnchorTarget.BLANK);
		content.add(pdvBirotica);
		content.add(textcontent21);
		content.add(build23);
		content.add(githublink24);
		content.setAlignItems(Alignment.CENTER);

		H3 h3WebChecker = new H3("\n\nWebsiteChecker\n");

		Span textcontent2 = new Span();
		textcontent2.add("WebsiteChecker is a service that checks and displays web site availability.\n");
		textcontent2.getStyle().set("text-align", "center");
		Span desc2 = new Span(
				"WebsiteChecker is able to add, check and display information about added websites availability based on http responses. After checking it "
						+ "displayes a thumbnail of the web page, type of connection(http/https), added date, status code and a history of checks.  After a web page "
						+ "is added it is checked constantly based on a time scheduler. More info is available on the github page.\n ");
		desc2.getStyle().set("text-align", "center");

		Span build2 = new Span("Build with: Java,Selenium, MariaDB  with Vaadin 8 frontend framework\n");
		Anchor githublink2 = new Anchor("https://github.com/spectral369/WebsiteChecker", "WebsiteChecker");
		githublink2.setTarget(AnchorTarget.BLANK);
		content.add(h3WebChecker);
		content.add(textcontent2);
		content.add(desc2);
		content.add(build2);
		content.add(githublink2);
		content.setAlignItems(Alignment.CENTER);

		H3 h3SoundSea = new H3("\n\nSoundSea\n");
		Span textcontent3 = new Span();
		textcontent3.add(
				"SoundSea is desktop FXML application that allows you to search, play and download mp3 songs for free :-)\n");
		textcontent3.getStyle().set("text-align", "center");
		Span build3 = new Span("Build with: Java8 and slider.kz as base for song fetching.\n");
		Anchor githublink3 = new Anchor("https://github.com/spectral369/SoundSea", "SoundSea");
		githublink3.setTarget(AnchorTarget.BLANK);
		content.add(h3SoundSea);
		content.add(textcontent3);
		content.add(build3);
		content.add(githublink3);

		content.setAlignItems(Alignment.CENTER);

		H3 h3TW2_Bot = new H3("\n\nTW2-Bot\n");
		Span textcontent4 = new Span();
		textcontent4.add("TW2-Bot is a selenium based farming bot for Tribal Wars 2.\n\n");
		textcontent4.getStyle().set("text-align", "center");
		Span desc4 = new Span(
				"It has multiple uses from farming nearby villages to storage work and player attack farming. Barbarian village farming is based on Knapsack algorithm and "
						+ "it has the ability to cycle those attacks. Headless mode is available and this app is available in system tray. More info is available on the github page.\n");
		desc4.getStyle().set("text-align", "center");
		Span build4 = new Span("Build with: Java and Selenium\n");
		Anchor githublink4 = new Anchor("https://github.com/spectral369/TW2-Bot", "TW2-Bot");
		githublink4.setTarget(AnchorTarget.BLANK);
		content.add(h3TW2_Bot);
		content.add(textcontent4);
		content.add(desc4);
		content.add(build4);
		content.add(githublink4);
		content.setAlignItems(Alignment.CENTER);

		H3 h3MyBloodBank = new H3("\n\nMyBloodBank\n");
		Span textcontent5 = new Span();
		textcontent5.add("MyBloodBank is an blood donation website.\n\n");
		textcontent5.getStyle().set("text-align", "center");
		Span desc5 = new Span(
				"MyBlood Bank is an attempt for a blood donation web site using Vaadin. It has some basic functions like Install Setup, language change, "
						+ "blood donation info, user control panel. More info is available on the github page.\n");
		desc5.getStyle().set("text-align", "center");
		Span build5 = new Span("Build with: Java and Vaadin\n");
		Anchor githublink5 = new Anchor("https://github.com/spectral369/MyBloodBank", "MyBloodBank");
		githublink5.setTarget(AnchorTarget.BLANK);
		content.add(h3MyBloodBank);
		content.add(textcontent5);
		content.add(desc5);
		content.add(build5);
		content.add(githublink5);
		content.setAlignItems(Alignment.CENTER);

		H3 h3QueryByExample_FX = new H3("\n\nQueryByExample-FX\n");
		Span textcontent6 = new Span();
		textcontent6.add("QueryByExample-FX is my implementation of Query by Example in Java\n\n");
		textcontent6.getStyle().set("text-align", "center");
		Span desc6 = new Span(
				"QueryByExample-FX has multiple database support MySQL/OracleDB/MongoDB and multi-column table selection(if I remember right). It also "
						+ "can export data to pdf and xml and display server status connection.\n");
		desc6.getStyle().set("text-align", "center");
		Span build6 = new Span("Build with: Java and FXML\n");
		Anchor githublink6 = new Anchor("https://github.com/spectral369/QueryByExample-FX", "QueryByExample-FX");
		githublink6.setTarget(AnchorTarget.BLANK);
		content.add(h3QueryByExample_FX);
		content.add(textcontent6);
		content.add(desc6);
		content.add(build6);
		content.add(githublink6);
		content.setAlignItems(Alignment.CENTER);

		H3 h3more = new H3("\n\nMore Projects available on github.");
		h3more.getStyle().set("text-align", "center");
		content.add(h3more);

		content.setAlignItems(Alignment.CENTER);
		content.getStyle().set("border-right", "ridge");
		content.getStyle().set("border-left", "ridge");

		content.setHeightFull();

		Scroller scroller = new Scroller(content);
		scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
		scroller.setSizeFull();
		scroller.getStyle().set("scrollbar-width", "none");
		add(scroller);

		add(tootsDisplay);
		tootsDisplay.getStyle().set("display", "none");
		tootsDisplay.addClickListener(e -> {
			isTootsDisplayed = !isTootsDisplayed;
			Utils.setRightListener(steamSide, isTootsDisplayed, intrestsSide, tootsDisplay, content);
			if (isSteamDisplayed) {
				isSteamDisplayed = !isSteamDisplayed;
				steamDisplay.setIcon(new Icon(VaadinIcon.ARROW_CIRCLE_RIGHT_O));
			}
		});
		add(intrestsSide);

		UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {

			Utils.resizeListenerMethod(e, steamDisplay, steamSide, tootsDisplay, intrestsSide);
			scroller.setHeight(0.76 * (e.getHeight()) + "px");
		});

		setJustifyContentMode(JustifyContentMode.CENTER);

		setSizeFull();
		UI.getCurrent().getPage().retrieveExtendedClientDetails(details -> {
			Utils.resize(details.getScreenWidth(), steamDisplay, tootsDisplay, steamSide, intrestsSide);
			scroller.setHeight(0.76 * (details.getWindowInnerHeight()) + "px");
		});

	}

}