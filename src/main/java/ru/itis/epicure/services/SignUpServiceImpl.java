package ru.itis.epicure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.epicure.dto.SignUpForm;
import ru.itis.epicure.models.User;
import ru.itis.epicure.repository.UsersRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void addUser(SignUpForm signUpForm) {
        if (userExists(signUpForm.getEmail(), signUpForm.getUsername())) {
            throw new IllegalArgumentException("Пользователь с таким email или username уже существует");
        }

        User user = User.builder()
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .username(signUpForm.getUsername())
                .build();
        System.out.println("SignUpServiceImpl: " + user);
        usersRepository.save(user);
        System.out.println("usersRepository ok");
    }

    @Override
    public boolean userExists(String email, String username) {
        return usersRepository.findUserByEmail(email).isPresent() || usersRepository.findUserByUsername(username).isPresent();
    }
}