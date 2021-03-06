package com.dinu.survey.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user", schema = "public")
@JsonIgnoreProperties(value = {"password"}, allowSetters = true)
// make password field write-only because it appears in survey output - creator field
public class AppUser {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Username is mandatory.")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "Password is mandatory.")
    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    @NotBlank(message = "Roles are mandatory.")
    private String roles;

    public AppUser(String username, String password, String roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public AppUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

}
