package info1.editor.tests.file;

import info1.editor.backend.File;
import info1.editor.exception.FileNotFoundException;

import java.io.IOException;

public class TestInsert {

    public static boolean launch() {

        boolean testOk;
        testOk = true;

        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.insert(0, "azerty");
            testOk &= false;
        } catch (FileNotFoundException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierVide.txt");
            f.insert(1, "azerty");
            testOk &= true;
        } catch (FileNotFoundException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= false;
        }

        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.insert(1, "azerty");
            testOk &= true;
        } catch (FileNotFoundException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= false;
        }

        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierDernieresLignes.txt");
            f.insert(1, "azerty");
            testOk &= false;
        } catch (FileNotFoundException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }
        return testOk;
    }
}
