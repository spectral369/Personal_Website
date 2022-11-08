package com.freelancingpeter.footer;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementFactory;

@CssImport("./themes/freelancingpeter/styles.css")
public class Info extends Div{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1494522269481636520L;
	
	
	
	public Info() {
		addClassName("dropup");
		
		Element button =  ElementFactory.createButton("visits");
		button.getThemeList().add(ButtonVariant.LUMO_TERTIARY.toString());
		button.getClassList().add("dropbtn");
		
		Element div =  ElementFactory.createDiv();
		div.getClassList().add("dropup-content");
		Element a1 = ElementFactory.createAnchor("#","Info 1");
		Element a2 = ElementFactory.createAnchor("#","Info 2");
		Element a3 = ElementFactory.createAnchor("#","Info 3");
		div.appendChild(a1,a2,a3);
		
		getElement().appendChild(button,div);
		
		
	}

}