package com.carlostorresdevjava.challenge.ceiba.funds.api.controller;

import com.carlostorresdevjava.challenge.ceiba.funds.api.dto.LoginRequest;
import com.carlostorresdevjava.challenge.ceiba.funds.api.model.User;
import com.carlostorresdevjava.challenge.ceiba.funds.api.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Crear un nuevo usuario
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Obtener usuario por ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    // Login de usuario
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        return userService.login(request.getEmail(), request.getPassword());
    }
}