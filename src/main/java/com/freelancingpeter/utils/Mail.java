package com.freelancingpeter.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class Mail {

	public Mail(String name, String aemail, String amessage) {
		Mailer mailer = MailerBuilder.withSMTPServer("localhost", 25).withProperty("mail.smtp.sendpartial", "true")
				.withProperty("mail.smtp.starttls.enable", "true").withTransportStrategy(TransportStrategy.SMTP_TLS)
				.buildMailer();

		Email email = EmailBuilder.startingBlank().from("Mail1 from " + name, "admin@freelancingpeter.eu")
				.to("admin", "admin@freelancingpeter.eu")
				.withSubject("mail1 from " + name + " " + new SimpleDateFormat("MM-dd-yyyy").format(new Date()))
				.withHTMLText("message: " + amessage + aemail).buildEmail();

		/*
		 * Email email = EmailBuilder.startingBlank().from("Mail1 from " + name,
		 * "admin@freelancingpeter.eu") .withRecipient("admin@freelancingpeter.eu",
		 * RecipientType.TO) .withSubject("mail1 from " + name + " " + new
		 * SimpleDateFormat("MM-dd-yyyy").format(new Date())) .withHTMLText("message " +
		 * amessage).buildEmail();
		 */

		mailer.sendMail(email);

	}

}