package com.example.markdownhtmlparser.validation.html;

public class ForbiddenTagError extends HTMLValidationError {
    public ForbiddenTagError(String tag) {
        super("Forbidden tag: " + tag);
    }
}
