package com.dinu.survey.controller;

import com.dinu.survey.controller.exception.SurveyAlreadyClosedException;
import com.dinu.survey.controller.exception.SurveyAlreadyOpenException;
import com.dinu.survey.controller.exception.SurveyNotFoundException;
import com.dinu.survey.entity.Survey;
import com.dinu.survey.repository.SurveyRepository;
import com.dinu.survey.repository.SurveySpecification;
import com.dinu.survey.util.SearchCriteria;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
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
    List<Survey> getAllSurveys(@NotNull Authentication authentication) {
        SurveySpecification specs = new SurveySpecification(
                new SearchCriteria("open", ":", true)
        );
        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")))
            return repository.findAll();

        return repository.findAll(specs);

    }

    /**
     * Get one survey.
     * If the user is normal user (USER) it can view the survey only if the survey is open, otherwise it will receive 401.
     * If the user is ADMIN it can view it any survey even if it is closed.
     */
    @GetMapping("/surveys/{id}")
    Survey getOneSurvey(@PathVariable Long id, @NotNull Authentication authentication) {
        Survey survey = repository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(id));

        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return survey;
        } else {
            if (survey.isOpen())
                return survey;
            else
                throw new AccessDeniedException("Only admins can see closed surveys.");
        }


    }

    /**
     * Open a closed survey. Only users with ADMIN role can open surveys.
     * If the survey is already opened, 409 is returned.
     * If the survey id does not exist, 404 is returned.
     * @param id Survey id.
     * @return String confirmation.
     */
    @PostMapping("/surveys/{id}/open")
    String openSurvey(@PathVariable Long id) {
        Survey survey = repository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(id));

        if (survey.isOpen()) {
            throw new SurveyAlreadyOpenException(survey.getTitle());
        } else {
            survey.setOpen(true);
            repository.save(survey);
        }

        return "Survey opened!";
    }


    /**
     * Close an opened survey. Only users with ADMIN role can close surveys.
     * If the survey is already closed, 409 is returned.
     * If survey id does not exist, 404 is returned.
     * @param id Survey id.
     * @return String confirmation.
     */
    @PostMapping("/surveys/{id}/close")
    String closeSurvey(@PathVariable Long id) {
        Survey survey = repository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(id));

        if (!survey.isOpen()) {
            throw new SurveyAlreadyClosedException(survey.getTitle());
        } else {
            survey.setOpen(false);
            repository.save(survey);
        }

        return "Survey closed!";
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
