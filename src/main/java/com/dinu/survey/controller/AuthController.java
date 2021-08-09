package com.dinu.survey.controller;

import com.dinu.survey.entity.AppUser;
import com.dinu.survey.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    String registerUser(@Validated @RequestBody AppUser user) {
        return authService.register(user);
    }
}