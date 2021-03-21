package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class TestDtoRequest {
    private int id;
    @NotEmpty
    @NotBlank
    private String name;
    @NotEmpty
    @NotBlank
    private String description;
    private int userId;
    @NotEmpty
    private List<QuestionDtoRequest> questionDtoRequests;
}
