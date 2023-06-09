package com.example.markdownhtmlparser.validation.html;

import com.example.markdownhtmlparser.utils.RegexMatcher;
import com.example.markdownhtmlparser.validation.Validator;

import java.util.Arrays;
import java.util.Stack;

public class HTMLValidator extends Validator {

    @Override
    public String validate(String html) throws HTMLValidationError {
        checkForInvalidTags(html);
        checkForForbiddenTags(html);
        checkIfAttributesAreQuoted(html);
        return html;
    }

    private static void checkIfAttributesAreQuoted(String html) throws HTMLValidationError {
        String[] tags = html.split("<");
        for (String tag : tags) {
            if (tag.contains(">")) {
                String[] tagParts = tag.split(">")[0].split(" ");
                if (tagParts.length > 1) {
                    String[] attributes = Arrays.stream(Arrays.copyOfRange(tagParts, 1, tagParts.length)).map(s -> s.replaceAll("/", "")).toArray(String[]::new);
                    for (String attribute : attributes) {
                        if (attribute.contains("=")) {
                            String[] attributeParts = attribute.split("=");
                            if (attributeParts.length > 1) {
                                String attributeName = attributeParts[0];
                                String attributeValue = attributeParts[1];
                                if (!attributeValue.startsWith("\"") || !attributeValue.endsWith("\"")) {
                                    throw new AttributeSyntaxError(attributeName + " not quoted");
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private static void checkForForbiddenTags(String html) throws HTMLValidationError {
        String[] tags = html.split("<");
        for (String tag : tags) {
            if (tag.contains(">")) {
                String[] tagParts = tag.split(">");
                if (tagParts.length > 1) {
                    String tagName = tagParts[0];
                    if (tagName.equals("script") || tagName.equals("style")) {
                        throw new ForbiddenTagError(tagName);
                    }
                }
            }
        }
    }
    private static void checkForInvalidTags(String html) throws UnclosedTagError, OpeningTagNotFoundError {
        Stack<String> stack = new Stack<>();
        int index = 0;

        while (index < html.length()) {
            int tagStart = html.indexOf('<', index);
            if (tagStart == -1)
                break;

            int tagEnd = html.indexOf('>', tagStart);
            if (tagEnd == -1)
                break;

            boolean isClosingTag = html.charAt(tagStart + 1) == '/';
            boolean isSelfClosingTag = html.charAt(tagEnd - 1) == '/';

            if (!isClosingTag) {
                String tagName = html.substring(tagStart + 1, tagEnd).split("\\s+")[0];
                stack.push(tagName);
            } else {
                String closingTagName = html.substring(tagStart + 2, tagEnd).split("\\s+")[0];

                if (stack.isEmpty()) {
                    throw new OpeningTagNotFoundError(closingTagName);
                }
                String openingTagName = stack.pop();

                if (!closingTagName.equalsIgnoreCase(openingTagName)) {
                    throw new UnclosedTagError(openingTagName);
                }
            }

            if (isSelfClosingTag) {
                stack.pop();
                index = tagEnd + 1;
            } else {
                index = tagEnd + 1;
                int nextTagStart = html.indexOf('<', index);
                if (nextTagStart != -1) {
                    index = nextTagStart;
                } else {
                    break;
                }
            }
        }

        if(!stack.isEmpty()) {
            throw new UnclosedTagError(stack.pop());
        }
    }

}

