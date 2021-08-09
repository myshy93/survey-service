package com.dinu.survey.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class SurveyResponse {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @ManyToOne(targetEntity = Survey.class)
    private Survey survey;

    @ManyToOne(targetEntity = AppUser.class)
    private AppUser respondent;

    @NotBlank
    @OneToMany(targetEntity = QuestionResponse.class, cascade = CascadeType.ALL)
    private Set<QuestionResponse> responseList;

    public SurveyResponse(Survey survey, AppUser respondent, Set<QuestionResponse> responseList) {
        this.survey = survey;
        this.respondent = respondent;
        this.responseList = responseList;
    }

    public SurveyResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Survey getSurvey() {
        return survey;
    }

    public void setSurvey(Survey survey) {
        this.survey = survey;
    }

    public Set<QuestionResponse> getResponseList() {
        return responseList;
    }

    public void setResponseList(Set<QuestionResponse> responseList) {
        this.responseList = responseList;
    }

    public AppUser getRespondent() {
        return respondent;
    }

    public void setRespondent(AppUser respondent) {
        this.respondent = respondent;
    }
}
