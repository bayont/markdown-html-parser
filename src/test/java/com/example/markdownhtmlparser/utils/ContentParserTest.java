package com.example.markdownhtmlparser.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContentParserTest {

    @Test
    void HTMLToMarkdown() {
        String html = "<b>bold</b> <i>italic</i> <code>code</code> <a href=\"https://www.google.com\">link</a> <a href=\"https://www.facebook.com\">link2</a> <img src=\"https://www.google.com\" alt=\"image\" /> <img src=\"https://www.facebook.com\"/>";
        String expectedMarkdown = "**bold** *italic* `code` [link](https://www.google.com) [link2](https://www.facebook.com) ![image](https://www.google.com) ![](https://www.facebook.com)";
        String actual = ContentParser.HTMLToMarkdown(html);
        assertEquals(expectedMarkdown, actual);
    }

    @Test
    void markdownToHTML() {
        String markdown = "**bold** *italic* `code` [link](https://www.google.com) [link2](https://www.facebook.com) ![image](https://www.google.com) ![](https://www.facebook.com)";
        String expectedHTML = "<b>bold</b> <i>italic</i> <code>code</code> <a href=\"https://www.google.com\">link</a> <a href=\"https://www.facebook.com\">link2</a> <img src=\"https://www.google.com\" alt=\"image\" /> <img src=\"https://www.facebook.com\" alt=\"\" />";
        String actual = ContentParser.MarkdownToHTML(markdown);
        assertEquals(expectedHTML, actual);
    }
}