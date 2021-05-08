package com.wetorek.teamproject.dto;

import com.wetorek.teamproject.entity.OptionStatus;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class OptionDtoResponse {
    private int id;
    @NotBlank
    private String answerText;
    private boolean marked;
    private OptionStatus optionStatus;
}
