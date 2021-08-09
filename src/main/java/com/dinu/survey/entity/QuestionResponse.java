package com.dinu.survey.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Entity
public class QuestionResponse {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank
    @ManyToOne(targetEntity = Question.class)
    private Question question;

    @OneToMany(targetEntity = Answer.class)
    private Set<Answer> answers;

    public QuestionResponse(Question question, Set<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public QuestionResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }
}
