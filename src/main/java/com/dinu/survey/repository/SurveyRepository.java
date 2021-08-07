package com.dinu.survey.repository;

import com.dinu.survey.dao.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
}
