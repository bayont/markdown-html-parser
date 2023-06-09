package com.example.markdownhtmlparser.elements;

import com.example.markdownhtmlparser.exceptions.InvalidHeadingLevelException;
import com.example.markdownhtmlparser.utils.ContentParser;

public class Heading extends Element {
    int level;
    String content;

    public Heading(int level, String content) throws InvalidHeadingLevelException {
        if (level < 1 || level > 6) {
            throw new InvalidHeadingLevelException();
        }
        this.level = level;
        this.content = content;
    }

    @Override
    public String toHTML() {
        return "<h" + level + ">" + ContentParser.MarkdownToHTML(content) + "</h" + level + ">";
    }

    @Override
    public String toMarkdown() {
        return "#".repeat(level) + " " + ContentParser.HTMLToMarkdown(content);
    }
}

