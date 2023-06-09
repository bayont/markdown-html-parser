package com.example.markdownhtmlparser.validation.markdown;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MarkdownValidatorTest {

    @Test
    void markdownSyntaxIsAlwaysValid() {
        MarkdownValidator validator = new MarkdownValidator();
        assertDoesNotThrow(() -> validator.validate("Test!"));
    }
}