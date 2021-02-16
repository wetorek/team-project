package com.wetorek.teamproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class TestDtoRequest {
    private int id;
    private String name;
    private String description;
    private List<QuestionDtoRequest> questionDtoRequests;
}
