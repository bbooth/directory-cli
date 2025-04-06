package directory.cli.domain.exceptions;

public class InvalidCommandException extends IllegalArgumentException {
    /**
     * Constructs an InvalidCommandException with the specified detail message.
     *
     * @param message the name of the invalid command
     */
    public InvalidCommandException(String message) {
        super("Invalid command type: " + message);
    }
}
