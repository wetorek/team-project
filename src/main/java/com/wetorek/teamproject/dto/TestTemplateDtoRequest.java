package com.wetorek.teamproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestTemplateDtoRequest {
    private String title;
    private String description;
    private Set<QuestionTemplateDtoRequest> questionTemplateDto;
}
