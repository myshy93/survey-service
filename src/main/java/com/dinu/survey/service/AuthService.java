package com.dinu.survey.service;


import com.dinu.survey.controller.exception.UserAlreadyRegisteredException;
import com.dinu.survey.entity.AppUser;
import com.dinu.survey.repository.UserRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public String register(@NotNull AppUser user) {
        //check if user exists in database
        if (repository.findByUsername(user.getUsername()) != null)
            throw new UserAlreadyRegisteredException(user.getUsername());
        // encode user password before saving it in DB
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return "User " + user.getUsername() + " added.";
    }
}
