package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class QuestionTemplateDtoResponse {
    private int id;
    @NotEmpty
    @NotBlank
    private String question;
    private int maxPoints;
    private int correctAnswers;
    private int allAnswers;
    @NotEmpty
    private Set<OptionTemplateDtoResponse> optionTemplateDto;
}
