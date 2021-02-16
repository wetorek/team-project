package com.wetorek.teamproject.dto;

import lombok.*;

import java.util.Set;

@Data
public class TestTemplateDtoRequest {
    private String title;
    private String description;
    private Set<QuestionTemplateDtoRequest> questionTemplateDto;
}
