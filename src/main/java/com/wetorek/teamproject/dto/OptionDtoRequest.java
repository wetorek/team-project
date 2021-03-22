package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class OptionDtoRequest {
    @Min(1)
    private int id;
    @NotBlank(message = "Answer text is mandatory")
    private String answerText;
    private boolean marked;
}
