package com.example.markdownhtmlparser.validation.html;

public class OpeningTagNotFoundError extends HTMLValidationError{
    public OpeningTagNotFoundError(String tagName) {
        super("Opening tag for " + tagName + " not found");
    }
}
