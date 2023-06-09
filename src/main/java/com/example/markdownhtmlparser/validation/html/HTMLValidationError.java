package com.example.markdownhtmlparser.validation.html;

import com.example.markdownhtmlparser.validation.ValidatorError;

public class HTMLValidationError extends ValidatorError {
    public HTMLValidationError(String message) {
        super("[HTML] " + message);
    }
}
