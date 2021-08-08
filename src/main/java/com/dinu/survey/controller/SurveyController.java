package com.dinu.survey.controller;

import com.dinu.survey.controller.exception.SurveyAlreadyClosedException;
import com.dinu.survey.controller.exception.SurveyAlreadyOpenException;
import com.dinu.survey.controller.exception.SurveyNotFoundException;
import com.dinu.survey.entity.Survey;
import com.dinu.survey.repository.SurveyRepository;
import com.dinu.survey.repository.SurveySpecification;
import com.dinu.survey.service.SurveyService;
import com.dinu.survey.util.SearchCriteria;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SurveyController {
    private final SurveyRepository repository;
    private final SurveyService surveyService;

    public SurveyController(SurveyRepository repository, SurveyService surveyService) {
        this.repository = repository;
        this.surveyService = surveyService;
    }

    @PostMapping("/surveys")
    Survey newSurvey(@Valid @RequestBody Survey newSurvey) {
        return surveyService.add(newSurvey);
    }

    @GetMapping("/surveys")
    List<Survey> getAllSurveys(@NotNull Authentication authentication) {
        return surveyService.getAll();
    }

    @GetMapping("/surveys/{id}")
    Survey getOneSurvey(@PathVariable Long id) {
        return surveyService.getOne(id);
    }

    /**
     * Endpoint secured by Security Config.
     * Only users with ADMIN role can access this endpoint.
     */
    @PostMapping("/surveys/{id}/open")
    String openSurvey(@PathVariable Long id) {
        return surveyService.open(id);
    }

    /**
     * Endpoint secured by Security Config.
     * Only users with ADMIN role can access this endpoint.
     */
    @PostMapping("/surveys/{id}/close")
    String closeSurvey(@PathVariable Long id) {
        return surveyService.close(id);
    }
}
