package com.wetorek.teamproject.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDtoRequest {
    private List<OptionDtoRequest> options;
    private int id;
    private String question;
    private int maxPoints;
}
