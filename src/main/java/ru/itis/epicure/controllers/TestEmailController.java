package ru.itis.epicure.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.epicure.services.EmailService;

@RestController
@RequiredArgsConstructor
public class TestEmailController {

    private final EmailService emailService;

    @GetMapping("/test-email")
    public String sendTestEmail() {
        emailService.sendEmail(
                "yaemikooo@yandex.ru",
                "Тестовое письмо",
                "Привет! Это тестовое письмо из Epicure."
        );
        return "Письмо отправлено!";
    }
}