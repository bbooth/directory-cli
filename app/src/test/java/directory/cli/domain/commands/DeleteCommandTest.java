package directory.cli.domain.commands;

import directory.cli.domain.Directory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class DeleteCommandTest {

    private Directory mockDirectory;

    @BeforeEach
    void setUp() {
        mockDirectory = mock(Directory.class);
    }

    @Test
    void testExecute_ValidDirectory() {
        DeleteCommand command = new DeleteCommand(new String[]{"valid"});
        doNothing().when(mockDirectory).deleteSubDirectory("valid");

        assertDoesNotThrow(() -> command.execute(mockDirectory));
        verify(mockDirectory, times(1)).deleteSubDirectory("valid");
    }

    @Test
    void testExecute_DirectoryDoesNotExist() {
        DeleteCommand command = new DeleteCommand(new String[]{"nonexistent"});
        doThrow(new IllegalArgumentException("Cannot delete nonexistent - Directory not found")).when(mockDirectory).deleteSubDirectory("nonexistent");

        assertThrows(IllegalArgumentException.class, () -> command.execute(mockDirectory));
        verify(mockDirectory, times(1)).deleteSubDirectory("nonexistent");
    }

    @Test
    void testExecute_InvalidDirectoryName() {
        assertThrows(IllegalArgumentException.class, () -> new DeleteCommand(new String[]{"Invalid"}));
        verify(mockDirectory, never()).deleteSubDirectory(anyString());
    }

    @Test
    void testConstructor_NullArgs() {
        assertThrows(NullPointerException.class, () -> new DeleteCommand(null));
    }

    @Test
    void testConstructor_InvalidArgsCount() {
        assertThrows(IllegalArgumentException.class, () -> new DeleteCommand(new String[]{}));
        assertThrows(IllegalArgumentException.class, () -> new DeleteCommand(new String[]{"arg1", "arg2"}));
    }
}