package com.example.markdownhtmlparser.elements;

public class NormalText extends Element {
    private String text;

    public NormalText(String text) {
        this.text = text;
    }

    public String toHTML() {
        return text;
    }

    public String toMarkdown() {
        return text;
    }
}
