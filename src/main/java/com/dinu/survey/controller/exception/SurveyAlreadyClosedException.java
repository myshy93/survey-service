package com.dinu.survey.controller.exception;

public class SurveyAlreadyClosedException extends RuntimeException {
    public SurveyAlreadyClosedException(String title) {
        super("Survey " + title + " already closed!");
    }
}
