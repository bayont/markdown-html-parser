package com.example.markdownhtmlparser.elements;

import com.example.markdownhtmlparser.utils.ContentParser;

public class ListElement implements Element {
    ListElementType type;
    String[] lines;

    public ListElement(ListElementType type, String[] lines) {
        this.type = type;
        this.lines = lines;
    }

    @Override
    public String toHTML() {
        StringBuilder html = new StringBuilder(type == ListElementType.ORDERED ? "<ol>\n" : "<ul>\n");
        for(String line : lines) {
            html.append("\t<li>").append(ContentParser.MarkdownToHTML(line)).append("</li>\n");
        }
        html.append(type == ListElementType.ORDERED ? "</ol>" : "</ul>");
        return html.toString();
    }

    @Override
    public String toMarkdown() {
        StringBuilder markdown = new StringBuilder();
        if(type == ListElementType.ORDERED) {
            for (int i = 0; i < lines.length; i++) {
                markdown.append(i + 1).append(". ").append(ContentParser.HTMLToMarkdown(lines[i]));
                if(i != lines.length - 1) {
                    markdown.append("\n");
                }
            }
        } else {
            for (int i = 0; i < lines.length; i++) {
                markdown.append("* ").append(ContentParser.HTMLToMarkdown(lines[i]));
                if(i != lines.length - 1) {
                    markdown.append("\n");
                }
            }
        }
        return markdown.toString();
    }
}
