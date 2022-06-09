package info1.editor.tests.file;

import info1.editor.backend.File;
import info1.editor.exception.FileLoadingException;
import info1.editor.exception.LineToLongException;

public class TestAppend {

    public static boolean launch() {

        boolean testOk;
        testOk = true;

        String[][] expectedResults = {
                {
                        "Maître Corbeau, sur un arbre perché,",
                        "uaseco",
                        "123456",
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
                        "azerty"
                        //Everything after is null
                }
        };

        /* Ajout classique de lignes */
        File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        f.append(0, "uaseco");
        f.append(1, "123456");
        f.append(19, "azerty");
        String[] result = f.getContent();
        for (int i = 0 ; i < expectedResults[0].length ; i++) {
            testOk &= result[i].equals(expectedResults[0][i]);
        }
        for (int i = expectedResults[0].length ; i < result.length ; i++) {
            testOk &= result[i] == null;
        }


        /* Ajout d'une ligne alors que le fichier est plein  */
        try {
            f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierDernieresLignes.txt");
            f.append(0, "uaseco");
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Ajout d'une ligne trop longue  */
        try {
            f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.append(0, "text over 75 characters long aaaaaaaaaaaaaaaaaaaaaaaa" +
                                 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            testOk &= false;
        } catch (LineToLongException expectedError) {
            testOk &= true;
        }

        /* Ajout de plusieurs lignes jusqu'à remplir le fichier sans dépasser
         * la limite de lignes */
        try {
            f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            for (int i = 18 ; i < 99 ; i++) {
                f.append(i, "azerty");
            }
            testOk &= true;
        } catch (IndexOutOfBoundsException e) {
            testOk &= false;
        }

        /* Ajout de plusieurs lignes jusqu'à remplir le fichier en dépassant
         * la limite de lignes */
        try {
            f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            for (int i = 18 ; i < 100 ; i++) {
                f.append(i, "azerty");
            }
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }


        return testOk;
    }
}
