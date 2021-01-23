package com.wetorek.teamproject.repository;

import com.wetorek.teamproject.entity.QuestionTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionTemplateRepository extends JpaRepository<QuestionTemplate, Integer> {
    List<QuestionTemplate> getAllByTestTemplate_Id(Integer testId);
}
