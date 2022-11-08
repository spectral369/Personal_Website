package com.freelancingpeter.utils;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;

@Tag("iframe")
public class CVEmbedded extends Component {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4885200401183448897L;

	public CVEmbedded() {
		getElement().setAttribute("scrolling", "auto");
		getElement().setAttribute("type", "application/pdf");
		getElement().setAttribute("src", "../webSecure.pdf");
		getElement().setAttribute("width", "100%");
		getElement().setAttribute("height", "100%");

	}

}