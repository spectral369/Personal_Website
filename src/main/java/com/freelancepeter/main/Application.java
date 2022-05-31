package com.freelancepeter.main;

import java.io.PrintStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.vaadin.artur.helpers.LaunchUtil;

import com.freelancerpeter.data.AccountsInfo;
import com.freelancerpeter.workers.MastodonLoadingThread;
import com.freelancerpeter.workers.SteamLoadingThread;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Meta;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;

/**
 * The entry point of the Spring Boot application.
 */
@SpringBootApplication
@Theme(themeClass = Lumo.class, variant = Lumo.DARK)
@PWA(name = "Freelancing Peter", shortName = "Freelancing Peter", description = "Freelancingpeter - Personal Web Site", iconPath = "icons/icon.png", offlineResources = {
		"icons/icon.png" })
@Meta(name = "author", content = "spectral369")
@Meta(name = "title", content = "Freelancing Peter")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@EnableScheduling
//@Push(value = PushMode.AUTOMATIC, transport = Transport.WEBSOCKET) //disabled temporary
public class Application extends SpringBootServletInitializer implements AppShellConfigurator, ServletContextListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1831875954710899959L;
	
	ScheduledExecutorService executor = null;

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(Application.class);

		app.setBanner(new Banner() {

			@Override
			public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
				out.println("###########################################");
				out.println("#############FreelanceingPeter#############");
				out.println("####Created by (github.com/)spectral369####");
				out.println("###########################################");

			}
		});
		
		LaunchUtil.launchBrowserInDevelopmentMode(app.run(args));
	}
	@Async
	@Override
	public void contextInitialized(ServletContextEvent event) {
		
	AccountsInfo info =  new AccountsInfo();	
	System.out.println(info.getSTEAMKEY());
	System.out.println(info.getSTEAMID());
	
		  executor = Executors.newScheduledThreadPool(1);

		  executor.scheduleAtFixedRate(SteamLoadingThread.getInstance(info), 0, 3, TimeUnit.HOURS);
		  executor.scheduleAtFixedRate(MastodonLoadingThread.getInstance(info), 0, 3, TimeUnit.HOURS);
		 event.getServletContext().setAttribute("SCHEDULER", executor);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
	  executor.shutdownNow();
	 }

	@Override
	public void configurePage(AppShellSettings settings) {
		
		settings.addMetaTag("description", "Freelancingpeter - Personal Web Site");
		settings.addLink("start", "/");
		settings.addMetaTag("keywords", "freelancing, freelance, peter, freelancingpeter, peterfreelance, spectral369");
		settings.addMetaTag("og:title", "Freelancing Peter");
		settings.addMetaTag("og:type", "Development");
		settings.addMetaTag("og:url", "freelancingpeter.eu");
		settings.addMetaTag("og:image", "https://static.thenounproject.com/png/111266-200.png");
		settings.addLink("canonical", "https://freelancingpeter.eu");
		settings.addMetaTag("Access-Control-Allow-Origin", "https://counter.dev/");
		settings.addMetaTag("Access-Control-Allow-Origin", "https://counter.dev/*");
		settings.addMetaTag("Access-Control-Allow-Origin", "https://freelancingpeter.eu/");
		settings.addMetaTag("Access-Control-Allow-Credentials", "true");
		settings.addMetaTag("Access-Control-Allow-Headers", "Content-Type");
		settings.addMetaTag("Access-Control-Allow-Methods", "GET, DELETE, HEAD, OPTIONS");
		settings.addMetaTag("google-site-verification", "tWuh1W7Qk3k_bJE20N7myp_DvjfcxroYbbNvX-JAKpw");
		//UI.getCurrent().getPushConfiguration().setPushUrl("https://freelancingpeter.eu");
	}

}
