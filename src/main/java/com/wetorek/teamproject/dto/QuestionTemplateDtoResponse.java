package com.wetorek.teamproject.dto;

import lombok.*;

import java.util.Set;

@Data
public class QuestionTemplateDtoResponse {
    private int id;
    private String question;
    private int maxPoints;
    private int correctAnswers;
    private int allAnswers;
    private Set<OptionTemplateDtoResponse> optionTemplateDto;
}
