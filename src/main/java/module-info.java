module com.example.markdownhtmlparser {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.markdownhtmlparser to javafx.fxml;
    exports com.example.markdownhtmlparser;
    exports com.example.markdownhtmlparser.io;
    opens com.example.markdownhtmlparser.io to javafx.fxml;
    exports com.example.markdownhtmlparser.engine;
    opens com.example.markdownhtmlparser.engine to javafx.fxml;
    exports com.example.markdownhtmlparser.elements;
    opens com.example.markdownhtmlparser.elements to javafx.fxml;
    exports com.example.markdownhtmlparser.utils;
    opens com.example.markdownhtmlparser.utils to javafx.fxml;
    exports com.example.markdownhtmlparser.validation;
    opens com.example.markdownhtmlparser.validation to javafx.fxml;
    exports com.example.markdownhtmlparser.validation.markdown;
    opens com.example.markdownhtmlparser.validation.markdown to javafx.fxml;
    exports com.example.markdownhtmlparser.validation.html;
    opens com.example.markdownhtmlparser.validation.html to javafx.fxml;
}