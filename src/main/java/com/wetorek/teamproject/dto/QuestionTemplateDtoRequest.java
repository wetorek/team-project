package com.wetorek.teamproject.dto;

import lombok.*;

import java.util.Set;

@Data
public class QuestionTemplateDtoRequest {
    private String question;
    private int maxPoints;
    private int correctAnswers;
    private int allAnswers;
    private Set<OptionTemplateDtoRequest> optionTemplateDto;
}
