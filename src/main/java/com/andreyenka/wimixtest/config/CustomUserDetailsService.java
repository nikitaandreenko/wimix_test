package com.andreyenka.wimixtest.config;

import com.andreyenka.wimixtest.service.UserService;
import com.andreyenka.wimixtest.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {


    private final UserService userService;

    @Autowired
    @Lazy
    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDto user = userService.findByLogin(username);
        return CustomUserDetails.fromUserEntityToCustomUserDetails(user);
    }
}
