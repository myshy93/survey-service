package com.dinu.survey.controller;

import com.dinu.survey.controller.exception.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

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

    @ResponseBody
    @ExceptionHandler({SurveyNotOpenException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    String surveyNotOpenHandler(@NotNull SurveyNotOpenException ex) {
        return ex.getMessage();
    }

    /**
     * Exception handler that send back in response what field in not valid in case of bad request
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(@NotNull MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
