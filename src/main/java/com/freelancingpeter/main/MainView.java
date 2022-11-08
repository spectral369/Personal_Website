package com.freelancingpeter.main;

import com.freelancingpeter.footer.WFooter;
import com.freelancingpeter.pages.Home;
import com.freelancingpeter.pages.CV;
import com.freelancingpeter.pages.About;
import com.freelancingpeter.pages.Github;
import com.freelancingpeter.pages.Contact;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.contextmenu.ContextMenu;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.StreamResource;

@Route("")
@PageTitle("F.P Home")
@CssImport("./themes/freelancingpeter/styles.css")
public class MainView extends VerticalLayout implements RouterLayout {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3000114871759485781L;

	VerticalLayout root = null;
	VerticalLayout header = null;

	About aboutContent = null;
	Home homeContent = null;
	WFooter footerContent = null;
	Github githubContent = null;
	CV CVContent = null;
	Contact contactContent = null;

	Button overflow = null;
	ContextMenu overflowcontext = null;
	Button Github = null;
	Button CV = null;
	Button Contact = null;
	Button About = null;
	ComponentEventListener<ClickEvent<MenuItem>> listener = null;

	public MainView() {

		root = new VerticalLayout();

		header = new VerticalLayout();
		HorizontalLayout HItems = new HorizontalLayout();

		overflowcontext = new ContextMenu();

		listener = e -> {

			root.remove(root.getComponentAt(1));
			switch (e.getSource().getText()) {
			case "Github": {
				if (githubContent == null) {
					githubContent = new Github();
				}

				root.addComponentAtIndex(1, githubContent);
				break;
			}
			case "Resume": {
				if (CVContent == null) {
					CVContent = new CV();
				}

				root.addComponentAtIndex(1, CVContent);
				break;
			}
			case "Home": {

				root.addComponentAtIndex(1, homeContent);
				break;
			}
			case "Contact": {
				if (contactContent == null) {
					contactContent = new Contact();
				}

				root.addComponentAtIndex(1, contactContent);
				break;
			}
			case "About": {
				if (aboutContent == null) {
					aboutContent = new About();

				}
				root.addComponentAtIndex(1, aboutContent);
				break;

			}

			default:
				root.addComponentAtIndex(1, homeContent);
			}
		};
		overflow = new Button(new Icon(VaadinIcon.ELLIPSIS_DOTS_V));
		overflow.getStyle().set("display", "none");

		overflowcontext.setTarget(overflow);
		overflowcontext.setOpenOnClick(true);

		Github = new Button("GitHub");

		StreamResource imageResource = new StreamResource("githubIcon.png",
				() -> getClass().getResourceAsStream("/META-INF/resources/icons/githubIcon.png"));
		Image githubIcon = new Image(imageResource, "Github");
		githubIcon.getStyle().set("vertical-align", "middle");
		githubIcon.getStyle().set("width", "66%");
		Github.setIcon(githubIcon);

		Github.addClassName("MenuButton");
		Github.getStyle().set("padding-right", "0.65em");
		Github.addClickListener(e -> {
			if (githubContent == null) {
				githubContent = new Github();
			}
			root.remove(root.getComponentAt(1));
			root.addComponentAtIndex(1, githubContent);
		});

		CV = new Button("Resume");
		CV.addClassName("MenuButton");
		CV.setIcon(VaadinIcon.DIPLOMA_SCROLL.create());
		CV.addClickListener(e -> {
			if (CVContent == null) {
				CVContent = new CV();
			}
			root.remove(root.getComponentAt(1));
			root.addComponentAtIndex(1, CVContent);
		});

		Button Home = new Button("Home");
		Home.setIcon(VaadinIcon.HOME_O.create());
		Home.addClassName("MenuButton");
		Home.addClassName("HomeButton1");
		Home.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
		Home.addClickListener(e -> {
			root.remove(root.getComponentAt(1));
			root.addComponentAtIndex(1, homeContent);
		});

		Contact = new Button("Contact");
		Contact.setIcon(VaadinIcon.CONNECT.create());
		Contact.addClickListener(e -> {
			if (contactContent == null) {
				contactContent = new Contact();
			}
			root.remove(root.getComponentAt(1));
			root.addComponentAtIndex(1, contactContent);
		});

		Contact.getStyle().set("padding-right", "0.65em");
		Contact.addClassName("MenuButton");

		About = new Button("About");
		About.setIcon(VaadinIcon.EXCLAMATION.create());
		About.addClassName("MenuButton");
		About.addClickListener(e -> {

			if (aboutContent == null) {
				aboutContent = new About();

			}
			root.remove(root.getComponentAt(1));
			root.addComponentAtIndex(1, aboutContent);

		});

		HItems.add(Github, CV, Home, Contact, About, overflow);
		UI.getCurrent().getPage().retrieveExtendedClientDetails(details -> {
			resize(details.getScreenWidth());
		});

		UI.getCurrent().getPage().addBrowserWindowResizeListener(e -> {

			overflow.getStyle().clear();
			if (e.getWidth() > 600) {
				Github.getStyle().clear();
				CV.getStyle().clear();
				Contact.getStyle().clear();
				About.getStyle().clear();
				overflow.getStyle().set("display", "none");
				overflowcontext.removeAll();
			} else if (e.getWidth() < 600 && e.getWidth() > 450) {
				overflowcontext.removeAll();
				Github.getStyle().clear();
				CV.getStyle().clear();
				Contact.getStyle().clear();
				About.getStyle().clear();
				overflowcontext.addItem("Github", listener);
				overflowcontext.addItem("About", listener);
				Github.getStyle().set("display", "none");
				About.getStyle().set("display", "none");
			} else if (e.getWidth() < 450) {
				overflowcontext.removeAll();
				Github.getStyle().clear();
				CV.getStyle().clear();
				Contact.getStyle().clear();
				About.getStyle().clear();
				overflowcontext.addItem("Github", listener);
				overflowcontext.addItem("Resume", listener);
				overflowcontext.addItem("Contact", listener);
				overflowcontext.addItem("About", listener);
				Github.getStyle().set("display", "none");
				CV.getStyle().set("display", "none");
				Contact.getStyle().set("display", "none");
				About.getStyle().set("display", "none");
			}

		});

		HItems.setSpacing(true);
		HItems.setAlignItems(Alignment.CENTER);

		header.add(HItems);
		header.setAlignItems(Alignment.CENTER);

		homeContent = new Home();

		footerContent = new WFooter();

		root.add(header);
		root.add(homeContent);
		root.add(footerContent);

		root.setSizeFull();
		root.setMargin(false);
		root.setPadding(false);
		// cookie consent
		add(new CookieConsent());
		add(root);
		setHorizontalComponentAlignment(Alignment.CENTER, root);
		setMargin(false);
		setPadding(false);
		setSpacing(false);
		setSizeFull();

	}

	private void resize(int width) {
		overflow.getStyle().clear();
		if (width > 700) {
			Github.getStyle().clear();
			CV.getStyle().clear();
			Contact.getStyle().clear();
			About.getStyle().clear();
			overflow.getStyle().set("display", "none");
			overflowcontext.removeAll();
		} else if (width < 700 && width > 450) {
			overflowcontext.removeAll();
			Github.getStyle().clear();
			CV.getStyle().clear();
			Contact.getStyle().clear();
			About.getStyle().clear();
			overflowcontext.addItem("Github", listener);
			overflowcontext.addItem("About", listener);
			Github.getStyle().set("display", "none");
			About.getStyle().set("display", "none");
		} else if (width < 450) {
			overflowcontext.removeAll();
			Github.getStyle().clear();
			CV.getStyle().clear();
			Contact.getStyle().clear();
			About.getStyle().clear();
			overflowcontext.addItem("Github", listener);
			overflowcontext.addItem("Resume", listener);
			overflowcontext.addItem("Contact", listener);
			overflowcontext.addItem("About", listener);
			Github.getStyle().set("display", "none");
			CV.getStyle().set("display", "none");
			Contact.getStyle().set("display", "none");
			About.getStyle().set("display", "none");
		}

	}

}