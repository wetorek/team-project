package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OptionTemplateDtoRequest {
    @NotBlank(message = "Answer text is mandatory")
    private String answerText;
    private boolean correct;
}
