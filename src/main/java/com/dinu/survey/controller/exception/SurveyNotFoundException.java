package com.dinu.survey.controller.exception;

public class SurveyNotFoundException extends RuntimeException{
    public SurveyNotFoundException(Long id) {
        super("Could not find survey " + id);
    }
}
