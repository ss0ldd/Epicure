package ru.itis.epicure.services;

import ru.itis.epicure.dto.SignUpForm;

public interface SignUpService {
    public void addUser(SignUpForm SignUpForm);
    public boolean userExists(String email, String username);
}
