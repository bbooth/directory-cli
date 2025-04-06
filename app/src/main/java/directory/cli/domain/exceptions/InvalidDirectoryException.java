package directory.cli.domain.exceptions;

public class InvalidDirectoryException extends IllegalArgumentException {
    /**
     * Constructs an InvalidCommandException with the specified detail message.
     */
    public InvalidDirectoryException() {
        super("root is reserved and can't be used as a subdirectory");
    }
}
