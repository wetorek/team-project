package com.wetorek.teamproject.dto;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class TestDtoRequest {
    @Min(1)
    private int id;
    @NotEmpty(message = "Name is mandatory")
    private String name;
    @NotEmpty(message = "Description is mandatory")
    private String description;
    @Min(1)
    private int userId;
    @NotEmpty
    private List<QuestionDtoRequest> questionDtoRequests;
}
