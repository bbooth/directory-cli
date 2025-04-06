package directory.cli.domain.commands;

import directory.cli.util.DirectoryValidationUtils;

import static java.util.Objects.requireNonNull;

/**
 * The BaseCommand class provides a base implementation for commands that can be executed on a Directory.
 * It includes common functionality for command arguments validation.
 */
abstract class BaseCommand implements Command {
    private final String[] args;

    /**
     * Constructs a BaseCommand with the specified arguments.
     *
     * @param args              the arguments for the command
     * @param expectedArgsCount the expected number of arguments for the command
     * @throws NullPointerException     if the args are null
     * @throws IllegalArgumentException if the number of arguments is not as expected
     */
    protected BaseCommand(String[] args, int expectedArgsCount) {
        requireNonNull(args, "Command arguments cannot be null.");

        this.args = args;

        validateArgs(args, expectedArgsCount);
    }

    /**
     * Validates the specified command arguments.
     * This method should be overridden by subclasses to provide specific argument validation logic.
     *
     * @param args              the command arguments to validate
     * @param expectedArgsCount the expected number of arguments for the command
     * @throws IllegalArgumentException if the number of arguments is not as expected
     */
    protected void validateArgs(String[] args, int expectedArgsCount) {
        if (args.length != expectedArgsCount) {
            throw new IllegalArgumentException(String.format("The command requires %s arguments", expectedArgsCount));
        }

        for (String arg : args) {
            DirectoryValidationUtils.validateDirectoryName(arg);
        }
    }

    /**
     * Returns the arguments of the command.
     *
     * @return the command arguments
     */
    @Override
    public String[] getArgs() {
        return this.args;
    }
}