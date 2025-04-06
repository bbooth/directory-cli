package directory.cli.domain.commands;

import directory.cli.domain.Directory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateCommandTest {

    private Directory mockDirectory;

    @BeforeEach
    void setUp() {
        mockDirectory = mock(Directory.class);
    }

    @Test
    void testExecute_ValidDirectory() {
        CreateCommand command = new CreateCommand(new String[]{"valid"});
        doNothing().when(mockDirectory).addSubDirectory("valid");

        assertDoesNotThrow(() -> command.execute(mockDirectory));
        verify(mockDirectory, times(1)).addSubDirectory("valid");
    }

    @Test
    void testExecute_DirectoryAlreadyExists() {
        CreateCommand command = new CreateCommand(new String[]{"existing"});
        doNothing().when(mockDirectory).addSubDirectory("existing");

        assertDoesNotThrow(() -> command.execute(mockDirectory));
        assertDoesNotThrow(() -> command.execute(mockDirectory));
        verify(mockDirectory, times(2)).addSubDirectory("existing");
    }

    @Test
    void testExecute_InvalidDirectoryName() {
        assertThrows(IllegalArgumentException.class, () -> new CreateCommand(new String[]{"Invalid"}));
        verify(mockDirectory, never()).addSubDirectory(anyString());
    }

    @Test
    void testConstructor_NullArgs() {
        assertThrows(NullPointerException.class, () -> new CreateCommand(null));
    }

    @Test
    void testConstructor_InvalidArgsCount() {
        assertThrows(IllegalArgumentException.class, () -> new CreateCommand(new String[]{}));
        assertThrows(IllegalArgumentException.class, () -> new CreateCommand(new String[]{"arg1", "arg2"}));
    }
}