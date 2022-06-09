package info1.editor.tests.file;

import info1.editor.backend.File;

import java.util.Objects;

public class TestCopyInt {

    static final String[] RESULTAT_ATTENDU = {
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
            "Maître Corbeau, sur un arbre perché,"
    };

    public static boolean launch() {

        boolean testOk;
        testOk = true;

        /* Duplication de la ligne de départ */
        File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        f.copy(0, 0);

        String[] contenu = f.getContent();
        testOk &= (Objects.equals(contenu[0], contenu[1]) && Objects.equals(contenu[1], "Maître Corbeau, sur un arbre perché,"));


        /* Tentative de copie d'une ligne dans un fichier deja plein */
        try {
            f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierDernieresLignes.txt");
            f.copy(0, 0);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Tentative de copie d'une ligne dans un indice qui n'existe pas (negatif) */
        try {
            f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.copy(-1, 0);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Tentative de copie d'une ligne dans un indice qui n'existe pas (positif) */
        try {
            f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.copy(100, 0);
            testOk &= false;
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        /* Copie d'une ligne au départ (à la pos 0) */
        f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        f.copy(1, -1);
        contenu = f.getContent();
        testOk &= Objects.equals(contenu[0], "Tenait en son bec un fromage.");



        /* Copie de la ligne de début à la fin */
        f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
        f.copy(0,  17);

        contenu = f.getContent();
        int index;
        for (index = 0 ; index < RESULTAT_ATTENDU.length ; index++) {
            testOk &= Objects.equals(contenu[index], RESULTAT_ATTENDU[index]);
        }
        for (; index < contenu.length ; index++) {
            testOk &= contenu[index] == null;
        }

        return testOk;
    }


}
