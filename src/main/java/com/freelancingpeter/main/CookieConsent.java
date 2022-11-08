package com.freelancingpeter.main;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;

public class CookieConsent extends Div{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4744675520566759486L;

	public CookieConsent() {
		Span message =  new Span("This web site uses some default framework cookies, they do not contain any relevant information besides session ID. ");
		add(message);
		Button  ok =  new Button("OK",VaadinIcon.CHECK_CIRCLE_O.create());
		ok.addClickListener(event ->{
			setVisible(false);
		});
		add(ok);
		getStyle().set("position", "fixed");
		getStyle().set("width", "100%");
		getStyle().set("height", "min-content");
		getStyle().set("bottom", "5%");
		getStyle().set("left", "0");
		getStyle().set("right", "0");
		getStyle().set("overflow-wrap", "break-word");
		getStyle().set("text-align", "center");
		getStyle().set("background", "hsla(214, 8%, 4%, 0.23)");
		
	}

}