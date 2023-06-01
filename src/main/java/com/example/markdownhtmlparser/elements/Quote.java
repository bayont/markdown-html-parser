package com.example.markdownhtmlparser.elements;

public class Quote extends Element{
    private String text;

    public Quote(String text) {
        this.text = text;
    }

    public String toHTML() {
        return "<blockquote>" + text + "</blockquote>";
    }

    public String toMarkdown() {
        return ">" + text;
    }
}
