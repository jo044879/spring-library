package spring.library.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring.library.dto.userDto.UserCreateRequest;
import spring.library.dto.userDto.UserResponse;
import spring.library.dto.userDto.UserUpdateRequest;
import spring.library.service.UserService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody UserCreateRequest userCreateRequest) {
        return userService.userCreat(userCreateRequest);
    }

    @GetMapping
    public List<UserResponse> readUser() {
        return userService.userRead();
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable Long id) {
        return userService.userUpdate(userUpdateRequest, id);
    }

    @DeleteMapping("/{id}")
    public UserResponse deleteUser(@PathVariable Long id) {
        return userService.userDelete(id);
    }

}
