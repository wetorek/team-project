package com.wetorek.teamproject.dto;

import lombok.Data;

@Data
public class OptionDtoResponse {
    private int id;
    private String answerText;
    private boolean marked;
    private int questionId;
}
