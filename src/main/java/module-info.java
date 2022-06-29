module main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires opencsv;

    opens main to javafx.fxml;

    exports main;
}
