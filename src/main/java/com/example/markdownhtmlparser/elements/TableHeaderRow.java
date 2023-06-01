package com.example.markdownhtmlparser.elements;

public class TableHeaderRow extends Element {
    private TableCell[] tableCells;

    public TableHeaderRow(TableCell[] tableCells) {
        this.tableCells = tableCells;
    }

    public String toHTML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<tr>");
        for (TableCell tableCell : tableCells) {
            stringBuilder.append("<th>" + tableCell.toHTML() + "</th>");
        }
        stringBuilder.append("</tr>");
        return stringBuilder.toString();
    }

    public String toMarkdown() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("| ");
        for (TableCell tableCell : tableCells) {
            stringBuilder.append(tableCell.toMarkdown() + " | ");
        }
        stringBuilder.append("\n");
        stringBuilder.append("| --- |");
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
