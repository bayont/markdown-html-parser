package com.example.markdownhtmlparser.elements;

public class ItalicText extends Element {
    private String text;

    public ItalicText(String text) {
        this.text = text;
    }

    public String toHTML() {
        return "<i>" + text + "</i>";
    }

    public String toMarkdown() {
        return "*" + text + "*";
    }
}
