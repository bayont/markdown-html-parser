package com.example.markdownhtmlparser.engine;

import com.example.markdownhtmlparser.elements.*;
import com.example.markdownhtmlparser.elements.ListElement;
import com.example.markdownhtmlparser.exceptions.InvalidHeadingLevelException;

import java.util.*;

public class MarkdownEngine extends ParserEngine {
    public ElementsList parse(String input) throws InvalidHeadingLevelException {
        LinkedList<String> lines = new LinkedList<String>(Arrays.asList(splitLines(input)));
        ElementsList elements = new ElementsList();

        while (!lines.isEmpty()) {
           String line = lines.getFirst();
            if (line.matches("^#{1,6} .*") && !line.matches("^#{7,} .*")) {
                lines.removeFirst();
                elements.add(parseHeading(line));
            }
            else if(line.matches("^\\* .*")) {
                String[] linesToProcess = getAllLinesWithPrefix("^\\* .*", lines);
                elements.add(parseList(ListElementType.UNORDERED, linesToProcess));
            }
            else if(line.matches("^\\d+\\. .*")) {
                String[] linesToProcess = getAllLinesWithPrefix("^\\d+\\. .*", lines);
                elements.add(parseList(ListElementType.ORDERED, linesToProcess));
            }
            else if(line.matches("^\\| .*")) {
                String[] linesToProcess = getAllLinesWithPrefix("^\\| .*", lines);
                elements.add(parseTable(linesToProcess));
            }
            else if(line.matches("^> .*")) {
                String[] linesToProcess = getAllLinesWithPrefix("^> .*", lines);
                elements.add(parseBlockQuote(linesToProcess));
            }
            else if(line.matches("^```.*")) {
                String language = line.substring(3);
                String[] linesToProcess = getAllLinesUntilPrefix("^```.*", lines);
                elements.add(parseCode(linesToProcess, language));
            }
            else if(line.matches("^---.*")) {
                lines.removeFirst();
                elements.add(parseHorizontalRule());
            }
            else if(line == "") {
                elements.add(parseParagraph(lines.removeFirst()));
            }
            else {
                elements.add(parseParagraph(lines.removeFirst()));
            }
        }
        return elements;
    }

    private Paragraph parseParagraph(String line) {
        return new Paragraph(new String[]{line});
    }
    private Heading parseHeading(String line) throws InvalidHeadingLevelException {
        int level = 0;
        while (line.charAt(level) == '#') {
            level++;
        }
        String content = line.substring(level + 1);
        return new Heading(level, content);
    }
    private ListElement parseList(ListElementType type, String[] lines) {
        String[] strippedLines = Arrays.stream(lines).map(line -> type == ListElementType.UNORDERED ? line.substring(2) : line.substring(line.indexOf('.') + 2)).toArray(String[]::new);
        return new ListElement(type, strippedLines);
    }
    private Table parseTable(String[] lines) {
        String[][] data = Arrays.stream(lines).map(line -> Arrays.stream(line.split("\\|")).filter(l -> l != "").map(l->l.trim()).toArray(String[]::new)).toArray(String[][]::new);
        if(data[1][0].matches("---+")) {
            data[1] = new String[]{};
            return new Table(data, true);
        }

        return new Table(data, false);
    }
    private BlockQuote parseBlockQuote(String[] lines) {
        String[] strippedLines = Arrays.stream(lines).map(line -> line.substring(2)).toArray(String[]::new);
        return new BlockQuote(strippedLines);
    }
    private Code parseCode(String[] lines, String language) {
        if(language == null) {
            return new Code(lines);
        }
        return new Code(lines, language);
    }
    private HorizontalRule parseHorizontalRule() {
        return new HorizontalRule();
    }
    private String[] splitLines(String input) {
        return input.split("\n");
    }

    private String[] getAllLinesWithPrefix(String regex, LinkedList<String> lines) {

        ArrayList<String> linesWithPrefix = new ArrayList<String>();
        String[] linesArr = lines.toArray(new String[lines.size()]);
        for (String line : linesArr) {
            if (line.matches(regex)) {
                linesWithPrefix.add(line);
                lines.removeFirst();
            }
            else break;
        }
        return linesWithPrefix.toArray(new String[linesWithPrefix.size()]);
    }

    private String[] getAllLinesUntilPrefix(String regex, LinkedList<String> lines) {
        ArrayList<String> linesWithPrefix = new ArrayList<String>();
        String[] linesArr = lines.toArray(new String[lines.size()]);
        lines.removeFirst();
        for (String line : linesArr) {
            if (line.matches(regex)) {
                lines.removeFirst();
                break;
            }
            else {
                linesWithPrefix.add(line);
                lines.removeFirst();
            }
        }
        return linesWithPrefix.toArray(new String[linesWithPrefix.size()]);
    }

}
