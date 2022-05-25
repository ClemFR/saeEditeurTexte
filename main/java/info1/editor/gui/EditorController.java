/*
 * EditorController.java             18/05/2022
 * IUT de Rodez, Groupe 3, pas de copyright
 */
package info1.editor.gui;

// import static info1.editor.gui.Editor.stageBis;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import info1.editor.gui.Editor;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

/**
 * TODO commenter la responsabilité de cette classe
 *  *
 */
public class EditorController {

    @FXML
    private TextArea textShow;
    @FXML
    public TextField textCommand;

    public String stockCommand;
    protected Stage popup = new Stage();

    @FXML
    protected void onSendAction() {
        quit();
        stockCommand = textCommand.getText();
        // System.out.println(stockCommand);
        textCommand.clear();
    }

    @FXML
    protected void onHelpAction() {
        if (popup.isShowing()) {
            popup.close();
        } else {
            helpStage();
        }
    }

    private void quit() {
        if (textCommand.getText().trim().equals("q")) {
            Platform.exit();
        }
    }
    private void helpStage() {
        popup.setTitle("Commands");
        popup.setHeight(375);
        popup.setWidth(340);
        popup.setX(100);
        VBox racine = new VBox(10);
        ScrollPane slp = new ScrollPane();

        Text titre = new Text("WARNING: Arguments séparés par des virgules\n" +
                "\n" +
                "> t un_fichier : lit le contenu du fichier.\n" +
                "\n" +
                "> f un_fichier : écrit le contenu du tampon \nqui contient le fichier.\n" +
                "\n" +
                "> q : permet de quitter l’éditeur.\n" +
                "\n" +
                "> e : efface les lignes mises en argument.\n" +
                "\n" +
                "> a : insère le texte après la ligne mise en argument.\n" +
                "\n" +
                "> i : insère le texte tapé sous  ligne 1 \nmise en argument.\n" +
                "\n" +
                "> m : modifie la ligne mise en argument par le texte \ntapé après la commande exécutée.\n" +
                "\n" +
                "> c : copie la ligne ou l’intervalle de lignes mis en \nargument à un emplacement écrit après celle-ci.\n" +
                "\n" +
                "> h : possède les mêmes fonctions que la commande \nci-dessus sans faire de distinction entre \nles majuscules et les minuscules.\n" +
                "\n" +
                "> r : effectue une recherche de la chaîne de caractères \nentrée en argument dans l’intervalle de lignes qui précède \ncelle-ci. " +
                "Il est possible de ne pas mettre d’intervalles. \nAlors la recherche s’effectuera dans tout le texte.\n" +
                "\n" +
                "> s : substitue une chaine de caractères par une autre qui \nsuit celle-ci en argument dans l’intervalle de lignes entré \nen premier argument. " +
                "Il est possible de ne pas mettre \nd’intervalles. Alors la substitution s’effectuera \ndans tout le texte.\n" +
                "\n");
        slp.setContent(titre);
        racine.getChildren().add(slp);
        Scene scene = new Scene(racine);
        popup.setScene(scene);
        popup.show();

    }
}