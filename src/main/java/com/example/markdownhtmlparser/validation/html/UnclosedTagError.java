package com.example.markdownhtmlparser.validation.html;

public class UnclosedTagError extends HTMLValidationError {
    public UnclosedTagError(String tag) {
        super("Unclosed tag: " + tag);
    }
}
