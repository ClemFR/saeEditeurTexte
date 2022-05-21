package info1.editor.backend;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class File {

    public final int MAX_LINES = 100;
    public final int MAX_CHAR = 75;

    private Path path;
    private String[] content;
    private int currentLine;

    /**
     * Creates a new file with the given path.
     * @param path the path of the file to create
     * @throws IOException if the file cannot be loaded
     */
    public File(String path) throws IOException {

        java.io.File file = new java.io.File(path);
        this.path = Path.of(path);

        if (!file.exists()) {
            throw new IOException("File not found");
        }
        this.content = loadFile(this.path);
    }

    /**
     * Loads the file into an array of strings. If the file is not found, an empty
     * file is created.
     * @param filePath the path of the file to load
     * @return an array of strings representing the file
     * @throws IndexOutOfBoundsException if the number of line is greater than the maximum
     *                                   or if the number of characters per line is
     *                                   greater than the maximum
     */
    private String[] loadFile(Path filePath) throws IOException {

        this.currentLine = (int) Files.lines(filePath).count();
        if (this.currentLine > MAX_LINES + 1) {
            throw new IndexOutOfBoundsException("Le fichier excède 100 lignes");
        }

        String[] lines = new String[MAX_LINES];
        Arrays.fill(lines, null);

        int lineIndex = 0;

        FileReader fr = new FileReader(filePath.toFile());

        BufferedReader br = new BufferedReader(fr);

        for (String line = br.readLine(); line != null; line = br.readLine()) {
            if (line.length() > MAX_CHAR) {
                throw new IndexOutOfBoundsException("Une ligne excède 75 caractères");
            }
            lines[lineIndex] = line;
            lineIndex++;
        }
        br.close();

        return lines;
    }

    //TODO : ajouter les tests
    public void save() throws IOException {
        PrintWriter newFile = new PrintWriter(path.toString());

        // Print first line outside so the last line is not just a new blank line
        newFile.print(content[0]);
        for (int i = 1 ; i < content.length ; i++) {
            newFile.print("\n" + content[i]);
        }
        newFile.close();
    }

    /**
     * Delete the line at specified index and shift the other lines.
     * @param idLine the index of the line to delete
     * @throws NullPointerException If the specified index is out of bounds
     */
    public void delete(int idLine) throws NullPointerException {
        if (idLine < 0 || idLine > this.currentLine) {
            throw new NullPointerException("Index out of bounds");
        }

        int lineIndex;

        if (idLine == MAX_LINES - 1) {
            this.content[idLine] = null;
        } else {
            for (lineIndex = idLine ; lineIndex < this.content.length && this.content[lineIndex] != null; lineIndex++) {
                this.content[lineIndex] = this.content[lineIndex + 1];
            }

            this.content[lineIndex] = null;
            this.currentLine--;
        }

    }

    public String[] getContent() {
        return Arrays.copyOf(this.content, MAX_LINES);
    }
}
