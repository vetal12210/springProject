package com.util;

import com.model.dto.UserFormDto;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class UserValidator implements Validator {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserFormDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserFormDto userFormDto = (UserFormDto) target;
        if (userService.existsByLogin(userFormDto.getLogin())) {
            errors.rejectValue("login", "", "This login is already in use");
        }
        if (!userFormDto.getPassword().equals(userFormDto.getPasswordRepeat())) {
            errors.rejectValue("passwordRepeat", "", "Passwords do not match");
        }
        if (userService.existsByLogin(userFormDto.getLogin())) {
            if (!userService.findByLogin(userFormDto.getLogin()).getEmail().equals(userFormDto.getEmail())
                    && userService.existsByEmail(userFormDto.getEmail())) {
                errors.rejectValue("email", "", "This email is already in use");
            }
        } else {
            if (userService.existsByEmail(userFormDto.getEmail())) {
                errors.rejectValue("email", "", "This email is already in use");
            }
        }
        if (Date.valueOf(userFormDto.getBirthday()).after(Date.valueOf(LocalDate.now()))) {
            errors.rejectValue("birthday", "", "Birthday could not be in the future");
        }
    }
}
