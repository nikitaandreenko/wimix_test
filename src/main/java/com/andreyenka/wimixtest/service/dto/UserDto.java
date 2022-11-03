package com.andreyenka.wimixtest.service.dto;

import com.andreyenka.wimixtest.entity.user.Role;
import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
public class UserDto {
    private Long id;

    @NotNull(message = "login couldn't be empty")
    private String login;

    @NotNull(message = "password couldn't be empty")
    @Pattern( regexp = "(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{6,}$",
            message = "Password must contain at least one uppercase letter, one lowercase letter and at least 6 characters"
    )
    private String password;

    @NotNull(message = "first name couldn't be empty")
    private String firstName;

    @NotNull(message = "last name couldn't be empty")
    private String lastName;

    @NotNull(message = "age couldn't be empty")
    @Positive(message = "Age couldn't less zero")
    private int age;

    @NotNull(message = "Role couldn't be empty")
    private Role role;

}
