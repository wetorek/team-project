package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class OptionDtoResponse {
    private int id;
    @NotEmpty
    @NotBlank
    private String answerText;
    private boolean marked;
    private int questionId;
}
