package com.example.markdownhtmlparser.io;

import java.io.*;

public class Files {
    public static String readFromFile(File file) throws IOException {
        if (file == null) throw new IOException("File is null");
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
            sb.append("\n");
        }
        return sb.toString();
    }
}
