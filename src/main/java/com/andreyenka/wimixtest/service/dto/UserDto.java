package com.andreyenka.wimixtest.service.dto;

import com.andreyenka.wimixtest.entity.Role;
import lombok.Data;

@Data
public class UserDto {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private Role role;

}
