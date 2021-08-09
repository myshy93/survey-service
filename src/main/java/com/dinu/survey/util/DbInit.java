package com.dinu.survey.util;

import com.dinu.survey.entity.*;
import com.dinu.survey.repository.SurveyRepository;
import com.dinu.survey.repository.SurveyResponseRepository;
import com.dinu.survey.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DbInit implements CommandLineRunner {

    @Autowired
    private SurveyRepository surveyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SurveyResponseRepository surveyResponseRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        // add one normal user
        AppUser user = new AppUser("user", passwordEncoder.encode("user"), "ROLE_USER");
        userRepository.save(user);
        // add a admin user
        AppUser admin = new AppUser("admin", passwordEncoder.encode("admin"), "ROLE_ADMIN");
        userRepository.save(admin);

        // add some answers
        Answer a1 = new Answer("Yes");
        Answer a2 = new Answer("No");

        Answer a3 = new Answer("From home");
        Answer a4 = new Answer("Form office");
        Answer a5 = new Answer("Hybrid");

        // add some questions
        Question q1 = new Question("Do you enjoy working in IT?", Set.of(a1, a2), false);
        Question q2 = new Question("How do you want to work?", Set.of(a3, a4, a5), true);

        // add one survey
        Survey survey = new Survey("IT survey", Set.of(q1, q2), admin, false);

        surveyRepository.save(survey);

        System.out.println("Database seeded!");
    }
}
