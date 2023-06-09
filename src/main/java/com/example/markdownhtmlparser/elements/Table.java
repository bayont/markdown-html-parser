package com.example.markdownhtmlparser.elements;

import com.example.markdownhtmlparser.utils.ContentParser;

public class Table implements Element {
    String[][] data;
    boolean isFirstRowHeader;

    public Table(String[][] data, boolean isFirstRowHeader) {
        super();
        this.data = data;
        this.isFirstRowHeader = isFirstRowHeader;
    }

    public String toHTML() {
        String html = "<table>\n";
        for (int i = 0; i < data.length; i++) {
            if(data[i].length == 0) continue;
            html += "\t<tr>\n";
            for (int j = 0; j < data[i].length; j++) {
                html +=  i == 0 && isFirstRowHeader ? "\t\t<th>" + ContentParser.MarkdownToHTML(data[i][j]) + "</th>\n" : "\t\t<td>" + ContentParser.MarkdownToHTML(data[i][j]) + "</td>\n";
            }
            html += "\t</tr>\n";
        }
        html += "</table>\n";
        return html;
    }

    public String toMarkdown() {
        String markdown = "";
        for (int i = 0; i < data.length; i++) {
            markdown += "|";

            for (int j = 0; j < data[i].length; j++) {
                markdown += " " + ContentParser.HTMLToMarkdown(data[i][j]) + " |";
            }
            markdown += "\n";
            if(i == 0 && isFirstRowHeader) {
                markdown += "|";
                for (int j = 0; j < data[i].length; j++) {
                    markdown += " --- |";
                }
                markdown += "\n";
            }
        }
        return markdown;
    }
}
