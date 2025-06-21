package ru.itis.epicure.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.epicure.dto.UserDto;
import ru.itis.epicure.models.User;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {
    
    @Override
    public UserDto convert(User user) {
        if (user == null) {
            return null;
        }
        
        return UserDto.builder()
                .userId(user.getUserId())
                .userName(user.getUsername())
                .email(user.getEmail())
                .build();
    }
} 