package info1.editor.tests;

import info1.editor.backend.File;

import java.io.IOException;

public class TestFile {

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
            File file = new File("ceci est un chemin invalide");
            testOk = false;
        } catch (IOException e) {
            testOk = true;
        }

        try {
            File file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierLigneSup75char.txt");
            testOk &= false;
        } catch (IndexOutOfBoundsException e) {
            testOk &= true;
        } catch (IOException e) {
            testOk &= false;
            System.out.println("Erreur lors du chargement du fichier. Vérifiez que les fichiers de tests existe bien.");
        }

        try {
            File file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierNbLigne150.txt");
            testOk &= false;
        } catch (IndexOutOfBoundsException e) {
            testOk &= true;
        } catch (IOException e) {
            testOk &= false;
            System.out.println("Erreur lors du chargement du fichier. Vérifiez que les fichiers de tests existe bien.");
        }

        return testOk;
    }
}
