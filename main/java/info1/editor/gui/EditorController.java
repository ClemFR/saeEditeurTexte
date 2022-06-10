/*
 * EditorController.java             18/05/2022
 * IUT de Rodez, Groupe 3, pas de copyright
 */
package info1.editor.gui;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import info1.editor.backend.File;
import java.io.IOException;
import java.nio.file.Path;


/**
 * Manage all the commands and their actions
 */
public class EditorController {

    @FXML
    private TextArea textShow;
    @FXML
    public TextField textCommand;

    public String stockCommand;

    public String[] splitCommande;
    public String memoryPath;

    public File f;
    protected Stage popup = new Stage();

    public Alert a = new Alert(Alert.AlertType.ERROR);

    @FXML
    public void onSendAction() throws IOException {

        int valeur = -1;
        boolean commandeOk;
        String[] commandesEnregistres = {"t", "f", "q", "?", "e", "a", "i", "m", "c", "d"};

        try {
            splitCommande = new String[]{textCommand.getText().substring(0, textCommand.getText().indexOf(" ")), textCommand.getText().substring(textCommand.getText().indexOf(" ") + 1)};
        } catch (Exception e) {
            splitCommande = new String[]{textCommand.getText()};
            System.out.println("Erreur splitcommande gettext");
        }

        commandeOk = false;
         try {
            for (int i = 0; i < commandesEnregistres.length && !commandeOk; i++) {
                commandeOk = splitCommande[0].equals(commandesEnregistres[i]);
                if (commandeOk) { valeur = i; }
            }

         } catch (Exception e) {
             errorCommandBox();
             System.out.println("Erreur splitcommande");
         }

        switch(valeur) {
            case -1:
                a.setHeaderText("Erreur, commande inconnue");
                a.show();
                break;

            case 0: //commande t -> ouvrir un fichier & lire contenu

                try {
                    memoryPath = splitCommande[1];
                    writeRead();
                } catch (Exception err) {
                    errorCommandBox();
                    System.out.println("Erreur ouverture/lecture");
                }
                break;

            case 1: //commande f -> sauver le fichier
                System.out.println("Sauvegarde du fichier : " + splitCommande[1]);
                try {
                    f.save(Path.of(splitCommande[1]));
                } catch (Exception e) {
                    errorCommandBox();
                    System.out.println("Erreur save");
                }
                break;

            case 2: //commande q -> quitter
                System.out.println("Au revoir !");
                quit();
                break;

            case 3: //commande ? -> afficher les commandes
                System.out.println("Commandes disponibles :");
                onHelpAction();
                break;

            case 4: //commande e -> détruire lignes (e -> tout effacer
                //                               e 1 -> effacer ligne 1
                //                               e 1, 7 -> effacer ligne 1 à 7)
                System.out.println("Destruction des lignes : " + splitCommande[1]);


                try {

                    String[] parts = splitCommande[1].split(",");

                    if (parts.length > 1) {

                        f.delete(Integer.parseInt(parts[0].trim()),Integer.parseInt(parts[1].trim()) );
                    } else {
                        int number = Integer.parseInt(splitCommande[1]);
                        f.delete(number);
                    }

                    // System.out.println(Arrays.toString(f.getContent()));
                    showText();
                } catch (Exception err) {
                    errorCommandBox();
                    System.out.println("Erreur delete");
                }
                break;

            case 5: // commande a -> ajouter texte a ligne (a 1 bla bla bla ->
                //                                  ajouter à ligne 1 bla bla bla)
                System.out.println("Ajout de ligne : " + splitCommande[1]);


                try {
                    String[] parts = splitCommande[1].split(",", 2);
                    System.out.println(parts[0] + parts[1]);
                    String texteToAdd = parts[1];
                    int line = Integer.parseInt(parts[0]);
                    f.append(line, texteToAdd);
                    showText();
                } catch (Exception err) {
                    errorCommandBox();
                    System.out.println("Erreur Append");
                }
                break;

            case 6: // commande i -> inserer texte debut ligne (i 2 bla bla bla ->
                //                                  insérer à ligne 2 bla bla bla)
                System.out.println("Insertion de ligne : " + splitCommande[1]);
                try {
                    String[] parts = splitCommande[1].split(",", 2);
                    String texteToAdd = parts[1];
                    int line = Integer.parseInt(parts[0]);
                    f.insert(line, texteToAdd);
                    showText();
                } catch (Exception err) {
                    errorCommandBox();
                    System.out.println("Erreur Insert");
                }
                break;

            case 7: // commande m -> modifier texte ligne (m 2 -> voir pdf)
                System.out.println("Modification de ligne : " + splitCommande[1]);
                try {
                    String[] parts = splitCommande[1].split(",");
                    String texteToAdd = parts[1];
                    int line = Integer.parseInt(parts[0].trim());
                    f.edit(line, texteToAdd);
                    showText();
                } catch (Exception err) {
                    errorCommandBox();
                    System.out.println("Erreur Modify");
                }
                break;

            case 8: // commande c -> copier ligne (c 1, 3, 4 | c 1, 6)
                System.out.println("Copie de ligne : " + splitCommande[1]);
                int lineFrom;
                int lineTo;
                int lineWhere;
                try {
                    String[] parts = splitCommande[1].split(",");
                    lineFrom = Integer.parseInt(parts[0].trim());
                    lineTo = Integer.parseInt(parts[1].trim());
                    if (parts.length > 2) {
                        lineWhere = Integer.parseInt(parts[2].trim());
                        f.copy(lineFrom, lineTo, lineWhere);
                    } else {
                        f.copy(lineFrom, lineTo);
                    }
                    showText();
                } catch (Exception e) {
                    errorCommandBox();
                    System.out.println("Erreur Copy");
                }
                break;

            case 9: // commande d -> déplacer la ligne*
                System.out.print("Déplacement de la ligne : " + splitCommande[1]);
                int lineFromMove, lineToMove, lineWhereMove;
                try {
                    String[] parts = splitCommande[1].split(",");
                    lineFromMove = Integer.parseInt(parts[0].trim());
                    lineToMove = Integer.parseInt(parts[1].trim());
                    if (parts.length > 2) {
                        lineWhereMove = Integer.parseInt(parts[2].trim());
                        f.move(lineFromMove, lineToMove, lineWhereMove);
                    } else {
                        f.move(lineFromMove, lineToMove);
                    }
                    showText();
                } catch (Exception e) {
                   errorCommandBox();
                   System.out.println("Erreur Move");
                }
                break;
        }
        stockCommand = textCommand.getText();
        // System.out.println(stockCommand);
        textCommand.clear();
        textCommand.requestFocus();
    }

