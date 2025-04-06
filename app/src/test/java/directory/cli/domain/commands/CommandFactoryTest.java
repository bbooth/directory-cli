package directory.cli.domain.commands;

import directory.cli.domain.exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandFactoryTest {

    private final CommandFactory commandFactory = new CommandFactory();

    @Test
    void testGetCommand_CreateCommand() {
        Command command = commandFactory.getCommand("CREATE subdir");
        assertInstanceOf(CreateCommand.class, command);
        assertArrayEquals(new String[]{"subdir"}, command.getArgs());
    }

    @Test
    void testGetCommand_ListCommand() {
        Command command = commandFactory.getCommand("LIST");
        assertInstanceOf(ListCommand.class, command);
        assertArrayEquals(new String[]{}, command.getArgs());
    }

    @Test
    void testGetCommand_MoveCommand() {
        Command command = commandFactory.getCommand("MOVE source destination");
        assertInstanceOf(MoveCommand.class, command);
        assertArrayEquals(new String[]{"source", "destination"}, command.getArgs());
    }

    @Test
    void testGetCommand_DeleteCommand() {
        Command command = commandFactory.getCommand("DELETE subdir");
        assertInstanceOf(DeleteCommand.class, command);
        assertArrayEquals(new String[]{"subdir"}, command.getArgs());
    }

    @Test
    void testGetCommand_InvalidCommand() {
        assertThrows(InvalidCommandException.class, () -> commandFactory.getCommand("INVALID"));
    }

    @Test
    void testGetCommand_NullCommand() {
        assertThrows(NullPointerException.class, () -> commandFactory.getCommand(null));
    }

    @Test
    void testGetCommand_EmptyCommand() {
        assertThrows(IllegalArgumentException.class, () -> commandFactory.getCommand(""));
    }
}