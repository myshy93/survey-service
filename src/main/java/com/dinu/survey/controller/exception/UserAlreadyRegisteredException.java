package com.dinu.survey.controller.exception;

public class UserAlreadyRegisteredException extends RuntimeException {
    public UserAlreadyRegisteredException(String username) {
        super("Username " + username + " already exists!");
    }
}
