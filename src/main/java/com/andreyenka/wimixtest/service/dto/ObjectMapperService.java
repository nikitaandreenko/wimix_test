package com.andreyenka.wimixtest.service.dto;

import com.andreyenka.wimixtest.entity.User;
import org.springframework.stereotype.Component;

@Component
public class ObjectMapperService {
    public UserDto toDto(User entity) {
        UserDto dto = new UserDto();
        dto.setLogin(entity.getLogin());
        dto.setPassword(entity.getPassword());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setAge(entity.getAge());
        dto.setRole(entity.getRole());
        return dto;
    }

    public User toEntity(UserDto dto) {
        User entity = new User();
        entity.setLogin(dto.getLogin());
        entity.setPassword(dto.getPassword());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setAge(dto.getAge());
        entity.setRole(dto.getRole());
        return entity;
    }


}
