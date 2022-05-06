module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    opens main to javafx.fxml;
    exports main;
}
