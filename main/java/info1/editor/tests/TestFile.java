package info1.editor.tests;

import info1.editor.backend.File;

import java.io.IOException;
import java.nio.file.InvalidPathException;

public class TestFile {
    /**
     * Debut du chemin menant au projet, utilisé pour construire le chemin de chaque fichier.
     * Il faut spécifier le dossier contanant le dossier "src"
     */
    private static final String PATH_BASE = "" ; //""C:/Users/Clement_L/Documents/IUT/S2/programmation/eclipseWorkspace/";

    public static void main(String[] args) {

        if (testFile()) {
            System.out.println("Test file OK");
        } else {
            System.out.println("Test file ECHEC");
        }
    }

    private static boolean testFile() {
        boolean testOk;

        try {
            File file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            testOk = true;
        } catch (IOException | IndexOutOfBoundsException e) {
            testOk = false;
        }

        try {
            File file = new File(PATH_BASE + "ceci est un chemin invalide");
            testOk = false;
        } catch (IOException e) {
            testOk = true;
        }

        try {
            File file = new File(PATH_BASE + "src/main/java/info1/editor/tests/fichierexemple/testFichierLigneSup75char.txt");
            testOk &= false;
        } catch (IndexOutOfBoundsException e) {
            testOk &= true;
        } catch (IOException e) {
            testOk &= false;
        }

        try {
            File file = new File(PATH_BASE + "src/main/java/info1/editor/tests/fichierexemple/testFichierNbLigne150.txt");
            testOk &= false;
        } catch (IndexOutOfBoundsException e) {
            testOk &= true;
        } catch (IOException e) {
            testOk &= false;
        }

        return testOk;
    }

    private static boolean testSave() {
        try {
            File file = new File(PATH_BASE + "src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier. Vérifiez que les fichiers de tests existe bien.");
        }

        return false;
    }
}
