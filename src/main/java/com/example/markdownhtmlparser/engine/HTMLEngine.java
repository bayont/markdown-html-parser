package com.example.markdownhtmlparser.engine;

import com.example.markdownhtmlparser.elements.*;
import com.example.markdownhtmlparser.exceptions.InvalidHeadingLevelException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLEngine extends ParserEngine {
    private static String[] INLINE_TAGS = {"strong", "em", "a", "s", "b", "i", "img"};
    public ElementsList parse(String input) throws InvalidHeadingLevelException {
        // parse input which is in HTML to ElementsList
        ElementsList elements = new ElementsList();
        List<HTMLElement> htmlElements = parseHTML(input);
        for(HTMLElement htmlElement : htmlElements) {
            switch(htmlElement.tag) {
                case "h1":
                    elements.add(parseHeading(htmlElement, 1));
                    break;
                case "h2":
                    elements.add(parseHeading(htmlElement, 2));
                    break;
                case "h3":
                    elements.add(parseHeading(htmlElement, 3));
                    break;
                case "h4":
                    elements.add(parseHeading(htmlElement, 4));
                    break;
                case "h5":
                    elements.add(parseHeading(htmlElement, 5));
                    break;
                case "h6":
                    elements.add(parseHeading(htmlElement, 6));
                    break;
                case "p":
                    elements.add(parseParagraph(htmlElement));
                    break;
                case "ul":
                    elements.add(parseList(htmlElement, ListElementType.UNORDERED));
                    break;
                case "ol":
                    elements.add(parseList(htmlElement, ListElementType.ORDERED));
                    break;
                case "blockquote":
                    elements.add(parseBlockQuote(htmlElement));
                    break;
                case "hr":
                    elements.add(parseHorizontalRule());
                    break;
                case "pre":
                    elements.add(parseCode(htmlElement));
                    break;
                case "table":
                    elements.add(parseTable(htmlElement));
                    break;
                default:
                    elements.add(parseParagraph(htmlElement));
                    break;
            }
        }

        return elements;

    }

    private Element parseTable(HTMLElement htmlElement) {
        List<String[]> lines = new ArrayList<>();
        boolean isFirstRowHeader = false;
        for(HTMLElement child : htmlElement.children) {
            if(child.tag.equals("tr")) {
                List<String> items = new ArrayList<>();
                for(HTMLElement cell : child.children) {
                    if(cell.tag.equals("th")) {
                        isFirstRowHeader = true;
                    }
                    items.add(cell.children.stream().map(c -> c.content.trim()).reduce("", String::concat));
                }
                lines.add(items.toArray(new String[0]));
            }
        }
        return new Table(lines.toArray(new String[0][0]), isFirstRowHeader);
    }

    private Element parseCode(HTMLElement htmlElement) {
        return new Code(htmlElement.content.split("\n"));
    }

    private Element parseHorizontalRule() {
        return new HorizontalRule();
    }

    private Element parseBlockQuote(HTMLElement htmlElement) {
        return new BlockQuote(htmlElement.content.split("\n"));
    }

    private Element parseList(HTMLElement htmlElement, ListElementType listType) {
        List<String> lines = new ArrayList<>();
        for(HTMLElement child : htmlElement.children) {
            if(child.tag.equals("li")) {
                lines.add(child.children.stream().map(c -> c.content.trim()).reduce("", String::concat));
            }
        }
        return new ListElement(listType, lines.toArray(new String[0]));
    }

    private Element parseParagraph(HTMLElement htmlElement) {
        return new Paragraph(htmlElement.content.split("\n"));
    }

    private Element parseHeading(HTMLElement htmlElement, int i) throws InvalidHeadingLevelException {
        return new Heading(i, htmlElement.children.get(0).content.trim());
    }

    public static List<HTMLElement> parseHTML(String html) {
        if(html == null) {
            return new ArrayList<>();
        }
        List<HTMLElement> elements = new ArrayList<>();
        Pattern pattern = Pattern.compile("<(.*?)>(.*?)</\\1>|<([^/][^>]*)/>", Pattern.MULTILINE | Pattern.DOTALL);
        Matcher matcher = pattern.matcher(html);

        boolean hasMatch = false;
        while (matcher.find()) {
            hasMatch = true;
            String tag = (matcher.group(1) != null ? matcher.group(1) : matcher.group(3)).trim().split(" ")[0];
            if(Arrays.stream(INLINE_TAGS).anyMatch(tag::equals)) {
                elements.add(new HTMLElement(tag, html));
                continue;
            }
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