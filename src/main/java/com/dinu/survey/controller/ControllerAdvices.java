package com.dinu.survey.controller;

import com.dinu.survey.controller.exception.SurveyAlreadyClosedException;
import com.dinu.survey.controller.exception.SurveyAlreadyOpenException;
import com.dinu.survey.controller.exception.SurveyNotFoundException;
import com.dinu.survey.controller.exception.UserAlreadyRegisteredException;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdvices {
    @ResponseBody
    @ExceptionHandler(UserAlreadyRegisteredException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String userAlreadyRegisteredHandler(@NotNull UserAlreadyRegisteredException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(SurveyNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String surveyNotFoundHandler(@NotNull SurveyNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({SurveyAlreadyOpenException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    String surveyAlreadyOpenHandler(@NotNull SurveyAlreadyOpenException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler({SurveyAlreadyClosedException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    String surveyAlreadyCloseHandler(@NotNull SurveyAlreadyClosedException ex) {
        return ex.getMessage();
    }
}
