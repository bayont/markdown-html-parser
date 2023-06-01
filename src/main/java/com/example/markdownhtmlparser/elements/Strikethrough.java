package com.example.markdownhtmlparser.elements;

public class Strikethrough extends Element{
    private ElementsList elementsList;

    public Strikethrough(ElementsList elementsList) {
        this.elementsList = elementsList;
    }

    public String toHTML() {
        return "<s>" + elementsList.toHTML() + "</s>";
    }

    public String toMarkdown() {
        return "~~" + elementsList.toMarkdown() + "~~";
    }
}
