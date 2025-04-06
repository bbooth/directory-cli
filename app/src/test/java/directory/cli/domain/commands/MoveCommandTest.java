package directory.cli.domain.commands;

import directory.cli.domain.Directory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MoveCommandTest {

    private Directory mockDirectory;

    @BeforeEach
    void setUp() {
        mockDirectory = mock(Directory.class);
    }

    @Test
    void testExecute_ValidMove() {
        MoveCommand command = new MoveCommand(new String[]{"source", "target"});
        doNothing().when(mockDirectory).moveSubDirectory("source", "target");

        assertDoesNotThrow(() -> command.execute(mockDirectory));
        verify(mockDirectory, times(1)).moveSubDirectory("source", "target");
    }

    @Test
    void testExecute_SourceDirectoryDoesNotExist() {
        MoveCommand command = new MoveCommand(new String[]{"nonexistentsource", "target"});
        doThrow(new IllegalArgumentException("Source directory not found")).when(mockDirectory).moveSubDirectory("nonexistentsource", "target");

        assertThrows(IllegalArgumentException.class, () -> command.execute(mockDirectory));
        verify(mockDirectory, times(1)).moveSubDirectory("nonexistentsource", "target");
    }

    @Test
    void testExecute_TargetDirectoryDoesNotExist() {
        MoveCommand command = new MoveCommand(new String[]{"source", "nonexistenttarget"});
        doThrow(new IllegalArgumentException("Target directory not found")).when(mockDirectory).moveSubDirectory("source", "nonexistenttarget");

        assertThrows(IllegalArgumentException.class, () -> command.execute(mockDirectory));
        verify(mockDirectory, times(1)).moveSubDirectory("source", "nonexistenttarget");
    }

    @Test
    void testExecute_InvalidSourceDirectoryName() {
        assertThrows(IllegalArgumentException.class, () -> new MoveCommand(new String[]{"InvalidSource", "target"}));
        verify(mockDirectory, never()).moveSubDirectory(anyString(), anyString());
    }

    @Test
    void testExecute_InvalidTasrgetDirectoryName() {
        assertThrows(IllegalArgumentException.class, () -> new MoveCommand(new String[]{"source", "InvalidTarget"}));
        verify(mockDirectory, never()).moveSubDirectory(anyString(), anyString());
    }

    @Test
    void testConstructor_NullArgs() {
        assertThrows(NullPointerException.class, () -> new MoveCommand(null));
    }

    @Test
    void testConstructor_InvalidArgsCount() {
        assertThrows(IllegalArgumentException.class, () -> new MoveCommand(new String[]{}));
        assertThrows(IllegalArgumentException.class, () -> new MoveCommand(new String[]{"arg1"}));
        assertThrows(IllegalArgumentException.class, () -> new MoveCommand(new String[]{"arg1", "arg2", "arg3"}));
    }
}