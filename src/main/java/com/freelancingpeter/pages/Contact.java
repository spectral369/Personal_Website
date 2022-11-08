package com.freelancingpeter.pages;

import com.freelancingpeter.sides.Intrests;
import com.freelancingpeter.sides.SteamSide;
import com.freelancingpeter.utils.Mail2;
import com.freelancingpeter.utils.Utils;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextAreaVariant;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.Setter;
import com.vaadin.flow.data.validator.EmailValidator;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.function.ValueProvider;

public class Contact extends FlexLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2032064944130364621L;

	Button steamDisplay = null;
	VerticalLayout content = null;
	boolean isSteamDisplayed = false;
	SteamSide steamSide = null;

	Button tootsDisplay = null;
	boolean isTootsDisplayed = false;
	Intrests intrestsSide = null;

	private Binder<Contact> binder;

	public Contact() {
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
		binder = new Binder<Contact>(Contact.class);

		H2 h2title = new H2("Contact Info");
		content.add(h2title);
		Span textcontent = new Span();
		textcontent.add("You can contact me on my email: 1dd151fb@opayq.com\n");
		content.add(textcontent);
		Span cmessage = new Span("Or you can send me a direct message via next contact form.\n\n");
		content.add(cmessage);

		HorizontalLayout nameAndEmailLayout = new HorizontalLayout();
		nameAndEmailLayout.getStyle().set("overflow-flow", "");
		TextField name = new TextField("Name");
		name.setPlaceholder("Name");
		name.setErrorMessage("Name Required");
		name.setWidth("20vw");
		name.setRequiredIndicatorVisible(true);
		binder.forField(name).asRequired().withValidator(str -> str.length() > 3, "Name must be greater than 3 chars!")
				.bind(new ValueProvider<Contact, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(Contact source) {
						return null;
					}
				}, new Setter<Contact, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(Contact bean, String fieldvalue) {

					}
				});

		EmailField email = new EmailField("E-mail");
		email.setRequiredIndicatorVisible(true);
		email.setPlaceholder("something@something.something");
		email.setErrorMessage("E-mail Required");
		email.setWidth("20vw");
		binder.forField(email).asRequired().withValidator(new EmailValidator("E-mail must be valid"))
				.bind(new ValueProvider<Contact, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(Contact source) {
						return null;
					}
				}, new Setter<Contact, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(Contact bean, String fieldvalue) {

					}
				});

		nameAndEmailLayout.add(name, email);
		nameAndEmailLayout.setAlignItems(Alignment.CENTER);

		TextArea message = new TextArea("Message");
		message.setRequiredIndicatorVisible(true);
		message.setPlaceholder("This will be your message content!");
		message.setErrorMessage("Message content Required");
		message.setValueChangeMode(ValueChangeMode.TIMEOUT);
		message.setValueChangeTimeout(1000);
		message.addThemeVariants(TextAreaVariant.LUMO_ALIGN_CENTER);
		binder.forField(message).asRequired()
				.withValidator(str -> str.length() > 20, "Name must be greater than 20 chars!")
				.bind(new ValueProvider<Contact, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public String apply(Contact source) {
						return null;
					}
				}, new Setter<Contact, String>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void accept(Contact bean, String fieldvalue) {

					}
				});

		Button send = new Button("Send", VaadinIcon.PAPERPLANE_O.create());
		send.setEnabled(false);

		send.addClickListener(event -> {
			boolean isSuccessful1 = false;

			try {
				// new Mail(name.getValue().trim(),email.getValue().trim(),
				// message.getValue().trim());
				new Mail2(name.getValue().trim(), email.getValue().trim(), message.getValue().trim());
				isSuccessful1 = true;
			} catch (Exception e1) {
				isSuccessful1 = false;
				/*
				 * try { new Mail2(name.getValue().trim(),email.getValue().trim(),
				 * message.getValue().trim()); isSuccessful2 = true; } catch (Exception e) {
				 * isSuccessful2 = false; }
				 */
			}

			name.clear();
			email.clear();
			message.clear();
			name.setRequiredIndicatorVisible(false);
			email.setRequiredIndicatorVisible(false);
			message.setRequiredIndicatorVisible(false);
			Notification.show("Message send... " + isSuccessful1, 5000, Position.MIDDLE);
			/*
			 * if (isSuccessful1 == false) Notification.show("Message send... " +
			 * isSuccessful2, 5000, Position.MIDDLE);
			 */
		});

		binder.addStatusChangeListener(event -> {
			if (binder.isValid()) {
				send.setEnabled(true);
			} else {
				send.setEnabled(false);
			}
		});
		content.add(nameAndEmailLayout);
		content.add(message);
		content.add(send);

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
			message.setWidth(0.40 * e.getWidth() + "px");
			message.setHeight(0.30 * e.getHeight() + "px");
		});

		setJustifyContentMode(JustifyContentMode.CENTER);

		setSizeFull();
		UI.getCurrent().getPage().retrieveExtendedClientDetails(details -> {
			Utils.resize(details.getScreenWidth(), steamDisplay, tootsDisplay, steamSide, intrestsSide);
			scroller.setHeight(0.76 * (details.getWindowInnerHeight()) + "px");
			message.setWidth(0.40 * details.getWindowInnerWidth() + "px");
			message.setHeight(0.30 * details.getWindowInnerHeight() + "px");
		});

	}

}