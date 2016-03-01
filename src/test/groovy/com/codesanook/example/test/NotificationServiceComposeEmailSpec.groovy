package com.codesanook.example.test

import com.codesanook.example.Email
import com.codesanook.example.EmailClient
import com.codesanook.example.NotificationInputValidator
import com.codesanook.example.NotificationService
import spock.lang.Specification


class NotificationServiceComposeEmailSpec extends Specification {

    def "valid email input, valid email object should return"() {

        def emailClient = new DummyEmailClient()
        def inputValidator = Stub(NotificationInputValidator);
        inputValidator.validateEmailInput(_, _, _, _) >> true;

        NotificationService notificationService =
                new NotificationService(emailClient, inputValidator)

        def from = "abc@mail.com"
        def to = "xyz@mail.com"
        def subject = "Hello"
        def body = "Hello World"
        Email email = notificationService.composeEmail(from, to, subject, body)

        expect:
        email.from == from
        email.to == to
        email.subject == subject
        email.body == body

    }


    def "validateEmailInput called once"() {
        given:
        EmailClient emailClient = new DummyEmailClient()
        NotificationInputValidator inputValidator = Spy()

        NotificationService notificationService = new NotificationService(emailClient, inputValidator)


        def from = "abc@mail.com"
        def to = "xyz@mail.com"
        def subject = "Hello"
        def body = "Hello World"
        when:
        notificationService.composeEmail(from, to, subject, body)

        then:
        1 * inputValidator.validateEmailInput(_, _, _, _)
    }


}

