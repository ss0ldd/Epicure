package ru.itis.epicure.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.epicure.models.Role;

@Component
public class StringToRoleConverter implements Converter<String, Role> {
    
    @Override
    public Role convert(String source) {
        try {
            return source != null ? Role.valueOf(source.toUpperCase()) : null;
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
} 