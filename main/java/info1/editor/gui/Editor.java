package info1.editor.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
