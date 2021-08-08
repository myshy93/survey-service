package com.dinu.survey.controller.exception;

public class SurveyAlreadyOpenException extends RuntimeException {
    public SurveyAlreadyOpenException(String title) {
        super("Survey " + title + " already opened!");
    }
}
