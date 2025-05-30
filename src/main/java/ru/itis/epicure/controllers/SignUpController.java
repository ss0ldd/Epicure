package ru.itis.epicure.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.epicure.dto.SignUpForm;
import ru.itis.epicure.services.SignUpService;

@Controller
public class SignUpController {
    @Autowired
    private SignUpService signUpService;

    @GetMapping("/signUp")
    public String getSignUpPage() {
        System.out.println("ok1");
        return "sign_up_page";
    }

    @PostMapping("/signUp")
    public String signUp(SignUpForm form, Model model){
        if (signUpService.userExists(form.getEmail(), form.getUsername())){
            model.addAttribute("error", "Пользователь с такой почтой или именем уже существует.");
            return "sign_up_page";
        }
        System.out.println("SignUpController: " + form);
        signUpService.addUser(form);
        return "redirect:/signUp";
    }

}
