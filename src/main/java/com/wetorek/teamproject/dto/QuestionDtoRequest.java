package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class QuestionDtoRequest {
    @NotEmpty
    private List<OptionDtoRequest> options;
    @Min(1)
    private int id;
    @NotBlank(message = "Question is mandatory")
    private String question;
    private int maxPoints;
}
