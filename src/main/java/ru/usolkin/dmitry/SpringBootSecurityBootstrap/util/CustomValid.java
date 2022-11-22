package ru.usolkin.dmitry.SpringBootSecurityBootstrap.util;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.models.User;
import ru.usolkin.dmitry.SpringBootSecurityBootstrap.service.CustomUserDetService;


@Component
public class CustomValid implements Validator {
    private  final CustomUserDetService userDetService;

    public CustomValid(CustomUserDetService userDetService) {
        this.userDetService = userDetService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        try {
            userDetService.loadUserByUsername(user.getUsername());
        } catch (UsernameNotFoundException ignored) {
            return;
        }
        errors.rejectValue("username","","Человек с таким именем пользователя уже существует");
    }
}
