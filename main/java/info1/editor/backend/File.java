package info1.editor.backend;

import info1.editor.exception.FileNotFoundException;
import info1.editor.exception.LineToLongException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
     * @throws FileNotFoundException if the file cannot be loaded
     */
    public File(String path) {

        java.io.File file = new java.io.File(path);
        this.path = Path.of(path);

        if (!file.exists()) {
            throw new FileNotFoundException("File not found");
        }
        try {
            this.content = loadFile(this.path);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileNotFoundException("File not found");
        }
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
    public String[] loadFile(Path filePath) throws IOException {

        this.currentLine = (int) Files.lines(filePath).count();
        if (this.currentLine > MAX_LINES + 1) {
            throw new IndexOutOfBoundsException("Le fichier excède 100 lignes");
        }

        String[] lines = new String[MAX_LINES];
        Arrays.fill(lines, "");

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
     * @throws IndexOutOfBoundsException If the specified index is out of bounds
     */
    public void delete(int idLine) throws IndexOutOfBoundsException {
        if (idLine < 0 || idLine > this.currentLine) {
            throw new IndexOutOfBoundsException("Index out of bounds");
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

    /**
     * Delete all lines included in the specified range.
     * @param start start of the range
     * @param end end of the range
     * @throws IndexOutOfBoundsException If the specified range is out of bounds
     */
    public void delete(int start, int end) {
        if (start < 0 || start > this.currentLine || end < 0 || end > this.currentLine || start > end) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        int linesDeleted = end - start + 1;
        int lineIndex;
        for (int i = start ; i <= end ; i++) {
            this.content[i] = null;
        }

        for (lineIndex = end + 1 ; lineIndex < this.content.length && this.content[lineIndex] != null; lineIndex++) {
            this.content[lineIndex - linesDeleted] = this.content[lineIndex];
            this.content[lineIndex] = null;
        }
        this.currentLine = this.currentLine - linesDeleted;
    }

    /**
     * Insert the specified text after the specified line.
     * @param line the line to insert the text after it.
     * @param text the text to insert
     * @throws IndexOutOfBoundsException If the specified line is out of bounds or if
     *      *                            the file is at max line size
     * @throws LineToLongException If the specified text is too long
     */
    public void append(int line, String text) {
        if (line < 0 || line > this.currentLine) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (this.currentLine == MAX_LINES - 1) {
            throw new IndexOutOfBoundsException("File at max line size");
        }
        if (text.length() > MAX_CHAR) {
            throw new LineToLongException("75 char max");
        }

        for (int i = this.currentLine ; i > line + 1 ; i--) {
            this.content[i] = this.content[i - 1];
        }
        this.content[line + 1] = text;
        this.currentLine++;
    }

    /**
     * Insert the specified text before the specified line.
     * @param line the line to insert the text before it.
     * @param text the text to insert
     * @throws IndexOutOfBoundsException If the specified line is out of bounds or if the
     *                                   file is at max line size
     * @throws LineToLongException If the specified text exceeds the maximum line size
     */
    public void insert(int line, String text) {
        if (!(line == 1 && this.currentLine == 0)) { // Create the first line if the file is empty

            if (line < 1 || line > this.currentLine) {
                throw new IndexOutOfBoundsException("Index out of bounds");
            }
            if (this.currentLine == MAX_LINES - 1) {
                throw new IndexOutOfBoundsException("File at max line size");
            }
            if (text.length() > MAX_CHAR) {
                throw new LineToLongException("75 char max");
            }

            for (int i = this.currentLine; i > line - 2; i--) {
                this.content[i + 1] = this.content[i];
            }
        }


        this.content[line - 1] = text;
        this.currentLine++;
    }

    /**
     * Replace the specified line with the specified text.
     * @param line the line to replace
     * @param text the text to replace with
     * @throws IndexOutOfBoundsException If the specified line is out of bounds
     * @throws LineToLongException If the specified text exceeds the maximum line size
     */
    public void edit(int line, String text) {
        if (line < 0 || line > this.currentLine) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (text.length() > MAX_CHAR) {
            throw new LineToLongException("75 char max");
        }

        this.content[line] = text;
    }

    /**
     * @param lineToCopy Index of the line to copy
     * @param locationToPaste Index of the location to paste the line just after it.
     *                        Use '-1' to paste at the start of the file.
     * @throws IndexOutOfBoundsException If the specified line is out of bounds
     */
    public void copy(int lineToCopy, int locationToPaste) {
        if (lineToCopy < 0 || lineToCopy > this.currentLine) {
            throw new IndexOutOfBoundsException("Index of 'lineToCopy' out of bounds");
        }
        if (locationToPaste < -1 || locationToPaste > this.currentLine) {
            throw new IndexOutOfBoundsException("Index of 'locationToPaste' out of bounds");
        }

        if (locationToPaste == -1) {
            this.insert(0, this.content[lineToCopy]);
        } else {
            this.append(locationToPaste, this.content[lineToCopy]);
        }
    }

    /**
     * @param start Starting index of the range to copy
     * @param end Last index of the range to copy
     * @param locationToPaste Index of the location to paste selected lines just after it.
     *                        Use '-1' to paste at the start of the file.
     * @throws IndexOutOfBoundsException If the specified line is out of bounds
     */
    public void copy(int start, int end, int locationToPaste) {
        if (start < 0 || start > this.currentLine) {
            throw new IndexOutOfBoundsException("Index of 'start' out of bounds");
        }
        if (end < 0 || end > this.currentLine || start >= end) {
            throw new IndexOutOfBoundsException("Index of 'end' out of bounds");
        }
        if (locationToPaste < -1 || locationToPaste > this.currentLine) {
            throw new IndexOutOfBoundsException("Index of 'locationToPaste' out of bounds");
        }

        String[] linesToCopy = new String[end - start + 1];
        for (int i = start ; i <= end ; i++) {
            linesToCopy[i - start] = this.content[i];
        }

        if (locationToPaste == -1) {
            // Revert paste to rectify the order of the lines
            for (int i = linesToCopy.length-1 ; i >= 0 ; i--) {
                this.insert(1, linesToCopy[i]);
            }
        } else {
            // Revert paste to rectify the order of the lines
            for (int i = linesToCopy.length-1 ; i >= 0 ; i--) {
                this.append(locationToPaste, linesToCopy[i]);
            }
        }
    }

    /**
     * Get the file in an array list
     * @return the file in an array list
     */
    public String[] getContent() {
        return Arrays.copyOf(this.content, MAX_LINES);
    }
}
