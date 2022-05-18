package info1.editor.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class File {

    public final int MAX_LINES = 100;
    public final int MAX_CHAR = 75;

    private Path path;
    private String[] content;

    public File(String path) throws IOException {
        this.path = Paths.get(path);
        this.content = loadFile(this.path);

    }

    private String[] loadFile(Path filePath) throws IOException {
        String[] lines = new String[(int) Files.lines(filePath).count()];
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
}
