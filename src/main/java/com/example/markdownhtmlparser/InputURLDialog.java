package com.example.markdownhtmlparser;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class InputURLDialog extends TextInputDialog{
    public String open(String title, String header, String content) {
        this.setTitle(title);
        this.setHeaderText(header);
        this.setContentText(content);
        Optional<String> result = this.showAndWait();
        return result.orElse("");
    }
}
