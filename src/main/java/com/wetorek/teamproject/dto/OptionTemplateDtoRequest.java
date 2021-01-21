package com.wetorek.teamproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OptionTemplateDtoRequest {
    private String answerText;
    private boolean correct;
}
