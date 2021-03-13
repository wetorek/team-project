package com.wetorek.teamproject.exceptions;

public class QuestionTemplateNotFoundEx extends ResourceNotFoundException {
    public QuestionTemplateNotFoundEx() {
    }

    public QuestionTemplateNotFoundEx(String message) {
        super(message);
    }

    public QuestionTemplateNotFoundEx(String message, Throwable cause) {
        super(message, cause);
    }

    public QuestionTemplateNotFoundEx(Throwable cause) {
        super(cause);
    }

    public QuestionTemplateNotFoundEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
