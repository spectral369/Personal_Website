package com.freelancingpeter.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail2 {
	String host = "localhost";
	String from = "root@freelancingpeter.eu";

	public Mail2(String name, String email, String amessage) {

		try {
			Properties props = System.getProperties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", from);
			props.put("mail.debug", "true");

			Session session = Session.getDefaultInstance(props, null);
			session.setDebug(true);
			Transport transport = session.getTransport("smtp");

			MimeMessage message = new MimeMessage(session);
			Address fromAddress = new InternetAddress("root@freelancingpeter.eu");

			message.setFrom(fromAddress);

			InternetAddress to = new InternetAddress("admin@freelancingpeter.eu");
			message.addRecipient(Message.RecipientType.TO, to);

			message.setSubject("E-mail from " + name);
			message.setText("E-mail user " + name + " \n User E-mail: " + email + " \n Message: " + amessage);

			transport.connect(host, from);
			message.saveChanges();
			Transport.send(message);
			transport.close();
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
}