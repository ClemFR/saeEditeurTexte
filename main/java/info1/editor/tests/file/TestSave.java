package info1.editor.tests.file;

import info1.editor.backend.File;

import java.io.IOException;

public class TestSave {

    //TODO: finir ce test
    public static boolean testSave() {
        try {
            File file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier. Vérifiez que les fichiers de tests existe bien.");
        }

        return false;
    }
}
