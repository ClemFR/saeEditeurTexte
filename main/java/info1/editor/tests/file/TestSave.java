package info1.editor.tests.file;

import info1.editor.backend.File;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Objects;

public class TestSave {

    public static boolean launch() {

        boolean testOk = true;

        /* Suppression des fichiers des anciens tests */
        cleanFiles();

        /* Fichier avec des lignes vides */
        File file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.save(Path.of("src/main/java/info1/editor/tests/enregistrement/testFichierOk.txt"));
            File loadedFile = new File("src/main/java/info1/editor/tests/enregistrement/testFichierOk.txt");

            String[] result = loadedFile.getContent();
            String[] expectedResult = file.getContent();

            for (int i = 0; i < expectedResult.length; i++) {
                testOk &= Objects.equals(result[i], expectedResult[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            testOk = false;
        }

        /* Fichier complement rempli */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierDernieresLignes.txt");
        try {
            file.save(Path.of("src/main/java/info1/editor/tests/enregistrement/testFichierDernieresLignes.txt"));
            File loadedFile = new File("src/main/java/info1/editor/tests/enregistrement/testFichierDernieresLignes.txt");

            String[] result = loadedFile.getContent();
            String[] expectedResult = file.getContent();

            for (int i = 0; i < expectedResult.length; i++) {
                testOk &= Objects.equals(result[i], expectedResult[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            testOk = false;
        }

        /* Fichier complement vide */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierVide.txt");
        try {
            file.save(Path.of("src/main/java/info1/editor/tests/enregistrement/testFichierVide.txt"));
            File loadedFile = new File("src/main/java/info1/editor/tests/enregistrement/testFichierVide.txt");

            String[] result = loadedFile.getContent();
            String[] expectedResult = file.getContent();

            for (int i = 0; i < expectedResult.length; i++) {
                testOk &= Objects.equals(result[i], expectedResult[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            testOk = false;
        }


        /* Creation d'un fichier qui n'existe pas sur le disque */
        file = new File("src/main/java/info1/editor/tests/enregistrement/nouveauFichier.txt");


        cleanFiles();

        return testOk;
    }


    private static void cleanFiles() {
        java.io.File directory = new java.io.File("src/main/java/info1/editor/tests/enregistrement/");
        for (java.io.File file : directory.listFiles()) {
            file.delete();
        }
    }
}
