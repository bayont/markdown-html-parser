package com.example.markdownhtmlparser.elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParagraphTest {

    @Test
    void toHTML() {
        Paragraph paragraph = new Paragraph(new String[]{"Test"});
        String expected = "<p>Test</p>";
        String actual = paragraph.toHTML();
        assertEquals(expected, actual);
    }

    @Test
    void toMarkdown() {
        Paragraph paragraph = new Paragraph(new String[]{"Test"});
        String expected = "Test\n";
        String actual = paragraph.toMarkdown();
        assertEquals(expected, actual);
    }
}