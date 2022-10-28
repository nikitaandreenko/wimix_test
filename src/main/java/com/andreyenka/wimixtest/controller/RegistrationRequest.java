package com.andreyenka.wimixtest.controller;

import com.andreyenka.wimixtest.entity.user.Role;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegistrationRequest {

    @NotEmpty
    private String login;

    @NotEmpty
    private String password;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    private int age;

    private Role role;

}
