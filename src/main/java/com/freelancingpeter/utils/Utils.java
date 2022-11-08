package com.freelancingpeter.utils;

import com.freelancingpeter.sides.Intrests;
import com.freelancingpeter.sides.SteamSide;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.BrowserWindowResizeEvent;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;

public class Utils {

	public static boolean isMobileDevice() {
		WebBrowser webBrowser = VaadinSession.getCurrent().getBrowser();
		return webBrowser.isAndroid() || webBrowser.isIPhone() || webBrowser.isWindowsPhone();
	}

	public static void resize(int width, Button left, Button right, SteamSide steam, Intrests intrests) {
		if (!isMobileDevice()) {
			if (width > 1430) {
				steam.getStyle().set("display", "inline-flex");
				intrests.getStyle().set("display", "inline-flex");
				left.getStyle().set("display", "none");
				right.getStyle().set("display", "none");
			} else {
				steam.getStyle().set("display", "none");
				intrests.getStyle().set("display", "none");
				left.getStyle().set("display", "inline-flex");
				right.getStyle().set("display", "inline-flex");
			}
		} else {
			steam.getStyle().set("display", "none");
			intrests.getStyle().set("display", "none");
			left.getStyle().set("display", "inline-flex");
			right.getStyle().set("display", "inline-flex");
		}

	}

	public static void setLeftListener(SteamSide steam, boolean isSteamDisplayed, Intrests intrests,
			Button steamDisplay, VerticalLayout center) {
		if (isMobileDevice())
			steam.setWidth("300vw");
		else
			steam.setWidth("40vw");
		if (isSteamDisplayed) {
			steam.getStyle().set("display", "inline-flex");
			steamDisplay.setIcon(new Icon(VaadinIcon.ARROW_CIRCLE_LEFT_O));
			if (isMobileDevice()) {
				center.getStyle().set("display", "none");
				intrests.getStyle().set("display", "none");
			}
		} else {
			steam.getStyle().set("display", "none");
			steamDisplay.setIcon(new Icon(VaadinIcon.ARROW_CIRCLE_RIGHT_O));
			if (isMobileDevice())
				center.getStyle().set("display", "inline-flex");
		}
	}

	public static void setRightListener(SteamSide steam, boolean isTootsDisplayed, Intrests intrests,
			Button tootsDisplay, VerticalLayout center) {
		if (isMobileDevice())
			intrests.setWidth("300vw");
		else
			intrests.setWidth("40vw");
		if (isTootsDisplayed) {
			intrests.getStyle().set("display", "inline-flex");
			tootsDisplay.setIcon(new Icon(VaadinIcon.ARROW_CIRCLE_RIGHT_O));
			if (isMobileDevice()) {
				center.getStyle().set("display", "none");
				steam.getStyle().set("display", "none");
			}
		} else {
			intrests.getStyle().set("display", "none");
			tootsDisplay.setIcon(new Icon(VaadinIcon.ARROW_CIRCLE_LEFT_O));
			if (isMobileDevice())
				center.getStyle().set("display", "inline-flex");
		}

	}

	public static void resizeListenerMethod(BrowserWindowResizeEvent e, Button steamDisplay, SteamSide steamSide,
			Button tootsDisplay, Intrests intrests) {
		if (!isMobileDevice()) {
			if (e.getWidth() > 1430) {
				steamDisplay.getStyle().set("display", "none");

				steamSide.getStyle().set("display", "inline-flex");
				steamSide.setWidth("15vw");

				tootsDisplay.getStyle().set("display", "none");
				intrests.getStyle().set("display", "inline-flex");
				intrests.setWidth("15vw");
			} else {
				steamSide.getStyle().set("display", "none");
				steamDisplay.getStyle().set("display", "inline-flex");
				intrests.getStyle().set("display", "none");
				tootsDisplay.getStyle().set("display", "inline-flex");
			}

		} else {
			steamSide.getStyle().set("display", "none");

			steamDisplay.getStyle().set("display", "inline-flex");
			intrests.getStyle().set("display", "none");
			tootsDisplay.getStyle().set("display", "inline-flex");
		}
	}

}