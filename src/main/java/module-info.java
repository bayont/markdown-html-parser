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
}