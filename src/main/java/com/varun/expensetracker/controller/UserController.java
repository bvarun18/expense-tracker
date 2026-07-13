package com.varun.expensetracker.controller;

import com.varun.expensetracker.dto.LoginRequest;
import com.varun.expensetracker.dto.LoginResponse;
import com.varun.expensetracker.entity.User;
import com.varun.expensetracker.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public String registerUser(@Valid @RequestBody User user) {
        userService.registerUser(user);
        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return userService.login(request);
    }
}