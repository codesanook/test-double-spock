package com.codesanook.example.test

import com.codesanook.example.Email
import com.codesanook.example.EmailClient
import com.codesanook.example.NotificationInputValidator
import com.codesanook.example.NotificationService
import spock.lang.Specification


class NotificationServiceRemoveHtmlTagSpec extends Specification {

    def "HTML string should be remove"() {

        given:
        def emailClient = new DummyEmailClient()
        def inputValidator = new DummyInputValidator()
        NotificationService notificationService =
                new NotificationService(emailClient, inputValidator)

        def htmlString = "<h1>hello</h1> <p>world</p>"

        when:
        def removedHtmlTag = notificationService.removeHtmlTag(htmlString)

        then:
        removedHtmlTag == "hello world"
    }
}


class DummyInputValidator extends NotificationInputValidator {

    @Override
    public boolean validateEmailInput(String from, String to, String subject, String body) {

        throw new IllegalStateException("should not be called");
    }
}

class DummyEmailClient implements EmailClient {

    @Override
    boolean sendEmail(Email email) {
        throw new IllegalStateException("should not be called");
    }
}


