package com.dinu.survey.entity;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Question {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    @NotBlank(message = "Question text is mandatory.")
    private String question;
    @OneToMany(targetEntity = Answer.class, cascade = CascadeType.ALL)
    private Set<Answer> answers;
    @NotNull(message = "Required field is mandatory.")
    boolean required;

    public Question(String question, Set<Answer> answers, boolean required) {
        this.question = question;
        this.answers = answers;
        this.required = required;
    }

    public Question() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Set<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<Answer> answers) {
        this.answers = answers;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
