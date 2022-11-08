package com.freelancingpeter.pages;

import com.freelancingpeter.sides.Intrests;
import com.freelancingpeter.sides.SteamSide;
import com.freelancingpeter.utils.Utils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;

public class About extends FlexLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6778143383869158463L;

	Button steamDisplay = null;
	VerticalLayout content = null;
	boolean isSteamDisplayed = false;
	SteamSide steamSide = null;

	Button tootsDisplay = null;
	boolean isTootsDisplayed = false;
	Intrests intrestsSide = null;

	public About() {
		content = new VerticalLayout();
		steamSide = new SteamSide();
		intrestsSide = new Intrests();
		steamDisplay = new Button(new Icon(VaadinIcon.ARROW_CIRCLE_RIGHT_O));
		tootsDisplay = new Button(new Icon(VaadinIcon.ARROW_CIRCLE_LEFT_O));
		add(steamDisplay);
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

		H2 h2title = new H2("Well, about me, right ?");
		content.add(h2title);
		Paragraph textContent = new Paragraph();
		textContent.getStyle().set("text-align", "center");
		Span text1 = new Span("My name is Peter P. I'm really passionate about coding and building software.");
		Span text2 = new Span(
				"I develop quite a lot of things(as you can see on my github) quite constantly, sometimes I'm amazed by my perseverence to "
						+ "fix/update my old projects, As I am a loser and can't get a job I do this with pleasure and it relaxes me !");
		Span text3 = new Span(
				"ATM I'm working on a few projects that include technologies such as Vaadin, NodeJS, EdgeDB (graph-relational database) and improving my Rust !");

		Span text4 = new Span(
				"P.S I have a degree in Computer Science, but it seems I can wipe my a** with that diploma !! hahahahaha");

		textContent.add(text1);
		textContent.getElement().appendChild(new Element("br"));
		textContent.add(text2);
		textContent.getElement().appendChild(new Element("br"));
		textContent.add(text3);
		textContent.getElement().appendChild(new Element("br"));
		textContent.getElement().appendChild(new Element("br"));
		textContent.add(text4);
		textContent.getElement().appendChild(new Element("br"));
		textContent.getElement().appendChild(new Element("br"));
		content.add(textContent);
		content.setAlignItems(Alignment.CENTER);
		content.getStyle().set("border-right", "ridge");
		content.getStyle().set("border-left", "ridge");
		content.setHeightFull();

		Scroller scroller = new Scroller(content);
		scroller.setScrollDirection(Scroller.ScrollDirection.VERTICAL);
		scroller.setSizeFull();

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