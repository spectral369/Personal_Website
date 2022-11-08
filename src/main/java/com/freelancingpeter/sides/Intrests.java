package com.freelancingpeter.sides;

import com.freelancingpeter.data.Data;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.Scroller.ScrollDirection;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.progressbar.ProgressBarVariant;

public class Intrests extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1851574172840474802L;
	VerticalLayout toots = null;
	Scroller tootScroller = null;

	public Intrests() {

		if (Data.INSTANCE.getToots().isEmpty()) {
			ProgressBar loading = new ProgressBar();
			loading.setIndeterminate(true);
			loading.addThemeVariants(ProgressBarVariant.LUMO_CONTRAST);
			add(loading);
			setAlignItems(Alignment.CENTER);
			setHorizontalComponentAlignment(Alignment.CENTER);
			setJustifyContentMode(JustifyContentMode.CENTER);
			setWidth("12vw");
		} else {

			toots = new VerticalLayout();
			toots.setAlignItems(Alignment.CENTER);
			Button labelbtn = new Button("My Mastodon Toots", VaadinIcon.USERS.create());
			labelbtn.addClassName("clearDisabled");
			labelbtn.setEnabled(false);
			add(labelbtn);
			setHorizontalComponentAlignment(Alignment.CENTER, labelbtn);

			for (int i = 0; i < Data.INSTANCE.getToots().size(); i++) {

				HorizontalLayout tootContent = new HorizontalLayout();
				tootContent.getStyle().set("border", "2px ridge white");
				Icon tootIcon = new Icon(VaadinIcon.DIPLOMA_SCROLL);
				tootContent.add(tootIcon);
				VerticalLayout textContent = new VerticalLayout();
				Label tootText = new Label(Data.INSTANCE.getToots().get(i));
				tootText.addClassName("labelAdjust");
				textContent.add(tootText);
				tootContent.add(textContent);

				tootContent.setAlignItems(Alignment.CENTER);

				tootContent.setWidthFull();
				tootContent.setMargin(false);
				tootContent.setSpacing(false);

				toots.add(tootContent);

			}

			if (toots != null) {

				toots.setJustifyContentMode(JustifyContentMode.CENTER);
				tootScroller = new Scroller(toots);
				tootScroller.setScrollDirection(ScrollDirection.VERTICAL);
				tootScroller.addClassName("scroll");

				add(tootScroller);

			}
			setHorizontalComponentAlignment(Alignment.CENTER);

			getStyle().set("--lumo-space-m", "0.35em");
			getStyle().set("overflow-y", "scroll");

			addClassName("scroll");
			setWidth("15vw");
			UI.getCurrent().getPage().retrieveExtendedClientDetails(details -> {
				tootScroller.setHeight(0.76 * (details.getWindowInnerHeight()) + "px");
			});
			UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {
				tootScroller.setHeight(0.76 * (e.getHeight()) + "px");
			});

		}
	}

}