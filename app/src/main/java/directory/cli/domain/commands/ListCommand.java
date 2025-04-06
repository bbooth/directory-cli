package directory.cli.domain.commands;

import directory.cli.domain.Directory;

import java.util.Optional;

/**
 * The ListCommand class represents a command to list the structure of a Directory.
 */
public class ListCommand extends BaseCommand {

    public static final String COMMAND_NAME = "LIST";

    /**
     * Constructs a ListCommand with the specified arguments.
     *
     * @param args the arguments for the command
     * @throws IllegalArgumentException if the arguments are invalid
     */
    public ListCommand(String[] args) {
        super(args, 0);
    }

    /**
     * Executes the list command on the specified Directory.
     * Returns the structure of the Directory as a string.
     *
     * @param directory the Directory on which the command is executed
     * @return the result of the command execution wrapped in an Optional
     */
    @Override
    public Optional<String> execute(Directory directory) {
        return Optional.of(directory.printStructure());
    }
}