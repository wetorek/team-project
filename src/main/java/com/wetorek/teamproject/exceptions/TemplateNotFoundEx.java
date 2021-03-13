package com.wetorek.teamproject.exceptions;

public class TemplateNotFoundEx extends ResourceNotFoundException {
    public TemplateNotFoundEx() {
    }

    public TemplateNotFoundEx(String message) {
        super(message);
    }

    public TemplateNotFoundEx(String message, Throwable cause) {
        super(message, cause);
    }

    public TemplateNotFoundEx(Throwable cause) {
        super(cause);
    }

    public TemplateNotFoundEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
