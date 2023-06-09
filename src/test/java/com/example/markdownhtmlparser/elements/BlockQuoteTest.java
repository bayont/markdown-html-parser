package com.example.markdownhtmlparser.elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockQuoteTest {

    @Test
    void toHTML() {
        BlockQuote blockQuote = new BlockQuote(new String[]{"line1", "line2"});
        String expected = "<blockquote>line1\n<br />\nline2</blockquote>";
        String actual = blockQuote.toHTML();
        assertEquals(expected, actual);
    }

    @Test
    void toMarkdown() {
        BlockQuote blockQuote = new BlockQuote(new String[]{"line1", "line2"});
        String expected = "> line1\n> line2";
        String actual = blockQuote.toMarkdown();
        assertEquals(expected, actual);
    }
}