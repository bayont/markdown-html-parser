package com.example.markdownhtmlparser.elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListElementTest {

    @Test
    void toHTMLOrdered() {
        ListElement list = new ListElement(ListElementType.ORDERED,new String[]{"line1", "line2"});
        String expected = "<ol>\n\t<li>line1</li>\n\t<li>line2</li>\n</ol>";
        String actual = list.toHTML();
        assertEquals(expected, actual);
    }

    @Test
    void toMarkdownOrdered() {
        ListElement list = new ListElement(ListElementType.ORDERED,new String[]{"line1", "line2"});
        String expected = "1. line1\n2. line2";
        String actual = list.toMarkdown();
        assertEquals(expected, actual);
    }

    @Test
    void toHTMLUnordered() {
        ListElement list = new ListElement(ListElementType.UNORDERED,new String[]{"line1", "line2"});
        String expected = "<ul>\n\t<li>line1</li>\n\t<li>line2</li>\n</ul>";
        String actual = list.toHTML();
        assertEquals(expected, actual);
    }

    @Test
    void toMarkdownUnordered() {
        ListElement list = new ListElement(ListElementType.UNORDERED,new String[]{"line1", "line2"});
        String expected = "* line1\n* line2";
        String actual = list.toMarkdown();
        assertEquals(expected, actual);
    }
}