package com.wetorek.teamproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDtoResponse {
    private List<OptionDtoResponse> options;
    private int id;
    private String question;
    private int maxPoints;
    private int points;
    private int testId;
}
