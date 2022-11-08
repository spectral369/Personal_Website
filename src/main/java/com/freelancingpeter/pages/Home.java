package com.freelancingpeter.pages;

import com.freelancingpeter.sides.Intrests;
import com.freelancingpeter.sides.SteamSide;
import com.freelancingpeter.utils.Utils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;

@PageTitle("Freelancing Peter")
public class Home extends FlexLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 315375221544904587L;

	Button steamDisplay = null;
	VerticalLayout content = null;
	boolean isSteamDisplayed = false;
	SteamSide steamSide = null;

	Button tootsDisplay = null;
	boolean isTootsDisplayed = false;
	Intrests intrestsSide = null;

	public Home() {

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

		H2 h2title = new H2("Welcome");
		content.add(h2title);
		Span textcontent = new Span();
		textcontent.add("My name is Peter P. I'm really passionate about coding and building software.");
		textcontent.getStyle().set("text-align", "center");
		content.add(textcontent);
		Span textContent2 = new Span("I develop quite a lot of things(as you can see on my github) quite constantly. \n"
				+ "I use Java(openJDK 17)(I know, I know) as my main programming language, I also use Javascript, Kotlin(learning), PhP(rarely), Rust(learning)");
		textContent2.getStyle().set("text-align", "center");
		Span textContent3 = new Span("This website was made Vaadin 23.x.x !");
		textContent3.getStyle().set("text-align", "center");
		Span textContent4 = new Span(
				"Update 10.08.2022: I am quite pissed of because after ~40 job applications and ~20 interviews I cannot get a fucking job, latest disappointment was \n"
						+ "at a interview for an academy ffs, I hope the world gets nuked(not I don't want that, I'm just bitter and pissed off) \n"
						+ "the only way to get a job is through connections, the rest of the applicants  they call them for interview just to have the numbers on statistics "
						+ "so it's quite normal for people to fuck up and hate the western world !");
		textContent4.getStyle().set("text-align", "center");
		Span textContent5 = new Span(
				"Update 07.11.2022: Someone got me in a devops 'tutorial' academy course and I'm enoying it, I doubt it will lead me somewhere but \n"
						+ "I really like the learning(even if it's quite easy) that includes bash scripting and VMs \n"
						+ "I'm also working on my raspberry pi, I hope I can put this website on it and running it from home! but I have to wait for my internet provider \n"
						+ "to unlock the ports ! I'm stil undecided what OS to put on it, Raspberry pi OS; Rocky Linux; or maybe  nixOS(but I have to learn the language)\n"
						+ "P.S Still no job ! and EU pisses me off more each passing day! latest annoyance: they put 2FA on europass portal and I cannot access it !\n"
						+ "P.P.S I should re start working on this website again !");
		textContent5.getStyle().set("text-align", "center");
		content.add(textContent2);
		content.add(textContent3);
		content.add(textContent4);
		content.add(textContent5);
		content.setAlignItems(Alignment.CENTER);
		content.getStyle().set("border-right", "ridge");
		content.getStyle().set("border-left", "ridge");

		content.setHeightFull();

		Scroller scroller = new Scroller(content);
		scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
		scroller.setSizeFull();
		add(scroller);

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