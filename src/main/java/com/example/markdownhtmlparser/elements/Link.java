package com.example.markdownhtmlparser.elements;

import java.net.URI;

public class Link extends Element {
    private URI link;
    private ElementsList elementsList;

    public Link(ElementsList elementsList, URI link) {
        this.link = link;
        this.elementsList = elementsList;
    }

    public String toHTML() {
        return "<a href=\"" + link + "\">" + elementsList.toHTML() + "</a>";
    }

    public String toMarkdown() {
        return "[" + elementsList.toMarkdown() + "](" + link + ")";
    }
}
