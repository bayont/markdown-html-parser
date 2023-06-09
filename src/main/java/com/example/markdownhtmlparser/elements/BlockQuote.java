package com.example.markdownhtmlparser.elements;

import com.example.markdownhtmlparser.utils.ContentParser;

public class BlockQuote implements Element {
    private String[] lines;

    public BlockQuote(String[] lines) {
        this.lines = lines;
    }

    public String toHTML() {
        return "<blockquote>" + ContentParser.MarkdownToHTML(String.join("\n<br />\n", lines)) + "</blockquote>";
    }

    public String toMarkdown() {
        String[] newLines = new String[lines.length];
        for (int i = 0; i < lines.length; i++) {
            newLines[i] = "> " + lines[i];
        }

        return ContentParser.HTMLToMarkdown(String.join("\n", newLines));
    }
}
