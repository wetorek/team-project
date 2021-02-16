package com.wetorek.teamproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class TestDtoResponse {
    private int id;
    private String name;
    private String description;
    private boolean checked;
    private boolean submitted;
    private int examinedUserId;
    private int testTemplateId;
    private List<QuestionDtoResponse> questionDtoResponseList;
}
