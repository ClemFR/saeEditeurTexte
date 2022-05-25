package info1.editor.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import info1.editor.gui.EditorController;
import java.io.IOException;

/**
 * TODO commenter la responsabilité de cette classe
 *
 * @author gabym
 */
public class Editor extends Application{

    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Editor.class.getResource("EditeurScene.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 480);


        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
