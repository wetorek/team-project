package com.wetorek.teamproject.dto;

import lombok.Data;

@Data
public class OptionDtoRequest {
    private int id;
    private String answerText;
    private boolean marked;
}
