package com.wetorek.teamproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionTemplateDtoRequest {
    private String question;
    private int maxPoints;
    private int correctAnswers;
    private Set<OptionTemplateDtoRequest> optionTemplateDto;
}
