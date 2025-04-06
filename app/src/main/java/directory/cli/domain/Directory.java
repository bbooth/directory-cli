package directory.cli.domain;

import directory.cli.domain.exceptions.DirectoryNotFoundException;
import directory.cli.domain.exceptions.InvalidDirectoryException;
import directory.cli.util.DirectoryValidationUtils;

import java.util.Map;
import java.util.TreeMap;

import static java.util.Objects.requireNonNull;

/**
 * The Directory class represents a directory structure with subdirectories.
 * It provides methods to add, move, and delete subdirectories, as well as to print the directory structure.
 */
public class Directory {
    private static String ROOT_DIRECTORY_NAME = "root";
    private final TreeMap<String, Directory> subDirectories;
    private final String name;
    private final boolean isRoot;

    /**
     * Constructs a Directory with the specified name.
     *
     * @param name the name of the directory
     */
    public Directory(String name) {
        DirectoryValidationUtils.validateDirectoryName(name);

        this.name = name;
        this.isRoot = false;
        this.subDirectories = new TreeMap<>();
    }

    /**
     * Constructs a root Directory
     */
    public Directory() {
        this.name = ROOT_DIRECTORY_NAME;
        this.isRoot = true;
        this.subDirectories = new TreeMap<>();
    }

    /**
     * Adds a subdirectory to the current directory.
     *
     * @param path the path of the subdirectory to add
     */
    public void addSubDirectory(String path) {
        DirectoryValidationUtils.validateDirectoryName(path);

        if (ROOT_DIRECTORY_NAME.equals(path)) {
            throw new InvalidDirectoryException();
        }

        String[] parts = path.split("/");
        Directory current = this;
        for (String part : parts) {
            current.subDirectories.putIfAbsent(part, new Directory(part));
            current = current.subDirectories.get(part);
        }
    }

    /**
     * Moves a subdirectory from the source path to the destination path.
     *
     * @param sourcePath      the source path of the subdirectory
     * @param destinationPath the destination path of the subdirectory
     * @throws DirectoryNotFoundException if the source or destination path is invalid
     */
    public void moveSubDirectory(String sourcePath, String destinationPath) {
        DirectoryValidationUtils.validateDirectoryName(sourcePath);
        DirectoryValidationUtils.validateDirectoryName(destinationPath);

        if (ROOT_DIRECTORY_NAME.equals(sourcePath)) {
            throw new InvalidDirectoryException();
        }

        Directory sourceParent = getParentDirectory(sourcePath);
        Directory destination = getDirectory(destinationPath);

        String sourceName = getDirectoryName(sourcePath);
        Directory sourceDir = sourceParent.subDirectories.remove(sourceName);

        if (sourceDir == null) {
            throw new DirectoryNotFoundException(sourceName);
        }

        destination.subDirectories.put(sourceName, sourceDir);
    }

    /**
     * Deletes a subdirectory from the current directory.
     *
     * @param path the path of the subdirectory to delete
     * @throws IllegalArgumentException if the subdirectory does not exist
     */
    public void deleteSubDirectory(String path) {
        DirectoryValidationUtils.validateDirectoryName(path);

        if (ROOT_DIRECTORY_NAME.equals(path)) {
            throw new InvalidDirectoryException();
        }

        try {
            Directory parent = getParentDirectory(path);

            String dirName = getDirectoryName(path);
            if (parent.subDirectories.remove(dirName) == null) {
                throw new DirectoryNotFoundException(dirName);
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Cannot delete %s - %s", path, e.getMessage()));
        }
    }

    /**
     * Gets the parent directory of the specified path.
     *
     * @param path the path of the directory
     * @return the parent directory
     * @throws IllegalArgumentException if any part of the path does not exist
     */
    private Directory getParentDirectory(String path) {
        DirectoryValidationUtils.validateDirectoryName(path);

        String[] parts = path.split("/");
        Directory current = this;
        for (int i = 0; i < parts.length - 1; i++) {
            current = current.subDirectories.get(parts[i]);
            if (current == null) {
                throw new DirectoryNotFoundException(parts[i]);
            }
        }
        return current;
    }

    /**
     * Gets the directory at the specified path.
     *
     * @param path the path of the directory
     * @return the directory at the specified path
     * @throws DirectoryNotFoundException if any part of the path does not exist
     */
    private Directory getDirectory(String path) {
        DirectoryValidationUtils.validateDirectoryName(path);

        if (ROOT_DIRECTORY_NAME.equals(path) && this.isRoot) {
            // Return the root directory instance
            return this;
        }

        String[] parts = path.split("/");
        Directory current = this;
        for (String part : parts) {
            current = current.subDirectories.get(part);
            if (current == null) {
                throw new DirectoryNotFoundException(part);
            }
        }
        return current;
    }

    /**
     * Gets the name of the directory from the specified path.
     *
     * @param path the path of the directory
     * @return the name of the directory
     */
    private String getDirectoryName(String path) {
        DirectoryValidationUtils.validateDirectoryName(path);

        String[] parts = path.split("/");
        return parts[parts.length - 1];
    }

    /**
     * Prints the structure of the directory.
     *
     * @return the structure of the directory as a string
     */
    public String printStructure() {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, Directory> entry : subDirectories.entrySet()) {
            entry.getValue().printStructure(builder, "");
        }

        return builder.toString().trim();
    }

    /**
     * Prints the structure of the directory with the specified prefix.
     *
     * @param builder the StringBuilder to append the structure to
     * @param prefix  the prefix to use for each line
     * @throws NullPointerException if the builder or prefix is null
     */
    private void printStructure(StringBuilder builder, String prefix) {
        requireNonNull(builder, "Builder cannot be null");
        requireNonNull(prefix, "Prefix cannot be null");

        builder.append(prefix).append(name).append("\n");

        for (Map.Entry<String, Directory> entry : subDirectories.entrySet()) {
            entry.getValue().printStructure(builder, prefix + "  ");
        }
    }
}