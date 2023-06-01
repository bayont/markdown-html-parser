package com.example.markdownhtmlparser.elements;

public abstract class Element {
    public abstract String toHTML();
    public abstract String toMarkdown();
}

/*
Plan jest taki, żeby zostawić tę abstrakcję i dodać składową children (tylko do niektórych klas dziedziczących po Element), która będzie miała typ Element[].
Dodatkowo, trzeba by dodać takie klasy jak NormalText, BoldText, ItalicText, Link, Image, Paragraph, Blockquote, CodeBlock, CodeSpan.
 */
