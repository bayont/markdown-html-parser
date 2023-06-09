package com.example.markdownhtmlparser.elements;

import java.util.ArrayList;
import java.util.Arrays;

public class ElementsList extends ArrayList<Element> {
    
    public String toHTML() {
        StringBuilder sb = new StringBuilder();
        for (Element element : this.stream().toList()) {
            sb.append(element.toHTML()).append("\n");
        }
        return sb.toString();
    }

    public String toMarkdown() {
        StringBuilder sb = new StringBuilder();
        for (Element element : this.stream().toList()) {
            sb.append(element.toMarkdown()).append("\n");
        }
        return sb.toString();
    }
}
