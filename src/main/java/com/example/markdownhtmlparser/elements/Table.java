package com.example.markdownhtmlparser.elements;

import com.example.markdownhtmlparser.utils.ContentParser;

public class Table extends Element {
    int rows;
    int columns;
    ElementsList[][] dataElements;
    public Table() {
        super();
    }

    public String toHTML() {
        String html = "<table>\n";
        for (int i = 0; i < rows; i++) {
            html += "<tr>\n";
            for (int j = 0; j < columns; j++) {
                html += "<td>" + dataElements[i][j].toHTML() + "</td>\n";
            }
            html += "</tr>\n";
        }
        html += "</table>\n";
        return html;
    }

    public String toMarkdown() {
        String markdown = "";
        for (int i = 0; i < rows; i++) {
            markdown += "|";
            for (int j = 0; j < columns; j++) {
                markdown += " " + dataElements[i][j].toMarkdown() + " |";
            }
            markdown += "\n";
        }
        return markdown;
    }
}
