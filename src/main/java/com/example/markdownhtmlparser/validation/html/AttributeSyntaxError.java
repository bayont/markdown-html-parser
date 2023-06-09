package com.example.markdownhtmlparser.validation.html;

public class AttributeSyntaxError extends HTMLValidationError {
    public AttributeSyntaxError(String attribute) {
        super("Attribute syntax error: " + attribute);
    }
}
