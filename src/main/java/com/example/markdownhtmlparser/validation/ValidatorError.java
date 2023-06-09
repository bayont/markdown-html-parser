package com.example.markdownhtmlparser.validation;

public abstract class ValidatorError extends Exception {
    public ValidatorError(String message) {
        super("Validation Error -> " + message);
    }
}
