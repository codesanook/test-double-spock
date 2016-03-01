package com.codesanook.example;


public class NotificationInputValidator {

    public boolean validateEmailInput(String from, String to, String subject, String body) {
        String empty = "";
        if (from == null) return false;
        if (to == null || to.equals(empty)) return false;
        if (subject == null || subject.equals(empty)) return false;
        if (body == null || body.equals(empty)) return false;

        return true;
    }
}
