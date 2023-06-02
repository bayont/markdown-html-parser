package com.example.markdownhtmlparser.engine;

import com.example.markdownhtmlparser.elements.ElementsList;
import com.example.markdownhtmlparser.elements.HorizontalRule;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLEngine extends ParserEngine {
    public ElementsList parse(String input) {
        // parse input which is in HTML to ElementsList
        ElementsList elements = new ElementsList();
        List<HTMLElement> htmlElements = parseHTML(input);

        return elements;

    }

    public static List<HTMLElement> parseHTML(String html) {
        List<HTMLElement> elements = new ArrayList<>();
        Pattern pattern = Pattern.compile("<(.*?)>(.*?)</\\1>|<([^/][^>]*)/>");
        Matcher matcher = pattern.matcher(html);

        boolean hasMatch = false;
        while (matcher.find()) {
            hasMatch = true;
            String tag = matcher.group(1) != null ? matcher.group(1) : matcher.group(3);
            String innerHTML = matcher.group(2);
            List<HTMLElement> children = parseHTML(innerHTML);
            elements.add(new HTMLElement(tag, children));
        }

        if(!hasMatch) {
            elements.add(new HTMLElement("", html));
        }

        return elements;
    }

}

class HTMLElement {
    public String tag;
    public String content;
    public List<HTMLElement> children;
    public HTMLElement(String tag, List<HTMLElement> children) {
        this.tag = tag;
        this.children = children;
    }
    public HTMLElement(String tag, String content) {
        this.tag = tag;
        this.content = content;
    }


}