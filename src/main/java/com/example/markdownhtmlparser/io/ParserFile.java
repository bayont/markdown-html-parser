package com.example.markdownhtmlparser.io;

import java.io.*;

public class ParserFile {
    public static String readFromFile(File file) {
        if (file == null) return "";
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            return "";
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException ignored) {
            return "";
        }
        return sb.toString();
    }
}
