package ru.itis.epicure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.epicure.dto.UserDto;
import ru.itis.epicure.exceptions.UserNotFoundException;
import ru.itis.epicure.models.User;
import ru.itis.epicure.repository.UsersRepository;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return UserDto.from(usersRepository.findAll());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return UserDto.from(user);
    }

    @Override
    public UserDto getUserByUsername(String username){
        User user = usersRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserDto.from(user);
    }
}
