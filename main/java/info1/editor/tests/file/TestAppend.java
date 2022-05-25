package info1.editor.tests.file;

import info1.editor.backend.File;

import java.io.IOException;

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

        try {
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
        } catch (IOException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        }

        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierDernieresLignes.txt");
            f.append(0, "uaseco");
            testOk &= false;
        } catch (IOException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            f.append(0, "text over 75 characters long aaaaaaaaaaaaaaaaaaaaaaaa" +
                                 "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            testOk &= false;
        } catch (IOException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }

        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            for (int i = 18 ; i < 99 ; i++) {
                f.append(i, "azerty");
            }
            testOk &= true;
        } catch (IOException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            testOk &= false;
        }

        try {
            File f = new File("src/main/java/info1/editor/tests/fichierexemple/testFichierOk.txt");
            for (int i = 18 ; i < 100 ; i++) {
                f.append(i, "azerty");
            }
            testOk &= false;
        } catch (IOException e) {
            System.err.println("Echec du test suite à une erreur dans le chargement du fichier");
            e.printStackTrace();
        } catch (IndexOutOfBoundsException expectedError) {
            testOk &= true;
        }


        return testOk;
    }
}
