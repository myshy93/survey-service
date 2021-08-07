package com.dinu.survey.controller;

public class SurveyNotFoundException extends RuntimeException{
    public SurveyNotFoundException(Long id) {
        super("Could not find survey " + id);
    }
}
