package directory.cli.domain.commands;

import directory.cli.domain.Directory;

import java.util.Optional;

/**
 * The DeleteCommand class represents a command to delete a subdirectory in a Directory.
 */
public class DeleteCommand extends BaseCommand {

    public static final String COMMAND_NAME = "DELETE";

    /**
     * Constructs a DeleteCommand with the specified arguments.
     *
     * @param args the arguments for the command
     * @throws IllegalArgumentException if the arguments are invalid
     */
    public DeleteCommand(String[] args) {
        super(args, 1);
    }

    /**
     * Executes the delete command on the specified Directory.
     * Deletes the subdirectory with the specified name from the Directory.
     *
     * @param directory the Directory on which the command is executed
     * @return an empty Optional as the result of the command execution
     */
    @Override
    public Optional<String> execute(Directory directory) {
        directory.deleteSubDirectory(getArgs()[0]);
        return Optional.empty();
    }
}