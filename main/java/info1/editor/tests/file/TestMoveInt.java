package info1.editor.tests.file;

import info1.editor.backend.File;

public class TestMoveInt {

    static final private String[][] EXPECTED_RESULT = {
        {
            "Tenait en son bec un fromage.",
            "Maître Corbeau, sur un arbre perché,",
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
            "Jura, mais un peu tard, qu'on ne l'y prendrait plus."
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
            "Jura, mais un peu tard, qu'on ne l'y prendrait plus."
        }

    };

    public static boolean launch() {
        boolean testOk = true;
        String[] result;

        /* Tentative d'un déplacement classique */

        File file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        file.move(1, -1);
        result = file.getContent();

        for (int i = 0; i < EXPECTED_RESULT[0].length; i++) {
            if (!result[i].equals(EXPECTED_RESULT[0][i])) {
                testOk &= false;
            }
        }
        for (int i = EXPECTED_RESULT[0].length + 1; i < result.length; i++) {
            if (result[i] != null) {
                testOk &= false;
            }
        }

        /* Tentative de déplacement d'une ligne pas encore écrite (positif) */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(50, -1);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(18, -1); // Fichier contant 18 lignes, mais depart à 0
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Tentative de déplacement d'une ligne illégale (négatif) */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(-1, -1);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(-10, -1);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Tentative de déplacement d'une ligne illégale vers un emplacement impossible (positif) */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(0, 18);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(0, 20);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Tentative de déplacement d'une ligne illégale vers un emplacement impossible (négatif) */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(0, -2);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(0, -10);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Tentative de déplacement d'une ligne sur elle-même */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        file.move(0, 0);
        result = file.getContent();
        for (int i = 0; i < EXPECTED_RESULT[1].length; i++) {
            if (!result[i].equals(EXPECTED_RESULT[1][i])) {
                testOk &= false;
            }
        }
        for (int i = EXPECTED_RESULT[1].length + 1; i < result.length; i++) {
            if (result[i] != null) {
                testOk &= false;
            }
        }

        /* Tentative d'un déplacement dans un fichier plein */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierDernieresLignes.txt");
        try {
            file.move(99, -1);
            testOk &= true;
        } catch (ArrayIndexOutOfBoundsException e) {
            testOk &= false;
            System.out.println("Erreur : " + e.getMessage());
        }


        return testOk;
    }
}
