package com.wetorek.teamproject.repository;


import com.wetorek.teamproject.entity.OptionTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionTemplateRepository extends JpaRepository<OptionTemplate, Integer> {
    List<OptionTemplate> getAllByQuestionTemplate_Id(Integer questionId);
}
