package com.example.markdownhtmlparser.elements;

public class HorizontalRule extends Element {
    public String toHTML() {
        return "<hr />";
    }

    public String toMarkdown() {
        return "---";
    }
}
