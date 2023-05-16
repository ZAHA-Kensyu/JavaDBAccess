module com.example.dbaccess {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.dbaccess to javafx.fxml;
    exports com.example.dbaccess;
}