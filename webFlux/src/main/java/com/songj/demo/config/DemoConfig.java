package com.songj.demo.config;

import com.songj.demo.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

//@Configuration
public class DemoConfig implements WebFluxConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry. addConverter(str2User());
    }

    public Converter<User, User> str2User(){
        Converter<User, User> converter = (User s) -> {
            User user = new User();
            user.setAge(12);
            user.setName("hh");
            return user;
        };
        return converter;
    }
}
