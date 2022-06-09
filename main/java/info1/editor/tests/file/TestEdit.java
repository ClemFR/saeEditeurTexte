package info1.editor.tests.file;

import info1.editor.backend.File;
import info1.editor.exception.FileLoadingException;
import info1.editor.exception.LineToLongException;

public class TestEdit {

    public static boolean launch() {

        boolean testOk;
        testOk = true;

        /* Edition d'une ligne à un index valide */
        File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        f.edit(0, "test");
        testOk &= f.getContent()[0].equals("test");

        /* Edition d'une ligne à un index invalide (negatif) */
        try {
            f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.edit(-1, "test");
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Edition d'une avec une ligne de remplacement trop longue */
        try {
            f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.edit(0, "line > char max aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
                               + "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            testOk &= false;
        } catch (LineToLongException expectedError) {
            testOk &= true;
        }


        return testOk;
    }

}
