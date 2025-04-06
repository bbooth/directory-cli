package directory.cli.domain.exceptions;

public class DirectoryNotFoundException extends IllegalArgumentException {
    /**
     * Constructs an InvalidCommandException with the specified detail message.
     */
    public DirectoryNotFoundException(String directoryName) {
        super(directoryName + " does not exist");
    }
}
