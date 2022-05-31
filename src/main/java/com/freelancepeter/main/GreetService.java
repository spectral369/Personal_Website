package com.freelancepeter.main;

import java.io.Serializable;

import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.apache.tomcat.util.http.SameSiteCookies;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class GreetService implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7067592689914905048L;

	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
		return (factory) -> factory.addContextCustomizers((context) -> {
			LegacyCookieProcessor legacyCookieProcessor = new LegacyCookieProcessor();
			legacyCookieProcessor.setSameSiteCookies(SameSiteCookies.NONE.getValue());
			context.setCookieProcessor(legacyCookieProcessor);
		});
	}

}
