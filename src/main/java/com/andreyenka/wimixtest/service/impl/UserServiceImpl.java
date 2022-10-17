package com.andreyenka.wimixtest.service.impl;

import com.andreyenka.wimixtest.entity.Role;
import com.andreyenka.wimixtest.entity.User;
import com.andreyenka.wimixtest.repository.RoleRepository;
import com.andreyenka.wimixtest.repository.UserRepository;
import com.andreyenka.wimixtest.service.UserService;
import com.andreyenka.wimixtest.service.dto.ObjectMapperService;
import com.andreyenka.wimixtest.service.dto.UserDto;
import com.andreyenka.wimixtest.service.exception.NoSuchEntityException;
import com.andreyenka.wimixtest.service.exception.ValidateException;
import com.andreyenka.wimixtest.controller.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;


    private final RoleRepository roleRepository;


    private final PasswordEncoder passwordEncoder;

    private final ObjectMapperService mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, ObjectMapperService mapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
    }

    @Override
    public UserDto findByLogin(String login) {
        log.debug("Get user by login={} from database users", login);
        User user = userRepository.findByLogin(login);
        return mapper.toDto(user);
    }

    @Override
    public UserDto findByLoginAndPassword(String login, String password) {
        log.debug("Get user by login={} and password={} from database users", login, password);
        UserDto user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public UserResponse getAllUser(int pageNo, int pageSize, String sortBy, String sortDir) {
        log.debug("Get all users from database");
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<User> users = userRepository.findAll(pageable);
        List<User> listOfUsers = users.getContent();
        List<UserDto> content = listOfUsers.stream().map(mapper::toDto).collect(Collectors.toList());
        UserResponse userResponse = new UserResponse();
        userResponse.setUsers(content);
        userResponse.setPageNo(users.getNumber());
        userResponse.setPageSize(users.getSize());
        userResponse.setTotalElements(users.getTotalElements());
        userResponse.setTotalPages(users.getTotalPages());
        userResponse.setLast(users.isLast());
        return userResponse;
    }

    @Override
    public UserDto create(UserDto user) {
        log.debug("Create user={} in database user", user);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        validate(user);
        User userCreated = mapper.toEntity(user);
        userRepository.save(userCreated);
        return mapper.toDto(userCreated);
    }

    @Override
    public UserDto findById(Long id) {
        log.debug("Get user by id={} from database users", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("User with id: " + id + " wasn't found"));
        return mapper.toDto(user);

    }

    @Override
    public List<UserDto> findAll() {
        log.debug("Get all users from database users");
        return userRepository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto update(UserDto user) {
        log.debug("Update user={} in database users", user);
        validate(user);
        User userUpdated = mapper.toEntity(user);
        userRepository.save(userUpdated);
        return mapper.toDto(userUpdated);
    }

    @Override
    public void delete(Long id) {
        log.debug("Delete user by id={} in database users", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchEntityException("User with id: " + id + " wasn't found"));
        userRepository.deleteById(id);
    }

    private void validate(UserDto user) {
        if (user == null) {
            throw new ValidateException("Users can't be empty...");
        }
        if (user.getAge() <= 0) {
            throw new ValidateException("Age is not valid. Age can't be less 0");
        }
    }


}
