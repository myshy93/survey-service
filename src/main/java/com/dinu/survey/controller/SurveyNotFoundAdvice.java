package com.dinu.survey.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SurveyNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(SurveyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String surveyNotFoundHandler(SurveyNotFoundException ex) {
        return ex.getMessage();
    }
}
