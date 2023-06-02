package com.example.markdownhtmlparser.elements;

public class HorizontalRule extends Element {
    public String toHTML() {
        return "<hr />\n";
    }

    public String toMarkdown() {
        return "---\n";
    }
}
