package com.andreyenka.wimixtest.service.dto;

import com.andreyenka.wimixtest.entity.user.Role;
import lombok.*;

@Data
public class UserDto {
    private Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private int age;
    private Role role;

}
