package com.example.markdownhtmlparser;

import com.example.markdownhtmlparser.io.ParserFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.*;

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
        outputTextArea.setText(inputTextArea.getText());
    }

    @FXML
    protected void onInputFileButtonClicked(ActionEvent event) {
        try {
            inputTextArea.setText(ParserFile.readFromFile(pickFile()));
        } catch (IOException ignored) {
        }
    }

    private File pickFile() {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(ParserApplication.primaryStage);
    }

    @FXML
    public void initialize() {
        this.evaluateLabels();
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