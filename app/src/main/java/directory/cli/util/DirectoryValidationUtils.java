package directory.cli.util;

public class DirectoryValidationUtils {
    /**
     * Validates the specified directory name.
     * - Does not start with a slash
     * - Does not contain consecutive slashes
     * - Does not end with a slash
     * - Contains only lowercase letters and slashes as separators
     *
     * @param directoryName the name of the directory to validate (only lowercase with / as a separator).
     * @throws IllegalArgumentException if the directory name is null, empty, or contains invalid characters
     */
    public static void validateDirectoryName(String directoryName) throws IllegalArgumentException {
        if (directoryName == null || directoryName.trim().isEmpty()) {
            throw new IllegalArgumentException("Directory name cannot be null or empty.");
        }

        if (!directoryName.matches("^(?!/)(?!.*//)[a-z]+(/[a-z]+)*$")) {
            throw new IllegalArgumentException("The name of the directory can only contain lowercase letters and /, cannot have consecutive slashes, and cannot end with a slash.");
        }
    }
}
