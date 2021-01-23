package com.wetorek.teamproject.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class QuestionTemplateNotFound extends RuntimeException {

    public QuestionTemplateNotFound(final String message) {
        super(message);
    }
}
