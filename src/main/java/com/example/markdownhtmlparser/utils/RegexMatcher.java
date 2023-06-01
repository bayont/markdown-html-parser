package com.example.markdownhtmlparser.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatcher {
    public static String[] getMatchedGroups(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        if(!matcher.matches()) {
            return null;
        }
        String[] groups = new String[matcher.groupCount()];
        for(int i = 0; i < matcher.groupCount(); i++) {
            groups[i] = matcher.group(i + 1);
        }
        return groups;
    }
}
