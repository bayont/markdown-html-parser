package com.example.markdownhtmlparser;

import com.example.markdownhtmlparser.elements.Element;
import com.example.markdownhtmlparser.elements.ElementsList;
import com.example.markdownhtmlparser.engine.HTMLEngine;
import com.example.markdownhtmlparser.engine.MarkdownEngine;
import com.example.markdownhtmlparser.engine.ParserEngine;
import com.example.markdownhtmlparser.io.Files;
import com.example.markdownhtmlparser.io.HttpClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.URI;
import java.util.Optional;

public class ParserController {
    @FXML
    private Label welcomeText;

    @FXML
    private Label inputLabel;

    @FXML
    private Label outputLabel;

    @FXML
    private TextArea inputTextArea;

    @FXML
    private TextArea outputTextArea;
    
    private ParserMode parserMode = ParserMode.HTML_TO_MARKDOWN;

    @FXML
    public void initialize() {
        this.evaluateLabels();
    }

    @FXML
    protected void onSwitchParserModeButtonClicked(ActionEvent event) {
        parserMode = parserMode == ParserMode.HTML_TO_MARKDOWN ? ParserMode.MARKDOWN_TO_HTML : ParserMode.HTML_TO_MARKDOWN;
        String temp = inputTextArea.getText();
        inputTextArea.setText(outputTextArea.getText());
        outputTextArea.setText(temp);
        evaluateLabels();
    }

    @FXML
    protected void onParseButtonClicked(ActionEvent event) {
        // TODO: Use actual parser here
        ParserEngine engine = parserMode == ParserMode.HTML_TO_MARKDOWN ? new HTMLEngine() : new MarkdownEngine();
        ElementsList list = engine.parse(inputTextArea.getText());
        outputTextArea.setText(parserMode == ParserMode.HTML_TO_MARKDOWN ? list.toMarkdown() : list.toHTML());
    }

    @FXML
    protected void onInputFileButtonClicked(ActionEvent event) {
        try {
            inputTextArea.setText(Files.readFromFile(pickFile()));
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    @FXML
    protected void onInputHttpButtonClicked(ActionEvent event) {
        InputURLDialog dialog = new InputURLDialog();
        String result = dialog.open("Load from URL", "Load " + (this.parserMode == ParserMode.HTML_TO_MARKDOWN ? "HTML" : "Markdown") + " from URL", "URL:");
        try {
            inputTextArea.setText(HttpClient.get(URI.create(result).toURL()));
        } catch (IOException exception) {
            inputTextArea.setText("Loading from URL failed!");
        }
    }

    private File pickFile() {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(ParserApplication.primaryStage);
    }

    private String getURLFromDialog() {
        TextInputDialog dialog = new TextInputDialog("walter");
        dialog.setTitle("Load from URL");
        dialog.setHeaderText("Look, a Text Input Dialog");
        dialog.setContentText("Url:");
        Optional<String> result = dialog.showAndWait();

        return result.orElse("");
    }


    private void evaluateLabels() {
        if(parserMode == ParserMode.HTML_TO_MARKDOWN) {
            inputLabel.setText("Input (HTML)");
            outputLabel.setText("Output (Markdown)");
            welcomeText.setText("HTML -> Markdown Parser");

        } else if(parserMode == ParserMode.MARKDOWN_TO_HTML) {
            inputLabel.setText("Input (Markdown)");
            outputLabel.setText("Output (HTML)");
            welcomeText.setText("Markdown -> HTML Parser");
        }
    }
}