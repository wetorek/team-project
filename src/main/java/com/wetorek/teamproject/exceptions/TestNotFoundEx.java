package com.wetorek.teamproject.exceptions;

public class TestNotFoundEx extends ResourceNotFoundException {
    public TestNotFoundEx() {
    }

    public TestNotFoundEx(String message) {
        super(message);
    }

    public TestNotFoundEx(String message, Throwable cause) {
        super(message, cause);
    }

    public TestNotFoundEx(Throwable cause) {
        super(cause);
    }

    public TestNotFoundEx(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
