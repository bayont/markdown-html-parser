package com.example.markdownhtmlparser.elements;

import java.net.URI;

public class Image extends Element {
    private URI link;
    private String altText;

    public Image(URI link, String altText) {
        this.link = link;
        this.altText = altText;
    }

    public String toHTML() {
        return "<img src=\"" + link + "\" alt=\"" + altText + "\">";
    }

    public String toMarkdown() {
        return "![" + altText + "](" + link + ")";
    }
}
