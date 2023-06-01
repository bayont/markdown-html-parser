package com.example.markdownhtmlparser.elements;

public class List extends Element {
    ListType type;
    ElementsList[] items;

    public List(ListType type, ElementsList[] items) {
        this.type = type;
        this.items = items;
    }

    @Override
    public String toHTML() {
        String html = type == ListType.ORDERED ? "<ol>\n" : "<ul>\n";
        for(ElementsList item : items) {
            html += "\t<li>" + item.toHTML() + "</li>\n";
        }
        html += type == ListType.ORDERED ? "</ol>\n" : "</ul>\n";
        return html;
    }

    @Override
    public String toMarkdown() {
        StringBuilder markdown = new StringBuilder();
        if(type == ListType.ORDERED) {
            for (int i = 0; i < items.length; i++) {
                markdown.append(i + 1).append(". ").append(items[i].toMarkdown()).append("\n");
            }
        } else {
            for (int i = 0; i < items.length; i++) {
                markdown.append("* ").append(items[i].toMarkdown()).append("\n");
            }
        }
        return markdown.toString();
    }

    public static List fromMarkdown(String[] lines) {
        //@TODO: implement
        return null;
    }
}
