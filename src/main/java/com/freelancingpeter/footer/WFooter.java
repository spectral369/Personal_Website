package com.freelancingpeter.footer;


import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.AnchorTarget;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@CssImport("./themes/freelancingpeter/styles.css")
public class WFooter extends VerticalLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4015282485126743768L;

	public WFooter() {

		HorizontalLayout footerContent = new HorizontalLayout();
		footerContent.setWidthFull();

		VerticalLayout madewith = new VerticalLayout();

		Div menu = new Div();
		menu.addClassName("menu");
		Div label = new Div();
		Span lspan = new Span("Follow me");
		label.addClassName("label");
		label.add(lspan);
		menu.add(label);
		Div spacer = new Div();
		spacer.addClassName("spacer");
		menu.add(spacer);
		Div item1 = new Div();
		item1.addClassName("item");
		Anchor i1span = new Anchor("https://github.com/spectral369", "Github");
		i1span.setTarget(AnchorTarget.BLANK);
		item1.add(i1span);
		menu.add(item1);
		Div item2 = new Div();
		item2.addClassName("item");
		Anchor i2span = new Anchor("https://mastodon.social/@spectral_shadow", "Mastodon");
		i2span.setTarget(AnchorTarget.BLANK);
		item2.add(i2span);
		menu.add(item2);
		Div item3 = new Div();
		item3.addClassName("item");
		Span i3span = new Span("©Spectral369");
		item3.add(i3span);
		menu.add(item3);

		madewith.add(menu);

		footerContent.add(madewith);

		footerContent.setHeight("10px");

		madewith.setAlignItems(Alignment.CENTER);
		madewith.setJustifyContentMode(JustifyContentMode.CENTER);
		footerContent.setVerticalComponentAlignment(Alignment.CENTER, madewith);
		// footerContent.setVerticalComponentAlignment(Alignment.CENTER, visits);
		footerContent.setJustifyContentMode(JustifyContentMode.CENTER);

		setJustifyContentMode(JustifyContentMode.CENTER);

		add(footerContent);

		setAlignItems(Alignment.CENTER);
		setHeight("10px");

	}

}