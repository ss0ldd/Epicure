package ru.itis.epicure.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.epicure.dto.SignInForm;

@Controller
public class SignInController {
    @GetMapping("/signIn")
    public String signIn(Model model) {
        model.addAttribute("signInForm", new SignInForm());
        return "sign_in_page";
    }
}