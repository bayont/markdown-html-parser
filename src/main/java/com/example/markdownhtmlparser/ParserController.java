package com.example.markdownhtmlparser;

import com.example.markdownhtmlparser.elements.ElementsList;
import com.example.markdownhtmlparser.engine.HTMLEngine;
import com.example.markdownhtmlparser.engine.MarkdownEngine;
import com.example.markdownhtmlparser.engine.ParserEngine;
import com.example.markdownhtmlparser.io.Files;
import com.example.markdownhtmlparser.io.HttpClient;
import com.example.markdownhtmlparser.validation.html.HTMLValidator;
import com.example.markdownhtmlparser.validation.markdown.MarkdownValidator;
import com.example.markdownhtmlparser.validation.Validator;
import com.example.markdownhtmlparser.validation.ValidatorError;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import java.io.*;
import java.net.URI;


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
    protected void onSwitchParserModeButtonClicked() {
        parserMode = parserMode == ParserMode.HTML_TO_MARKDOWN ? ParserMode.MARKDOWN_TO_HTML : ParserMode.HTML_TO_MARKDOWN;
        String temp = inputTextArea.getText();
        inputTextArea.setText(outputTextArea.getText());
        outputTextArea.setText(temp);
        evaluateLabels();
    }

    @FXML
    protected void onParseButtonClicked() {
        ParserEngine engine = parserMode == ParserMode.HTML_TO_MARKDOWN ? new HTMLEngine() : new MarkdownEngine();
        Validator validator = parserMode == ParserMode.HTML_TO_MARKDOWN ? new HTMLValidator() : new MarkdownValidator();
        ElementsList list;
        try {
            list = engine.parse(validator.validate(inputTextArea.getText()));
        }
        catch (ValidatorError error) {
            outputTextArea.setText(error.getMessage());
            return;
        }
        catch (Exception exception) {
            outputTextArea.setText("Parsing failed!");
            return;
        }
        if (list == null) {
            outputTextArea.setText("Parsing failed!");
            return;
        }
        outputTextArea.setText(parserMode == ParserMode.HTML_TO_MARKDOWN ? list.toMarkdown() : list.toHTML());
    }

    @FXML
    protected void onInputFileButtonClicked() {
        try {
            inputTextArea.setText(Files.readFromFile(pickFile()));
        } catch (IOException exception) {
            System.out.println(exception);
        }
    }

    @FXML
    protected void onInputHttpButtonClicked() {
        InputURLDialog dialog = new InputURLDialog();
        String result = dialog.open("Load from URL", "Load " + (this.parserMode == ParserMode.HTML_TO_MARKDOWN ? "HTML" : "Markdown") + " from URL", "URL:");
        try {
            inputTextArea.setText(HttpClient.get(URI.create(result).toURL()));
        } catch (IOException exception) {
            inputTextArea.setText("Loading from URL failed!");
        }
        catch (Exception exception) {
            inputTextArea.setText("Invalid URL!");
        }
    }

    private File pickFile() {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(ParserApplication.primaryStage);
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