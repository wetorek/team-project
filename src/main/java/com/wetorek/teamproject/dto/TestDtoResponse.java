package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class TestDtoResponse {
    private int id;
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private boolean checked;
    private boolean submitted;
    private int examinedUserId;
    @NotBlank
    private String examinedUserUsername;
    private int testTemplateId;
    private int points;
    @NotEmpty
    private List<QuestionDtoResponse> questionDtoResponseList;
}
