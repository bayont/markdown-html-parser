package com.example.markdownhtmlparser.elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CodeTest {

    @Test
    void toHTML() {
        Code codeTest = new Code(new String[]{"line1", "line2"});
        String expected = "<code>line1\n<br />\nline2</code>";
        String actual = codeTest.toHTML();
        assertEquals(expected, actual);

    }

    @Test
    void toMarkdown() {
        Code codeTest = new Code(new String[]{"line1", "line2"});
        String expected = "```\nline1\nline2\n```";
        String actual = codeTest.toMarkdown();
        assertEquals(expected, actual);
    }
}