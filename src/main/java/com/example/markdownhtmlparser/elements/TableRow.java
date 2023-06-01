package com.example.markdownhtmlparser.elements;

public class TableRow extends Element {
    private TableCell[] tableCells;

    public TableRow(TableCell[] tableCells) {
        this.tableCells = tableCells;
    }

    public String toHTML() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<tr>");
        for (TableCell tableCell : tableCells) {
            stringBuilder.append("<td>" + tableCell.toHTML() + "</td>");
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
        return stringBuilder.toString();
    }
}
