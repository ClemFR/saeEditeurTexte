package info1.editor.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextFile {

    static String[] editionEnCours;

    public static void main(String[] args) {

        try {
            editionEnCours = TextFile.readFile("C:\\Users\\Clement_L\\Downloads\\QCM.txt");
            System.out.println(editionEnCours.length);
            for (String line : editionEnCours) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * permet de lire un fichier texte et de le retourner sous forme de tableau de String
     * qui contient chaque ligne du fichier
     * @param filePath Le nom du fichier à lire
     * @throws IOException Si le fichier est introuvable ou si une erreur de lecture
     *                     survient
     * @throws IndexOutOfBoundsException si le fichier excède 100 lignes ou si une ligne
     *                                   excède 75 caractères.
     */
    public static String[] readFile(String filePath) throws IOException {

        Path path = Paths.get(filePath);
        String[] lines = new String[(int) Files.lines(path).count()];
        if (lines.length > 100) {
            throw new IndexOutOfBoundsException("Le fichier excède 100 lignes");
        }
        int lineIndex = 0;

    	//On ouvre le fichier en lecture
    	FileReader fr = new FileReader(path.toFile());

    	//On crée un buffer de lecture
    	BufferedReader br = new BufferedReader(fr);

    	//On parcours le fichier ligne par ligne
        for (String line = br.readLine(); line != null; line = br.readLine()) {
            if (line.length() > 75) {
                throw new IndexOutOfBoundsException("Une ligne excède 75 caractères");
            }
            lines[lineIndex] = line;
            lineIndex++;
        }
    	//On ferme le fichier
    	br.close();

        return lines;
    }

}
