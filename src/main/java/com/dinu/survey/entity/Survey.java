package com.dinu.survey.entity;

import com.dinu.survey.security.AppUserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Entity
public class Survey {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Title is mandatory.")
    private String title;

    @OneToMany(targetEntity = Question.class, cascade = CascadeType.ALL)
    private Set<Question> questions;

    @ManyToOne(targetEntity = AppUser.class)
    private AppUser creator;

    private boolean open = false;

    public Survey(String title, Set<Question> questions, AppUser creator, boolean open) {
        this.title = title;
        this.questions = questions;
        this.creator = creator;
        this.open = open;
    }

    public Survey() {
    }

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

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public AppUser getCreator() {
        return creator;
    }

    public void setCreator(AppUser creator) {
        this.creator = creator;
    }
}
