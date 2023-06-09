package com.example.markdownhtmlparser.elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HorizontalRuleTest {

    @Test
    void toHTML() {
        HorizontalRule hr = new HorizontalRule();
        String expected = "<hr />\n";
        String actual = hr.toHTML();
        assertEquals(expected, actual);
    }

    @Test
    void toMarkdown() {
        HorizontalRule hr = new HorizontalRule();
        String expected = "\n---\n";
        String actual = hr.toMarkdown();
        assertEquals(expected, actual);
    }
}