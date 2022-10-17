package com.andreyenka.wimixtest.service;

import com.andreyenka.wimixtest.service.dto.UserDto;
import com.andreyenka.wimixtest.controller.UserResponse;

public interface UserService extends AbstractService<UserDto, Long>{
    public UserDto findByLogin(String login);

    public UserDto findByLoginAndPassword(String login, String password);

   UserResponse getAllUser(int pageNo, int pageSize, String sortBy, String sortDir);
}
