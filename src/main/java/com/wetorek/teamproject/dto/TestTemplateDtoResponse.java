package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class TestTemplateDtoResponse {
    private int id;
    @NotEmpty
    @NotBlank
    private String title;
    @NotEmpty
    @NotBlank
    private String description;
    @NotEmpty
    @NotBlank
    private String creator;
    @NotEmpty
    private Set<QuestionTemplateDtoResponse> questionTemplateDto;
}
