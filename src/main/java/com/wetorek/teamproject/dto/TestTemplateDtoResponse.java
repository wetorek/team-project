package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class TestTemplateDtoResponse {
    private int id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @NotBlank
    private String creator;
    @NotEmpty
    private List<QuestionTemplateDtoResponse> questionTemplateDtoResponses;
}
