package info1.editor.exception;

/**
 * Exception thrown when a file is not found
 * @author Clément L. & Gabriel M. & Tony L.
 */
public class FileLoadingException extends RuntimeException {

    public FileLoadingException(String message) {
        super(message);
    }

}