package com.dinu.survey.controller;

import com.dinu.survey.controller.exception.UserAlreadyRegisteredException;
import com.dinu.survey.entity.AppUser;
import com.dinu.survey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    String registerUser(@Validated @RequestBody AppUser user) {
        //check if user exists in database
        if (repository.findByUsername(user.getUsername()) != null)
            throw new UserAlreadyRegisteredException(user.getUsername());
                    // encode user password before saving it in DB
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(user);
        return "User " + user.getUsername() + " added.";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    /*
    Exception handler that send back in response what field in not valid in case of bad request
     */
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
