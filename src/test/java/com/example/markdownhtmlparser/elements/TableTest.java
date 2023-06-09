package com.example.markdownhtmlparser.elements;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TableTest {

    @Test
    void toHTML() {
        Table table = new Table(new String[][]{{"1", "2"}, {"3", "4"}}, true);
        String expected = "<table>\n\t<tr>\n\t\t<th>1</th>\n\t\t<th>2</th>\n\t</tr>\n\t<tr>\n\t\t<td>3</td>\n\t\t<td>4</td>\n\t</tr>\n</table>\n";
        String actual = table.toHTML();
        assertEquals(expected, actual);

    }

    @Test
    void toMarkdown() {
        Table table = new Table(new String[][]{{"1", "2"}, {"3", "4"}}, true);
        String expected = "| 1 | 2 |\n| --- | --- |\n| 3 | 4 |\n";
        String actual = table.toMarkdown();
        assertEquals(expected, actual);
    }
}