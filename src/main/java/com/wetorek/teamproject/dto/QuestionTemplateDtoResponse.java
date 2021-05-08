package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class QuestionTemplateDtoResponse {
    private int id;
    @NotBlank
    private String question;
    private int maxPoints;
    private int correctAnswers;
    private int allAnswers;
    @NotEmpty
    private List<OptionTemplateDtoResponse> optionTemplateDtoResponses;
}
