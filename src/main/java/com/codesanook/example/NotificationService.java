package com.codesanook.example;

public class NotificationService {

    private EmailClient emailClient;
    private NotificationInputValidator validator;

    public NotificationService(EmailClient emailClient,
                               NotificationInputValidator validator) {
        this.emailClient = emailClient;
        this.validator = validator;
    }

    public String removeHtmlTag(String input) {
        return input.replaceAll("<[^>]*>", "");
    }

    public Email composeEmail(String from, String to, String subject, String body) {
        if (!validator.validateEmailInput(from, to, subject, body)) {
            throw new IllegalStateException("invalid email input");
        }

        Email email = new Email();
        email.setFrom(from);
        email.setTo(to);
        email.setSubject(subject);
        email.setBody(body);
        return email;
    }


    public boolean notifyByEmail(String from, String to, String subject, String body) {
        subject = removeHtmlTag(subject);
        body = removeHtmlTag(body);
        Email email = composeEmail(from, to, subject, body);
        return emailClient.sendEmail(email);
    }


}
