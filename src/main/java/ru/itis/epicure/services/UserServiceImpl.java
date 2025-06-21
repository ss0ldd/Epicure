package ru.itis.epicure.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.itis.epicure.dto.UserDto;
import ru.itis.epicure.exceptions.UserNotFoundException;
import ru.itis.epicure.models.User;
import ru.itis.epicure.repository.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersRepository usersRepository;
    
    @Autowired
    private ConversionService conversionService;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = usersRepository.findAll();
        return users.stream()
                .map(user -> conversionService.convert(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        return conversionService.convert(user, UserDto.class);
    }

    @Override
    public UserDto getUserByUsername(String username){
        User user = usersRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return conversionService.convert(user, UserDto.class);
    }
}
