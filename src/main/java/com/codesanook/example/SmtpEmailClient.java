package com.codesanook.example;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SmtpEmailClient {
    public boolean sendEmail(Email email) {
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "localhost");
        Session session = Session.getDefaultInstance(properties);

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(email.getFrom()));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(email.getTo()));
            message.setSubject(email.getSubject());
            message.setText(email.getBody());
            Transport.send(message);

            return true;
        } catch (MessagingException ex) {
            ex.printStackTrace();
            return false;
        }

    }
}
