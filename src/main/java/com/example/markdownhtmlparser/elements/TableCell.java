package com.example.markdownhtmlparser.elements;

public class TableCell extends Element{
    private ElementsList elementsList;

    public TableCell(ElementsList elementsList) {
        this.elementsList = elementsList;
    }

    public String toHTML() {
        return elementsList.toHTML();
    }

    public String toMarkdown() {
        return elementsList.toMarkdown();
    }
}
