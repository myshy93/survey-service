package com.dinu.survey.dao;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Survey {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;
    private String title;
    @OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL)
    private Set<Question> questions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
