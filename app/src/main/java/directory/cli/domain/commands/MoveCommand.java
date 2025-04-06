package directory.cli.domain.commands;

import directory.cli.domain.Directory;

import java.util.Optional;

/**
 * The MoveCommand class represents a command to move a subdirectory from one location to another within a Directory.
 */
public class MoveCommand extends BaseCommand {

    public static final String COMMAND_NAME = "MOVE";

    /**
     * Constructs a MoveCommand with the specified arguments.
     *
     * @param args the arguments for the command
     * @throws IllegalArgumentException if the arguments are invalid
     */
    public MoveCommand(String[] args) {
        super(args, 2);
    }

    /**
     * Executes the move command on the specified Directory.
     * Moves the subdirectory from the source location to the destination location.
     *
     * @param directory the Directory on which the command is executed
     * @return an empty Optional as the result of the command execution
     */
    @Override
    public Optional<String> execute(Directory directory) {
        directory.moveSubDirectory(getArgs()[0], getArgs()[1]);

        return Optional.empty();
    }
}