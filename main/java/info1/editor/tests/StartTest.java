package info1.editor.tests;

import info1.editor.backend.File;
import info1.editor.tests.file.*;

import java.io.IOException;

public class StartTest {
    /**
     * Debut du chemin menant au projet, utilisé pour construire le chemin de chaque fichier.
     * Il faut spécifier le dossier contanant le dossier "src"
     */
    private static final String PATH_BASE = "" ; //""C:/Users/Clement_L/Documents/IUT/S2/programmation/eclipseWorkspace/";

    public static void main(String[] args) {

        if (TestFile.launch()) {
            System.out.println("Test file OK");
        } else {
            System.out.println("Test file ECHEC");
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
    }
}
