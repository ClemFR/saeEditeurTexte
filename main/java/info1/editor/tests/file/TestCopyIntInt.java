package info1.editor.tests.file;

import info1.editor.backend.File;

public class TestCopyIntInt {

    static final String[][] EXPECTED_RESULT = {

            {
                    "Maître Corbeau, sur un arbre perché,",
                    "Tenait en son bec un fromage.",
                    "Maître Corbeau, sur un arbre perché,",
                    "Tenait en son bec un fromage.",
                    "Maître Renard, par l'odeur alléché,"
            },
    };

    public static boolean launch() {
        boolean testOk = true;
        String[] result;

        /* Tentative d'une copie classique */
        File file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        file.copy(0, 1, -1);
        result = file.getContent();
        for (int i = 0; i < EXPECTED_RESULT[0].length; i++) {
            testOk &= result[i].equals(EXPECTED_RESULT[0][i]);
        }

        /* tentative d'une copie dans un fichier plein */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierDernieresLignes.txt");
        try {
            file.copy(0, 1, 0);
            testOk &= false;
        } catch (IndexOutOfBoundsException e) {
            testOk &= true;
        }

        /* tentative d'une copie d'une ligne dans un indice qui n'existe pas (negatif) */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.copy(0, 4, -2);
            testOk &= false;
        } catch (IndexOutOfBoundsException e) {
            testOk &= true;
        }

        /* tentative d'une copie d'une ligne dans un indice qui n'existe pas (positif) */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.copy(0, 4, 18);
            testOk &= false;
        } catch (IndexOutOfBoundsException e) {
            testOk &= true;
        }

        /* tentative de copie alors que les lignes sont mal réferencés */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.copy(2, 2, 0);
            testOk &= false;
        } catch (IndexOutOfBoundsException e) {
            testOk &= true;
        }

        try {
            file.copy(3, 2, 0);
            testOk &= false;
        } catch (IndexOutOfBoundsException e) {
            testOk &= true;
        }

        return testOk;
    }
}

