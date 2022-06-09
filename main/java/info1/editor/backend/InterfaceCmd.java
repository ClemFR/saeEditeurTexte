package info1.editor.backend;

import java.io.IOException;
import java.util.Scanner;

public class InterfaceCmd {

    static String[] fichier;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String commande;

        System.out.print("Entrez une commande : ");
        commande = scanner.nextLine();

        analyserCommande(commande);



    }

    private static int analyserCommande(String commande) {

        int valeur = -1;
        boolean commandeOk;
        String[] commandesEnregistres = {"t", "f", "q", "?", "e", "a", "i", "m", "c", "d"};
        String[] splitCommande = {commande.substring(0, commande.indexOf(" ")), commande.substring(commande.indexOf(" ") + 1)};

        commandeOk = false;
        for (int i = 0; i < commandesEnregistres.length || commandeOk; i++) {
            commandeOk = splitCommande[0].equals(commandesEnregistres[i]);
            if (commandeOk) { valeur = i; }
        }

        switch(valeur) {
            case -1:
                System.out.println("Commande inconnue");
                break;

            case 0: //commande t -> ouvrir un fichier & lire contenu
                System.out.println("Ouverture du fichier : " + splitCommande[1]);
                // try {
                //     fichier = File.charger(splitCommande[1]);
                // } catch (IOException e) {
                //     System.out.println("Erreur lors de l'ouverture du fichier.");
                // }
                break;

            case 1: //commande f -> sauver le fichier
                System.out.println("WIP !!! Sauvegarde du fichier : " + splitCommande[1]);
                //TODO : sauvegarder le fichier
                break;

            case 2: //commande q -> quitter
                System.out.println("Au revoir !");
                //TODO : Quitter le programme
                break;

            case 3: //commande ? -> afficher les commandes
                System.out.println("Commandes disponibles :");
                //TODO : afficher les commandes avec explications
                break;

            case 4: //commande e -> détruire lignes (e -> tout effacer
                    //                               e 1 -> effacer ligne 1
                    //                               e 1, 7 -> effacer ligne 1 à 7)
                System.out.println("WIP !!! Destruction des lignes : " + splitCommande[1]);
                //TODO : détruire les lignes
                //TODO : mechanisme de détection du type de commande
                break;

            case 5: // commande a -> ajouter texte a ligne (a 1 bla bla bla ->
                    //                                  ajouter à ligne 1 bla bla bla)
                System.out.println("WIP !!! Ajout de ligne : " + splitCommande[1]);
                //TODO : ajouter texte a ligne
                break;

            case 6: // commande i -> inserer texte debut ligne (i 2 bla bla bla ->
                        //                                  insérer à ligne 2 bla bla bla)
                System.out.println("WIP !!! Insertion de ligne : " + splitCommande[1]);
                //TODO : insérer texte à ligne
                break;

            case 7: // commande m -> modifier texte ligne (m 2 -> voir pdf)
                System.out.println("WIP !!! Modification de ligne : " + splitCommande[1]);
                //TODO : modifier texte de ligne
                break;

            case 8: // commande c -> copier ligne (c 1, 3, 4 | c 1, 6)
                System.out.println("WIP !!! Copie de ligne : " + splitCommande[1]);
                //TODO : copier ligne + détection du type de commande
                break;
        }





        return valeur;
    }
}
