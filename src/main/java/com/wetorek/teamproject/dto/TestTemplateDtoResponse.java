package com.wetorek.teamproject.dto;

import lombok.*;

import java.util.Set;

@Data
public class TestTemplateDtoResponse {
    private int id;
    private String title;
    private String description;
    private String creator;
    private Set<QuestionTemplateDtoResponse> questionTemplateDto;
}
