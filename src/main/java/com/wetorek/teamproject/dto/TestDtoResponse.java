package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class TestDtoResponse {
    private int id;
    @NotEmpty
    @NotBlank
    private String name;
    @NotEmpty
    @NotBlank
    private String description;
    private boolean checked;
    private boolean submitted;
    private int examinedUserId;
    private int testTemplateId;
    private int points;
    @NotEmpty
    private List<QuestionDtoResponse> questionDtoResponseList;
}
