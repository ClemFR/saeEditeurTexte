package info1.editor.tests.file;

import info1.editor.backend.File;

public class TestMoveIntInt {

    static final private String[][] EXPECTED_RESULT = {
            {
                    "Tenait en son bec un fromage.",
                    "Maître Renard, par l'odeur alléché,",
                    "Lui tint à peu près ce langage :",
                    "Maître Corbeau, sur un arbre perché,",
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
                    "Et bonjour, Monsieur du Corbeau.",
                    "Tenait en son bec un fromage.",
                    "Maître Renard, par l'odeur alléché,",
                    "Lui tint à peu près ce langage :",
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

        /* Tentative de déplacement classique */
        File file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        file.move(1, 3,-1);
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

        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        file.move(1, 3,4);
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

        /* Tentative de déplacement de lignes pas encore écrites (positif) */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(18, 20,-1);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(20, 30,-1);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Tentative de déplacement d'une ligne illégale (négatif) */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(-15, -2,-1);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(-20, -5,-1);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Tentative de déplacement d'une ligne illégale vers un emplacement impossible (positif) */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(0, 5,18);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(0, 5,25);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Tentative de déplacement d'une ligne illégale vers un emplacement impossible (négatif) */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(0, 5,-2);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        try {
            file.move(0, 5,-5);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Tentative de déplacement d'une ligne sur elle-même */
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        file.move(0, 0,0);
        result = file.getContent();
        for (int i = 0; i < EXPECTED_RESULT[2].length; i++) {
            if (!result[i].equals(EXPECTED_RESULT[2][i])) {
                testOk &= false;
            }
        }
        for (int i = EXPECTED_RESULT[2].length + 1; i < result.length; i++) {
            if (result[i] != null) {
                testOk &= false;
            }
        }

        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        file.move(0, 5,0);
        result = file.getContent();
        for (int i = 0; i < EXPECTED_RESULT[2].length; i++) {
            if (!result[i].equals(EXPECTED_RESULT[2][i])) {
                testOk &= false;
            }
        }
        for (int i = EXPECTED_RESULT[2].length + 1; i < result.length; i++) {
            if (result[i] != null) {
                testOk &= false;
            }
        }

        // Déplacement dans un fichier plein
        file = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierDernieresLignes.txt");
        try {
            file.move(96, 99,-1);
            testOk &= true;
        } catch (ArrayIndexOutOfBoundsException e) {
            testOk &= false;
            System.out.println(e.getMessage());
        }

        return testOk;
    }
}