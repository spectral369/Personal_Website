package com.freelancingpeter.pages;

import com.freelancingpeter.sides.Intrests;
import com.freelancingpeter.sides.SteamSide;
import com.freelancingpeter.utils.Utils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.IFrame;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.StreamRegistration;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinSession;

@CssImport("./themes/freelancingpeter/styles.css")
public class CV extends FlexLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4469701933095191871L;

	Button steamDisplay = null;
	VerticalLayout content = null;
	boolean isSteamDisplayed = false;
	SteamSide steamSide = null;

	Button tootsDisplay = null;
	boolean isTootsDisplayed = false;
	Intrests intrestsSide = null;

	public CV() {
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

		H2 h2title = new H2("Redacted Curriculum Vitae");
		h2title.getStyle().set("text-align", "center");
		content.add(h2title);
		Span textcontent = new Span();
		textcontent.add("Yes, yes, it's on old version, it's the only one I've found !");
		textcontent.getStyle().set("text-align", "center");
		content.add(textcontent);
		content.setAlignItems(Alignment.CENTER);
		content.getStyle().set("border-right", "ridge");
		content.getStyle().set("border-left", "ridge");

		StreamResource streamResource = new StreamResource("webSecure.pdf",
				() -> getClass().getResourceAsStream("/META-INF/resources/webSecure.pdf"));
		StreamRegistration registration = VaadinSession.getCurrent().getResourceRegistry()
				.registerResource(streamResource);
		IFrame iframe = new IFrame(registration.getResourceUri().toString());
		iframe.setSizeFull();
		content.add(iframe);

		// content.add(new CVEmbedded());
		content.setAlignItems(Alignment.CENTER);
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