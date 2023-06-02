package com.example.markdownhtmlparser.elements;

public class Code extends Element{
    private String[] lines;
    private String language;

    public Code(String[] lines) {
        this.lines = lines;
        this.language = "";
    }

    public Code(String[] lines, String language) {
        this.lines = lines;
        this.language = language;
    }

    public String toHTML() {
        return "<code>" + String.join("\n<br />", lines) + "</code>";
    }

    public String toMarkdown() {
        return "```" + language + "\n" + String.join("\n", lines) + "\n```";
    }
}
