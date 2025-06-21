package ru.itis.epicure.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.epicure.dto.UserDto;
import ru.itis.epicure.models.User;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {
    
    @Override
    public User convert(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        
        return User.builder()
                .userId(userDto.getUserId())
                .username(userDto.getUserName())
                .email(userDto.getEmail())
                .build();
    }
} 