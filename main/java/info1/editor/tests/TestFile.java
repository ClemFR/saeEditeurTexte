package info1.editor.tests;

import info1.editor.backend.File;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.util.Arrays;

public class TestFile {
    /**
     * Debut du chemin menant au projet, utilisé pour construire le chemin de chaque fichier.
     * Il faut spécifier le dossier contanant le dossier "src"
     */
    private static final String PATH_BASE = "" ; //""C:/Users/Clement_L/Documents/IUT/S2/programmation/eclipseWorkspace/";

    public static void main(String[] args) {

        if (testFile()) {
            System.out.println("Test file OK");
        } else {
            System.out.println("Test file ECHEC");
        }

        if (testDeleteInt()) {
            System.out.println("Test deleteInt OK");
        } else {
            System.out.println("Test deleteInt ECHEC");
        }
    }

    private static boolean testFile() {
        boolean testOk;

        try {
            new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            testOk = true;
        } catch (IOException | IndexOutOfBoundsException e) {
            testOk = false;
        }

        try {
            new File(PATH_BASE + "ceci est un chemin invalide");
            testOk = false;
        } catch (IOException e) {
            testOk = true;
        }

        try {
            new File(PATH_BASE + "src/main/java/info1/editor/tests/fichierexemple/testFichierLigneSup75char.txt");
            testOk &= false;
        } catch (IndexOutOfBoundsException e) {
            testOk &= true;
        } catch (IOException e) {
            testOk &= false;
        }

        try {
            new File(PATH_BASE + "src/main/java/info1/editor/tests/fichierexemple/testFichierNbLigne150.txt");
            testOk &= false;
        } catch (IndexOutOfBoundsException e) {
            testOk &= true;
        } catch (IOException e) {
            testOk &= false;
        }

        return testOk;
    }

    //TODO: finir ce test
    private static boolean testSave() {
        try {
            File file = new File(PATH_BASE + "src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement du fichier. Vérifiez que les fichiers de tests existe bien.");
        }

        return false;
    }

    /**
     * Test for the simple delete of a single line
     * @return true if test is ok, false otherwise
     */
    private static boolean testDeleteInt() {

        boolean testOk;
        testOk = true;
        String[][] expectedResults = {
                {
                        "Tenait en son bec un fromage.",
                        "Maître Renard, par l'odeur alléché,",
                        "Lui tint à peu près ce langage :",
                        "Et bonjour, Monsieur du Corbeau.",
                        "Que vous êtes joli ! que vous me semblez beau !",
                        "Sans mentir, si votre ramage",
                        "Se rapporte à votre plumage,",
                        "Vous êtes le Phénix des hôtes de ces bois.",
                        "À ces mots, le Corbeau ne se sent pas de joie ;",
                        "Et pour montrer sa belle voix,",
                        "Il ouvre un large bec, laisse tomber sa proie.",
                        "Le Renard s'en saisit, et dit : Mon bon Monsieur,",
                        "Apprenez que tout flatteur",
                        "Vit aux dépens de celui qui l'écoute.",
                        "Cette leçon vaut bien un fromage, sans doute.",
                        "Le Corbeau honteux et confus",
                        "Jura, mais un peu tard, qu'on ne l'y prendrait plus.",
                        null
                },
                {
                        "Maître Corbeau, sur un arbre perché,",
                        "Tenait en son bec un fromage.",
                        "Maître Renard, par l'odeur alléché,",
                        "Lui tint à peu près ce langage :",
                        "Et bonjour, Monsieur du Corbeau.",
                        "Que vous êtes joli ! que vous me semblez beau !",
                        "Sans mentir, si votre ramage",
                        "Se rapporte à votre plumage,",
                        "Vous êtes le Phénix des hôtes de ces bois.",
                        "À ces mots, le Corbeau ne se sent pas de joie ;",
                        "Et pour montrer sa belle voix,",
                        "Il ouvre un large bec, laisse tomber sa proie.",
                        "Le Renard s'en saisit, et dit : Mon bon Monsieur,",
                        "Apprenez que tout flatteur",
                        "Vit aux dépens de celui qui l'écoute.",
                        "Cette leçon vaut bien un fromage, sans doute.",
                        "Le Corbeau honteux et confus",
                        "Jura, mais un peu tard, qu'on ne l'y prendrait plus.",
                        null
                }
        };


        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.delete(0);
            String[] result = f.getContent();
            for (int i = 0 ; i < expectedResults[0].length - 1 ; i++) {
                testOk &= result[i].equals(expectedResults[0][i]);
            }
            //test null lines not written in tests array
            for (int i = expectedResults[0].length - 1; i < result.length ; i++) {
                testOk &= result[i] == null;
            }
        } catch (IOException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        }

        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierDernieresLignes.txt");
            f.delete(99);
            for (int i = 0 ; i < 82 ; i++) {
                testOk &= f.getContent()[i].equals("");
            }
            for (int i = 82 ; i < 99 ; i++) {
                testOk &= f.getContent()[i].equals(expectedResults[1][i-82]);
            }
            testOk &= f.getContent()[99] == null;

        } catch (IOException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        }

        return testOk;
    }
}
