package com.andreyenka.wimixtest.controller;

import com.andreyenka.wimixtest.config.jwt.JwtProvider;
import com.andreyenka.wimixtest.service.UserService;
import com.andreyenka.wimixtest.service.impl.UserServiceImpl;
import com.andreyenka.wimixtest.service.dto.UserDto;
import com.andreyenka.wimixtest.util.ConstantsForPagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final JwtProvider jwtProvider;

    @Autowired
    public UserController(UserServiceImpl userService, JwtProvider jwtProvider) {
        this.userService = userService;
        this.jwtProvider = jwtProvider;
    }

    @GetMapping
    public UserResponse getAllUsers(
            @RequestParam(value = "pageNo", defaultValue = ConstantsForPagination.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = ConstantsForPagination.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = ConstantsForPagination.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = ConstantsForPagination.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return userService.getAllUser(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("/register")
    public UserDto registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserDto userDto = new UserDto();
        userDto.setPassword(registrationRequest.getPassword());
        userDto.setLogin(registrationRequest.getLogin());
        userDto.setFirstName(registrationRequest.getFirstName());
        userDto.setLastName(registrationRequest.getLastName());
        userDto.setAge(registrationRequest.getAge());
        userService.create(userDto);
        return userDto;
    }

    @PutMapping
    public UserDto updateUser(@RequestBody UserDto user) {
        return userService.update(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "User with id = " + id + " was deleted";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        UserDto user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(user.getLogin());
        return new AuthResponse(token);
    }
}
