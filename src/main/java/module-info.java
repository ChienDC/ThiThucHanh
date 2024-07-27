module org.example.thithuchanh {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens org.example.thithuchanh to javafx.fxml;
    exports org.example.thithuchanh;
}