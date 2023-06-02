package com.example.markdownhtmlparser.elements;

import com.example.markdownhtmlparser.utils.ContentParser;

public class List extends Element {
    ListType type;
    String[] lines;

    public List(ListType type, String[] lines) {
        this.type = type;
        this.lines = lines;
    }

    @Override
    public String toHTML() {
        String html = type == ListType.ORDERED ? "<ol>\n" : "<ul>\n";
        for(String line : lines) {
            html += "\t<li>" + ContentParser.MarkdownToHTML(line) + "</li>\n";
        }
        html += type == ListType.ORDERED ? "</ol>\n" : "</ul>\n";
        return html;
    }

    @Override
    public String toMarkdown() {
        StringBuilder markdown = new StringBuilder();
        if(type == ListType.ORDERED) {
            for (int i = 0; i < lines.length; i++) {
                markdown.append(i + 1).append(". ").append(ContentParser.HTMLToMarkdown(lines[i])).append("\n");
            }
        } else {
            for (int i = 0; i < lines.length; i++) {
                markdown.append("* ").append(ContentParser.HTMLToMarkdown(lines[i])).append("\n");
            }
        }
        return markdown.toString();
    }
}
