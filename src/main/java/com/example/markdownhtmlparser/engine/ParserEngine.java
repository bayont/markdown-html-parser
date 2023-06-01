package com.example.markdownhtmlparser.engine;

import com.example.markdownhtmlparser.elements.ElementsList;

public abstract class ParserEngine {
    public abstract ElementsList parse(String input);
}
