package ru.bryanin.dev.questionnairebackend.office.email;


public interface EmailService {

    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
}
