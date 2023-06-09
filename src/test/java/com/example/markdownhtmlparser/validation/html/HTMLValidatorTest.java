package com.example.markdownhtmlparser.validation.html;

import com.example.markdownhtmlparser.validation.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HTMLValidatorTest {

    @Test
    void missingAttributeQuoteShouldThrowError() {
        Validator htmlValidator = new HTMLValidator();

        assertThrows(AttributeSyntaxError.class, () -> {
            htmlValidator.validate("<img src=foo.png />");
        });

    }

    @Test
    void usingForbiddenTagShouldThrowError() {
        Validator htmlValidator = new HTMLValidator();

        assertThrows(ForbiddenTagError.class, () -> {
            htmlValidator.validate("<script>alert('hello');</script>");
        });
    }

    @Test
    void unclosedTagShouldThrowError() {
        Validator htmlValidator = new HTMLValidator();

        assertThrows(UnclosedTagError.class, () -> {
            htmlValidator.validate("<h1>hello");
        });
    }

    @Test
    void openingTagNotFoundErrorShouldThrowError() {
        Validator htmlValidator = new HTMLValidator();

        assertThrows(OpeningTagNotFoundError.class, () -> {
            htmlValidator.validate("hello</h2>");
        });
    }
}