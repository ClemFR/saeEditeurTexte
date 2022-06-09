package info1.editor.tests.file;

import info1.editor.backend.File;

import info1.editor.exception.FileLoadingException;

import info1.editor.exception.LineToLongException;

public class TestInsert {

    public static boolean launch() {

        boolean testOk;
        testOk = true;

        /* Insertion d'une ligne à un index invalide */
        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.insert(0, "azerty");
            testOk &= false;

        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Creation de la premiere ligne d'un fichier vierge */
        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierVide.txt");
            f.insert(1, "azerty");
            testOk &= true;


        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= false;
        }

        /* Insertion d'une ligne à un index valide */
        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.insert(1, "azerty");
            testOk &= true;


        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= false;
        }

        /* Insertion d'une ligne dans un fichier plein */
        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierDernieresLignes.txt");
            f.insert(1, "azerty");
            testOk &= false;


        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Insertion d'une ligne avec plus de 75 caracteres */
        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.insert(1, "line > 75 char aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                                  + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            testOk &= false;


        } catch (LineToLongException expectedError) {
            testOk &= true;
        }
        return testOk;
    }
}
