package com.codesanook.example.test

import com.codesanook.example.Email
import com.codesanook.example.EmailClient
import com.codesanook.example.NotificationInputValidator
import com.codesanook.example.NotificationService
import spock.lang.Specification


class NotificationServiceSendEmailSpec extends Specification {

    def "valid email input, sendEmail called once"() {
        given:
        EmailClient emailClient = Mock()
        NotificationInputValidator inputValidator = Stub()
        inputValidator.validateEmailInput(_, _, _, _) >> true

        NotificationService notificationService =
                new NotificationService(emailClient, inputValidator)

        def from = "abc@mail.com"
        def to = "xyz@mail.com"
        def subject = "Hello"
        def body = "Hello World"

        when:
        notificationService.notifyByEmail(from, to, subject, body)

        then:
        1 * emailClient.sendEmail({ Email email ->
            email.from == from
            email.to == to
            email.subject == subject
            email.body == body
        })
    }


    def "valid email, send email return true"() {
        given:
        EmailClient emailClient = new FakeEmailClient();
        NotificationInputValidator inputValidator = Stub()
        inputValidator.validateEmailInput(_, _, _, _) >> true

        NotificationService notificationService =
                new NotificationService(emailClient, inputValidator)

        def from = "abc@mail.com"
        def to = "xyz@mail.com"
        def subject = "Hello"
        def body = "Hello World"

        when:
        def sentResult = notificationService.notifyByEmail(from, to, subject, body)

        then:
        sentResult == true
    }


    class FakeEmailClient implements EmailClient {


        @Override
        boolean sendEmail(Email email) {
            println "sent email from ${email.from} to ${email.to}\n" +
                    "subject ${email.subject} body ${email.body}";
            return true;
        }
    }

}

