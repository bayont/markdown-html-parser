package com.example.markdownhtmlparser.elements;

public class Code extends Element{
    private String text;
    private String language;

    public Code(String text) {
        this.text = text;
    }

    public Code(String text, String language) {
        this.text = text;
        this.language = language;
    }

    public String toHTML() {
        return "<code>" + text + "</code>";
    }

    public String toMarkdown() {
        return "```" + language + "\n" + text + "\n```";
    }
}
