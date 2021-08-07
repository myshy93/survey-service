package com.dinu.survey.controller;

import com.dinu.survey.entity.Survey;
import com.dinu.survey.repository.SurveyRepository;
import org.springframework.http.HttpStatus;
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

    public SurveyController(SurveyRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/surveys")
    Survey newSurvey(@Valid @RequestBody Survey newSurvey) {
        return repository.save(newSurvey);
    }

    @GetMapping("/surveys")
    List<Survey> getAllSurveys() {
        return repository.findAll();
    }

    @GetMapping("/surveys/{id}")
    Survey getOneSurvey(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(id));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
