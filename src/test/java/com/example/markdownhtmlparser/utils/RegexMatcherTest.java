package com.example.markdownhtmlparser.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexMatcherTest {

    @Test
    void shouldFindSingleGroup() {
        assertEquals("b", RegexMatcher.getMatchedGroups("a(b)c", ".*\\((.*)\\).*")[0]);
    }

    @Test
    void shouldFindManyGroups() {
        String[] expected = {"b", "d"};
        String[] actual = RegexMatcher.getMatchedGroups("a(b)c(d)e", ".*\\((.*)\\).*\\((.*)\\).*");
        assertArrayEquals(expected, actual);
    }
}