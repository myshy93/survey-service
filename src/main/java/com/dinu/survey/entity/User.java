package com.dinu.survey.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Username is mandatory.")
    @Column(name = "username")
    private String username;

    @NotBlank(message = "Password is mandatory.")
    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    @NotBlank(message = "Roles are mandatory.")
    private String roles;

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
