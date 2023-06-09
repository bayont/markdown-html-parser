package com.example.markdownhtmlparser.elements;

public class Paragraph extends Element{
    private String[] lines;

    public Paragraph(String[] lines) {
        this.lines = lines;
    }

    public String toHTML() {
        return "<p>" + String.join("\n<br />\n", lines) + "</p>";
    }

    public String toMarkdown() {
        return String.join("\n", lines) + "\n";
    }
}
