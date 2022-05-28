package info1.editor.exception;

/**
 * Exception thrown when a file is not found
 * @author Clément L. & Gabriel M. & Tony L.
 */
public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(String message) {
        super(message);
    }

}