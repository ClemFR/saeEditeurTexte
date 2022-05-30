package info1.editor.tests.file;

import info1.editor.backend.File;
import info1.editor.exception.FileNotFoundException;

public class TestFile {

    public static boolean launch() {
        boolean testOk;

        try {
            new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            testOk = true;
        } catch (FileNotFoundException | IndexOutOfBoundsException e) {
            testOk = false;
        }

        try {
            new File("ceci est un chemin invalide");
            testOk = false;
        } catch (FileNotFoundException expectedError) {
            testOk = true;
        }

        try {
            new File("src/main/java/info1/editor/tests/fichierexemple/testFichierLigneSup75char.txt");
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        } catch (FileNotFoundException e) {
            testOk &= false;
        }

        try {
            new File("src/main/java/info1/editor/tests/fichierexemple/testFichierNbLigne150.txt");
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        } catch (FileNotFoundException e) {
            testOk &= false;
        }

        return testOk;
    }

}
