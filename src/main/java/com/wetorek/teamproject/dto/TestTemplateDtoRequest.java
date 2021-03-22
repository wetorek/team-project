package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class TestTemplateDtoRequest {
    @NotEmpty(message = "Title is mandatory")
    private String title;
    @NotEmpty(message = "Description is mandatory")
    private String description;
    @NotEmpty
    private List<QuestionTemplateDtoRequest> questionTemplateDto;
}
