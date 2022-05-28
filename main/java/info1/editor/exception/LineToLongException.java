package info1.editor.exception;

/**
 * Exception thrown when a String has too many characters
 * @author Clément L. & Gabriel M. & Tony L.
 */
public class LineToLongException extends RuntimeException {

    public LineToLongException(String message) {
        super(message);
    }
}