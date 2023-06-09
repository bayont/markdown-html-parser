package com.example.markdownhtmlparser.elements;

public class HorizontalRule implements Element {
    public String toHTML() {
        return "<hr />";
    }

    public String toMarkdown() {
        return "---";
    }
}
