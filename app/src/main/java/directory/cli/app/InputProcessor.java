package directory.cli.app;

import directory.cli.domain.Directory;
import directory.cli.domain.commands.Command;
import directory.cli.domain.commands.CommandFactory;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

import static java.util.Objects.requireNonNull;

/**
 * The InputProcessor class is responsible for processing input commands from an InputStream
 * and executing them on a Directory structure.
 */
public class InputProcessor {

    private final CommandFactory commandFactory;
    private final Scanner scanner;

    /**
     * Constructs an InputProcessor with the specified CommandFactory and InputStream.
     *
     * @param commandFactory the CommandFactory to create commands
     * @param inputStream    the InputStream to read input commands from
     * @throws NullPointerException if the commandFactory or inputStream is null
     */
    public InputProcessor(CommandFactory commandFactory, InputStream inputStream) {
        requireNonNull(inputStream, "InputStream cannot be null");

        this.commandFactory = requireNonNull(commandFactory, "CommandFactory cannot be null");
        scanner = new Scanner(inputStream);
    }

    /**
     * Starts processing input commands from the InputStream and executes them on a Directory.
     * The results are printed to the specified PrintStream.
     *
     * @param printStream the PrintStream to print the results of the executed commands
     * @throws NullPointerException if the printStream is null
     */
    public void startProcessingInput(PrintStream printStream) {
        requireNonNull(printStream, "PrintStream cannot be null");

        Directory directory = new Directory();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();

            Command command;

            try {
                printStream.println(input);
                command = commandFactory.getCommand(input);
                Optional<String> result = command.execute(directory);
                result.ifPresent(printStream::println);
            } catch (Exception e) {
                printStream.println(e.getMessage());
            }
        }

        scanner.close();
    }
}