    @FXML
    protected void onHelpAction() {
        if (popup.isShowing()) {
            popup.close();
        } else {
            helpStage();
        }
        textCommand.requestFocus();
    }

    @FXML
    protected void onSaveAction() throws IOException {
        try {
            f.save();
        } catch (Exception err) {
            a.setHeaderText("Erreur, aucun fichier d'ouvert");
            a.show();
        }
    }


    private void quit() {
        if (textCommand.getText().trim().equals("q")) {
            Platform.exit();
        }
    }

    private void writeRead() {
        try {
            System.out.println("Ouverture du fichier : " + splitCommande[1]);
            f = new File(memoryPath);
            // text = f.loadFile(Paths.get(memoryPath));
            showText();
        } catch (Exception e) {
            e.printStackTrace();
            errorCommandBox();
            System.out.println("Erreur WriteRead()");
            // System.out.println("Erreur lors de l'ouverture du fichier.");
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

    private void errorCommandBox() {
        a.setHeaderText("Erreur, commande inconnue ou invalide");
        a.show();
    }

    private void showText() {
        String toPrint = "";
        for (int i = 0; i < f.getContent().length && f.getContent()[i] != null; i++ ) {
            toPrint += i + ". ";
            if (i < 10) {
                toPrint += "  ";
            }
            toPrint += f.getContent()[i] + "\n";
        }
        // Evite crash si fichier vide
        if (toPrint.length() > 0) {
            toPrint = toPrint.substring(0, toPrint.length()-1);
        }
        textShow.setText(toPrint);
    }
}