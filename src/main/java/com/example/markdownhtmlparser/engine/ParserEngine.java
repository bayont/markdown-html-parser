package com.example.markdownhtmlparser.engine;

import com.example.markdownhtmlparser.elements.ElementsList;
import com.example.markdownhtmlparser.exceptions.InvalidHeadingLevelException;

public abstract class ParserEngine {
    public abstract ElementsList parse(String input) throws Exception;
}
