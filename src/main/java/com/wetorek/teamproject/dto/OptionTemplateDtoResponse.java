package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class OptionTemplateDtoResponse {
    private int id;
    @NotBlank
    private String answerText;
    private boolean correct;
}
