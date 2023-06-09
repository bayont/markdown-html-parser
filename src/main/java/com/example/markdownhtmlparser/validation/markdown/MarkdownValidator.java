package com.example.markdownhtmlparser.validation.markdown;

import com.example.markdownhtmlparser.validation.Validator;

public class MarkdownValidator extends Validator {
    @Override
    public String validate(String markdown) throws MarkdownValidatorError {
        return markdown;
    }
}

