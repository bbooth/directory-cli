package directory.cli.domain.commands;

import directory.cli.domain.Directory;

import java.util.Optional;

/**
 * The CreateCommand class represents a command to create a new subdirectory in a Directory.
 */
public class CreateCommand extends BaseCommand {

    public static final String COMMAND_NAME = "CREATE";

    /**
     * Constructs a CreateCommand with the specified arguments.
     *
     * @param args the arguments for the command
     * @throws IllegalArgumentException if the arguments are invalid
     */
    public CreateCommand(String[] args) {
        super(args, 1);
    }

    /**
     * Executes the create command on the specified Directory.
     * Adds a new subdirectory with the specified name to the Directory.
     * IF the subdirectory with the same name already exists, it will be a no-op (i.e., it will not create a duplicate).
     *
     * @param directory the Directory on which the command is executed
     * @return an empty Optional as the result of the command execution
     */
    @Override
    public Optional<String> execute(Directory directory) {
        directory.addSubDirectory(getArgs()[0]);

        return Optional.empty();
    }
}