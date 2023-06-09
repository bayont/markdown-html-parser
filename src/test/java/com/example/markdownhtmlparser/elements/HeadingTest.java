package com.example.markdownhtmlparser.elements;

import com.example.markdownhtmlparser.exceptions.InvalidHeadingLevelException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeadingTest {

    @Test
    void invalidLevel() {
        assertThrows(InvalidHeadingLevelException.class, () -> new Heading(0, "Test"));
        assertThrows(InvalidHeadingLevelException.class, () -> new Heading(7, "Test2"));
    }

    @Test
    void toHTML() throws InvalidHeadingLevelException {
        Heading heading = new Heading(1, "Test");
        String expected = "<h1>Test</h1>";
        String actual = heading.toHTML();
        assertEquals(expected, actual);
    }

    @Test
    void toMarkdown() throws InvalidHeadingLevelException {
        Heading heading = new Heading(1, "Test");
        String expected = "# Test";
        String actual = heading.toMarkdown();
        assertEquals(expected, actual);
    }
}