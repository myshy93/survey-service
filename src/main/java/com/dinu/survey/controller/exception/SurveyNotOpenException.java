package com.dinu.survey.controller.exception;

public class SurveyNotOpenException extends RuntimeException {
    public SurveyNotOpenException(String title) {
        super("Survey " + title + " is closed or was not started yet.");
    }
}
