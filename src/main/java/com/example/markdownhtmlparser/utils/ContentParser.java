package com.example.markdownhtmlparser.utils;

public class ContentParser {
    public static String HTMLToMarkdown(String input) {
        if(input.matches(".*<b>.*</b>.*")) {
            input = input.replaceAll("<b>(.*)</b>", "**$1**");
        }
        if(input.matches(".*<i>.*</i>.*")) {
            input = input.replaceAll("<i>(.*)</i>", "*$1*");
        }
        if(input.matches(".*<code>.*</code>.*")) {
            input = input.replaceAll("<code>(.*)</code>", "`$1`");
        }
        if(input.matches(".*<a href=\"([^<>]*)\">([^<>]*)</a>.*")) {
            input = input.replaceAll("<a href=\"([^<>]*)\">([^<>]*)</a>", "[$2]($1)");
        }
        if(input.matches(".*<a href=\"([^<>]*)\".*/>.*")) {
            input = input.replaceAll("<a href=\"([^<>]*)\".*/>", "[]($1)");
        }
        if(input.matches(".*<img src=\"(.*)\" alt=\"(.*)\" />.*")) {
            input = input.replaceAll("<img src=\"(.*)\" alt=\"(.*)\" />", "![$2]($1)");
        }
        if(input.matches(".*<img src=\"(.*)\".*/>.*")) {
            input = input.replaceAll("<img src=\"(.*)\".*/>", "![]($1)");
        }
        return input;
    }

    public static String MarkdownToHTML(String input) {
        if(input.matches(".*\\*\\*.*\\*\\*.*")) {
            input = input.replaceAll("\\*\\*(.*)\\*\\*", "<b>$1</b>");
        }
        if(input.matches(".*\\*.*\\*.*")) {
            input = input.replaceAll("\\*(.*)\\*", "<i>$1</i>");
        }
        if(input.matches(".*`.*`.*")) {
            input = input.replaceAll("`(.*?)`", "<code>$1</code>");
        }
        if(input.matches(".*[^!]\\[.*\\]\\(.*\\).*")) {
            input = input.replaceAll("(?<!!)\\[(.*?)\\]\\((.*?)\\)", "<a href=\"$2\">$1</a>");
        }
        if(input.matches(".*!\\[.*\\]\\(.*\\).*")) {
            input = input.replaceAll("!\\[(.*?)\\]\\((.*?)\\)", "<img src=\"$2\" alt=\"$1\" />");
        }
        return input;
    }
}
