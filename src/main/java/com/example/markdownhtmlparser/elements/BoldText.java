package com.example.markdownhtmlparser.elements;

public class BoldText extends Element {
    private String text;

    public BoldText(String text) {
        this.text = text;
    }

    public String toHTML() {
        return "<b>" + text + "</b>";
    }

    public String toMarkdown() {
        return "**" + text + "**";
    }
}
