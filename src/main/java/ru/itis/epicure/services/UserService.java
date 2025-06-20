package ru.itis.epicure.services;

import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.epicure.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto getUserByUsername(String username);
}
