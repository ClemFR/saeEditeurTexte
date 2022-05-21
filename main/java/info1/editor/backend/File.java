package info1.editor.backend;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class File {

    public final int MAX_LINES = 100;
    public final int MAX_CHAR = 75;

    private Path path;
    private String[] content;

    /**
     * Creates a new file with the given path.
     * @param path the path of the file to create
     * @throws IOException if the file cannot be loaded
     */
    public File(String path) throws IOException {

        java.io.File file = new java.io.File(path);

        this.path = Path.of(path);

        file = file.getCanonicalFile();

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

        String[] lines;
        lines = new String[(int) Files.lines(filePath).count()];

        if (lines.length > MAX_LINES) {
            throw new IndexOutOfBoundsException("Le fichier excède 100 lignes");
        }
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

    public void save() throws IOException {
        PrintWriter newFile = new PrintWriter(path.toString());

        // Print first line outside so the last line is not just a new blank line
        newFile.print(content[0]);
        for (int i = 1 ; i < content.length ; i++) {
            newFile.print("\n" + content[i]);
        }

        newFile.close();
    }
}
