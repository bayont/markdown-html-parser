package com.example.markdownhtmlparser;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ParserApplication extends Application {
    public static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ParserApplication.class.getResource("parser-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("markdown-html-parser");
        stage.setScene(scene);
        stage.show();
        primaryStage = stage;
    }

    public static void main(String[] args) {
        launch();
    }
}