package com.dinu.survey.controller;

import com.dinu.survey.dao.Survey;
import com.dinu.survey.repository.SurveyRepository;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
public class SurveyController {
    private final SurveyRepository repository;

    public SurveyController(SurveyRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/surveys")
    Survey newSurvey(@RequestBody Survey newSurvey) {
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
}
