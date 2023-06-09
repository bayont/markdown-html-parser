package com.example.markdownhtmlparser.elements;

import com.example.markdownhtmlparser.utils.ContentParser;

public class Paragraph extends Element{
    private String[] lines;

    public Paragraph(String[] lines) {
        this.lines = lines;
    }

    public String toHTML() {
        return "<p>" + ContentParser.MarkdownToHTML(String.join("\n<br />\n", lines)) + "</p>";
    }

    public String toMarkdown() {
        return ContentParser.HTMLToMarkdown(String.join("\n", lines)) + "\n";
    }
}
