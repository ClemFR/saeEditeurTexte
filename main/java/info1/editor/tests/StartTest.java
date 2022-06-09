package info1.editor.tests;

import info1.editor.tests.file.*;

public class StartTest {
    /**
     * Debut du chemin menant au projet, utilisé pour construire le chemin de chaque fichier.
     * Il faut spécifier le dossier contanant le dossier "src"
     */
    public static void main(String[] args) {

        if (TestFile.launch()) {
            System.out.println("Test file (constructeur) OK");
        } else {
            System.out.println("Test file (constructeur) ECHEC");
        }

        if (TestDeleteInt.launch()) {
            System.out.println("Test deleteInt OK");
        } else {
            System.out.println("Test deleteInt ECHEC");
        }

        if (TestDeleteIntInt.launch()) {
            System.out.println("Test deleteIntInt OK");
        } else {
            System.out.println("Test deleteIntInt ECHEC");
        }

        if (TestAppend.launch()) {
            System.out.println("Test append OK");
        } else {
            System.out.println("Test append ECHEC");
        }

        if (TestInsert.launch()) {
            System.out.println("Test insert OK");
        } else {
            System.out.println("Test insert ECHEC");
        }

        if (TestEdit.launch()) {
            System.out.println("Test edit OK");
        } else {
            System.out.println("Test edit ECHEC");
        }

        if (TestCopyInt.launch()) {
            System.out.println("Test CopyInt OK");
        } else {
            System.out.println("Test CopyInt ECHEC");
        }

        if (TestCopyIntInt.launch()) {
            System.out.println("Test CopyIntInt OK");
        } else {
            System.out.println("Test CopyIntInt ECHEC");
        }
    }
}