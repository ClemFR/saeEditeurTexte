package info1.editor.tests.file;

import info1.editor.backend.File;
import info1.editor.exception.FileNotFoundException;
import info1.editor.exception.LineToLongException;

public class TestEdit {

    public static boolean launch() {

        boolean testOk;
        testOk = true;

        /* Edition d'une ligne à un index valide */
        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.edit(0, "test");
            testOk &= f.getContent()[0].equals("test");
        } catch (FileNotFoundException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        }

        /* Edition d'une ligne à un index invalide (negatif) */
        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.edit(-1, "test");
            testOk &= false;
        } catch (FileNotFoundException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Edition d'une avec une ligne de remplacement trop longue */
        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.edit(0, "line > char max aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                               + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            testOk &= false;
        } catch (FileNotFoundException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        } catch (LineToLongException expectedError) {
            testOk &= true;
        }


        return testOk;
    }

}
