package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class QuestionDtoRequest {
    @NotEmpty
    private List<OptionDtoRequest> options;
    private int id;
    @NotEmpty
    @NotBlank
    private String question;
    private int maxPoints;
}
