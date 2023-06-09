package com.example.markdownhtmlparser.elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElementsListTest {

    @Test
    void toHTML() {
        ElementsList list = new ElementsList();
        list.add(new HorizontalRule());
        list.add(new HorizontalRule());
        list.add(new HorizontalRule());
        list.add(new ListElement(ListElementType.ORDERED,new String[]{"line1", "line2"}));
        String expected = "<hr />\n<hr />\n<hr />\n<ol>\n\t<li>line1</li>\n\t<li>line2</li>\n</ol>\n";
        String actual = list.toHTML();
        assertEquals(expected, actual);
    }

    @Test
    void toMarkdown() {
        ElementsList list = new ElementsList();
        list.add(new HorizontalRule());
        list.add(new HorizontalRule());
        list.add(new HorizontalRule());
        list.add(new ListElement(ListElementType.ORDERED,new String[]{"line1", "line2"}));
        String expected = "---\n---\n---\n1. line1\n2. line2\n";
        String actual = list.toMarkdown();
        assertEquals(expected, actual);

    }
}