module info1.editor.gui {
    requires javafx.controls;
    requires javafx.fxml;


    opens info1.editor.gui to javafx.fxml;
    exports info1.editor.gui;
}