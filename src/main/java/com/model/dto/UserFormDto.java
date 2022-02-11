package com.model.dto;

import com.model.entity.Role;
import com.model.entity.User;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
public class UserFormDto {
    @NotEmpty(message = "Login should not be empty")
    @Size(min = 2, max = 30, message = "Login should be between 2 and 30 characters")
    private String login;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 2, message = "Password should be longer then 2 characters")
    private String password;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 2, message = "Password should be longer then 2 characters")
    private String passwordRepeat;

    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Email should be valid")
    private String email;

    @NotEmpty(message = "First Name should not be empty")
    private String firstName;

    @NotEmpty(message = "Last Name should not be empty")
    private String lastName;

    @DateTimeFormat
    private String birthday;

    private String role;

    @SneakyThrows
    public static User userFormDtoToUser(UserFormDto userFormDto) {
        User user = new User();
        user.setLogin(userFormDto.getLogin());
        user.setPassword(userFormDto.getPassword());
        user.setEmail(userFormDto.getEmail());
        user.setFirstName(userFormDto.getFirstName());
        user.setLastName(userFormDto.getLastName());
        user.setBirthday(Date.valueOf(userFormDto.getBirthday()));
        Role role = new Role();
        role.setId(Long.valueOf(userFormDto.getRole()));
        user.setRole(role);
        return user;
    }

    public static UserFormDto userToUserFormDto(User user) {
        UserFormDto userFormDto = new UserFormDto();
        userFormDto.setLogin(user.getLogin());
        userFormDto.setPassword(user.getPassword());
        userFormDto.setPasswordRepeat(user.getPassword());
        userFormDto.setEmail(user.getEmail());
        userFormDto.setFirstName(user.getFirstName());
        userFormDto.setLastName(user.getLastName());
        userFormDto.setBirthday(user.getBirthday().toString());
        userFormDto.setRole(user.getRole().getId().toString());
        return userFormDto;
    }
}
