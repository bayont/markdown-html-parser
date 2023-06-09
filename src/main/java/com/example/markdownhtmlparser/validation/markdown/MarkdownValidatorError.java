package com.example.markdownhtmlparser.validation.markdown;

import com.example.markdownhtmlparser.validation.ValidatorError;

public class MarkdownValidatorError extends ValidatorError {
    public MarkdownValidatorError(String message) {
        super("[Markdown] " + message);
    }
}
