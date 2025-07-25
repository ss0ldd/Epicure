package ru.itis.epicure.services;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yaemikooo@yandex.ru");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        System.out.println(message);
        mailSender.send(message);
    }

}