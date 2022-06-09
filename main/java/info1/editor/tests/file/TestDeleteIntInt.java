package info1.editor.tests.file;

import info1.editor.backend.File;

import info1.editor.exception.FileLoadingException;


public class TestDeleteIntInt {

    public static boolean launch() {
        boolean testOk;
        testOk = true;

        String[][] expectedResults = {
                {
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
                        "Jura, mais un peu tard, qu'on ne l'y prendrait plus."
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
                        //Everything after is null
                }

        };

        /* Suppression de lignes en debut de fichier pour voir si le décalage s'effectue
         * bien */

        File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        f.delete(0, 4);
        String[] result = f.getContent();
        int lineIndex;
        for (lineIndex = 0 ; lineIndex < expectedResults[0].length - 1 ; lineIndex++) {
            if (result[lineIndex] != null) {
                testOk &= result[lineIndex].equals(expectedResults[0][lineIndex]);
            } else {
                testOk = false;
            }
        }
        //test null lines not written in tests array
        for (; lineIndex < result.length ; lineIndex++) {
            testOk &= result[lineIndex] == null;

        }

        /* Suppression de lignes en fin de fichier pour voir si le décalage d'une ligne et
         * la transformation en null s'effectue bien */

        f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierDernieresLignes.txt");
        f.delete(94, 98);
        result = f.getContent();
        for (lineIndex = 0 ; lineIndex < 82 ; lineIndex++) {
            testOk &= result[lineIndex].equals("");
        }
        for (;lineIndex - 82 < expectedResults[1].length ; lineIndex++) {
            testOk &= result[lineIndex].equals(expectedResults[1][lineIndex - 82]);
        }
        //test null lines not written in tests array
        for (; lineIndex < result.length ; lineIndex++) {
            testOk &= result[lineIndex] == null;

        }

        /* Suppression de lignes en fin de fichier pour voir si la transformation en null
         * s'effectue bien */

        f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierDernieresLignes.txt");
        f.delete(94, 99);
        result = f.getContent();
        for (lineIndex = 0 ; lineIndex < 82 ; lineIndex++) {
            testOk &= result[lineIndex].equals("");
        }
        for (;lineIndex - 82 < expectedResults[2].length ; lineIndex++) {
            testOk &= result[lineIndex].equals(expectedResults[1][lineIndex - 82]);
        }
        //test null lines not written in tests array
        for (; lineIndex < result.length ; lineIndex++) {
            testOk &= result[lineIndex] == null;

        }

        /* Suppression à un index illégal (interdit - valide) */
        try {

            f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.delete(-1, 4);
            testOk &= false;

        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Suppression à un index illégal (valide - interdit) */
        try {

            f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.delete(0, 100);
            testOk &= false;

        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Suppression à un index illégal (interdit - interdit) */
        try {

            f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.delete(-1, 100);
            testOk &= false;

        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        return testOk;
    }


}
