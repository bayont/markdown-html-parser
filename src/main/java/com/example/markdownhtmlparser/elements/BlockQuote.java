package com.example.markdownhtmlparser.elements;

import com.example.markdownhtmlparser.utils.ContentParser;

public class BlockQuote extends Element {
    private String[] lines;

    public BlockQuote(String[] lines) {
        this.lines = lines;
    }

    public String toHTML() {
        return "<blockquote>" + ContentParser.MarkdownToHTML(String.join("\n<br />\n", lines)) + "</blockquote>";
    }

    public String toMarkdown() {

        return ContentParser.HTMLToMarkdown(String.join("\n> "));
    }
}
