package ru.itis.epicure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import ru.itis.epicure.converters.*;

@Configuration
public class ConverterConfig {
    
    @Bean
    public ConversionService conversionService() {
        DefaultConversionService conversionService = new DefaultConversionService();
        
        // Регистрируем наши конвертеры
        conversionService.addConverter(new RoleToStringConverter());
        conversionService.addConverter(new StringToRoleConverter());
        conversionService.addConverter(new UserToUserDtoConverter());
        conversionService.addConverter(new UserDtoToUserConverter());
        conversionService.addConverter(new PostToPostDtoConverter());
        
        return conversionService;
    }
} 