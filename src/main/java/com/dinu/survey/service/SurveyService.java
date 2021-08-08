package com.dinu.survey.service;

import com.dinu.survey.controller.exception.SurveyAlreadyClosedException;
import com.dinu.survey.controller.exception.SurveyAlreadyOpenException;
import com.dinu.survey.controller.exception.SurveyNotFoundException;
import com.dinu.survey.entity.AppUser;
import com.dinu.survey.entity.Survey;
import com.dinu.survey.repository.SurveyRepository;
import com.dinu.survey.repository.SurveySpecification;
import com.dinu.survey.repository.UserRepository;
import com.dinu.survey.security.AppUserDetails;
import com.dinu.survey.util.SearchCriteria;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Add new survey. Creator field is filled with current user.
     *
     * @param newSurvey New survey object received as JSON.
     * @return Newly created survey.
     */
    public Survey add(@NotNull Survey newSurvey) {
        AppUserDetails userDetails;
        AppUser appUser;
        Object user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user != null) {
            userDetails = (AppUserDetails) user;
            appUser = userRepository.findById(userDetails.getId()).orElse(null);
            newSurvey.setCreator(appUser);
        }

        return surveyRepository.save(newSurvey);
    }

    /**
     * Get all surveys with respect to user role.
     * If the user is normal user (USER), only the opened surveys will be returned.
     * If the user is ADMIN, all surveys will be returned.
     *
     * @return A list of surveys.
     */
    public List<Survey> getAll() {
        SurveySpecification specs = new SurveySpecification(
                new SearchCriteria("open", ":", true)
        );
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN")))
            return surveyRepository.findAll();

        return surveyRepository.findAll(specs);
    }

    /**
     * Get one survey.
     * If the user is normal user (USER) it can view the survey only if the survey is open, otherwise it will receive 401.
     * If the user is ADMIN it can view it any survey even if it is closed.
     */
    public Survey getOne(Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(id));

        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
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
     * Open a closed survey.
     * If the survey is already opened, 409 is returned.
     * If the survey id does not exist, 404 is returned.
     *
     * @param id Survey id.
     * @return String confirmation.
     */
    public String open(Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(id));

        if (survey.isOpen()) {
            throw new SurveyAlreadyOpenException(survey.getTitle());
        } else {
            survey.setOpen(true);
            surveyRepository.save(survey);
        }

        return "Survey opened!";
    }


    /**
     * Close an opened survey.
     * If the survey is already closed, 409 is returned.
     * If the survey id does not exist, 404 is returned.
     *
     * @param id Survey id.
     * @return String confirmation.
     */
    public String close(Long id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(() -> new SurveyNotFoundException(id));

        if (!survey.isOpen()) {
            throw new SurveyAlreadyClosedException(survey.getTitle());
        } else {
            survey.setOpen(false);
            surveyRepository.save(survey);
        }

        return "Survey closed!";
    }
}
