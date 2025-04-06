package directory.cli.domain.commands;

import directory.cli.domain.Directory;

import java.util.Optional;

/**
 * The Command interface represents a command that can be executed on a Directory.
 * Implementations of this interface should provide the logic for executing the command
 * and building the command output.
 */
public interface Command {

    /**
     * Executes the command on the specified Directory.
     *
     * @param directory the Directory on which the command is executed
     * @return the output of the command execution if any, wrapped in an Optional
     */
    Optional<String> execute(Directory directory);

    /**
     * Returns the arguments of the command.
     *
     * @return the command arguments
     */
    String[] getArgs();
}