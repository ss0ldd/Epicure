package ru.itis.epicure.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.epicure.models.Role;

@Component
public class RoleToStringConverter implements Converter<Role, String> {
    
    @Override
    public String convert(Role role) {
        return role != null ? role.name() : null;
    }
} 