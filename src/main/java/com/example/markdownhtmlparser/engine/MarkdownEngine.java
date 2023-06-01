package com.example.markdownhtmlparser.engine;

import com.example.markdownhtmlparser.elements.*;
import com.example.markdownhtmlparser.elements.List;
import com.example.markdownhtmlparser.exceptions.InvalidHeadingLevelException;

import java.util.*;

public class MarkdownEngine extends ParserEngine {
    public ElementsList parse(String input) {
        LinkedList<String> lines = new LinkedList<String>(Arrays.asList(splitLines(input)));
        ElementsList elements = new ElementsList();

        while (!lines.isEmpty()) {
           String line = lines.removeFirst();
            if (line.startsWith("#")) {
                try {
                    elements.add(parseHeading(line));
                } catch (InvalidHeadingLevelException e) {
                    e.printStackTrace();
                }
            }
            if(line.startsWith("*")) {
                String[] linesToProcess = getAllLinesWithPrefix("*", lines);
                // @TODO: parse unordered list
            }
        }
        return elements;
    }

    /*

    Wymyślić lepszy sposób na zorganizowanie fromMarkdown!
     */

    public static ElementsList recognizeInlineElements(String input) {
        ElementsList elements = new ElementsList();
        char[] chars = input.toCharArray();
        for(int i = 0; i < chars.length; i++ ) {
            if(chars[i] == '*') {
                if(chars[i + 1] == '*') {
                    int spottedIdx = i+2;
                    elements.add(new BoldText(input.substring(spottedIdx, findClosingTagIndex(input.substring(spottedIdx), "**"))));
                    i+=2;
                } else {
                    int spottedIdx = i+1;
                    elements.add(new ItalicText(input.substring(spottedIdx, findClosingTagIndex(input.substring(spottedIdx), "*"))));
                    i++;
                }
            }
        }
        return elements;
    }

    public static int findClosingTagIndex(String input, String tag) {
        int index = input.indexOf(tag);
        if(index == -1) {
            return -1;
        }
        return index + tag.length();
    }

    private List parseUnorderedList(ElementsList[] items) {
        return new List(ListType.UNORDERED, items);
    }
    private Heading parseHeading(String line) throws InvalidHeadingLevelException {
        int level = 0;
        while (line.charAt(level) == '#') {
            level++;
        }
        String content = line.substring(level + 1);
        return new Heading(level, content);
    }
    private String[] splitLines(String input) {
        return input.split("\n");
    }

    private String[] getAllLinesWithPrefix(String prefix, LinkedList<String> lines) {
        ArrayList<String> linesWithPrefix = new ArrayList<String>();
        for (String line : lines) {
            if (line.startsWith(prefix)) {
                linesWithPrefix.add(line);
            }
            else break;
        }
        return linesWithPrefix.toArray(new String[linesWithPrefix.size()]);
    }

}
