package info1.editor.tests.file;

import info1.editor.backend.File;
import info1.editor.exception.FileNotFoundException;

public class TestDeleteInt {

    /**
     * Test for the simple delete of a single line
     * @return true if test is ok, false otherwise
     */
    public static boolean launch() {

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
                        //Everything after is null
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


        /* Suppression d'une ligne en debut de fichier pour voir si toutes les lignes sont
         * bien décalés */
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
        } catch (FileNotFoundException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        }

        /* Suppression d'une ligne en fin de fichier pour voir si elle se transforme bien
         * en null */
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

        } catch (FileNotFoundException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        }

        /* Tentative de suppression d'une ligne à un indice qui n'existe pas (negatif) */
        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.delete(-1);
            testOk &= false;
        } catch (FileNotFoundException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Tentative de suppression d'une ligne à un indice qui n'existe pas (positif) */
        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.delete(100);
            testOk &= false;
        } catch (FileNotFoundException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        return testOk;
    }

}
