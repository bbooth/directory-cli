package directory.cli.domain.commands;

import directory.cli.domain.exceptions.InvalidCommandException;

import java.util.Arrays;

import static java.util.Objects.requireNonNull;

/**
 * The CommandFactory class is responsible for creating instances of Command based on the provided command string.
 */
public class CommandFactory {

    /**
     * Returns a Command instance based on the provided command string.
     *
     * @param command the command string to parse and create the corresponding Command instance
     * @return the Command instance corresponding to the provided command string
     * @throws NullPointerException     if the command is null
     * @throws InvalidCommandException if the command is empty or invalid
     */
    public Command getCommand(String command) {
        requireNonNull(command, "Command cannot be null");

        String[] inputs = command.split(" ");

        if (inputs.length < 1) {
            throw new IllegalArgumentException("Command cannot be empty");
        }

        String commandName = inputs[0];
        String[] commandArgs = inputs.length > 1 ? Arrays.copyOfRange(inputs, 1, inputs.length) : new String[0];

        return switch (commandName) {
            case CreateCommand.COMMAND_NAME -> new CreateCommand(commandArgs);
            case ListCommand.COMMAND_NAME -> new ListCommand(commandArgs);
            case MoveCommand.COMMAND_NAME -> new MoveCommand(commandArgs);
            case DeleteCommand.COMMAND_NAME -> new DeleteCommand(commandArgs);
            default -> throw new InvalidCommandException(commandName);
        };
    }
}