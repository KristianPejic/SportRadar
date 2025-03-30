package com.sportradar.worldcup.validation;

public class ScoreboardValidationException extends RuntimeException {
    public ScoreboardValidationException(String message) {
        super(message);
    }

    public ScoreboardValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}