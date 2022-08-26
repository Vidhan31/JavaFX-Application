module com.javafxapp.javafxapplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    //requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    //requires eu.hansolo.tilesfx;

    opens com.javafxapp.javafxapplication to javafx.fxml;
    exports com.javafxapp.javafxapplication;
    exports Assignments;
    opens Assignments to javafx.fxml;
}