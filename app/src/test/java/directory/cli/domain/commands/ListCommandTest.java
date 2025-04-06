package directory.cli.domain.commands;

import directory.cli.domain.Directory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListCommandTest {

    private Directory mockDirectory;

    @BeforeEach
    void setUp() {
        mockDirectory = mock(Directory.class);
    }

    @Test
    void testExecute() {
        ListCommand command = new ListCommand(new String[]{});
        when(mockDirectory.printStructure()).thenReturn("a");

        Optional<String> result = command.execute(mockDirectory);
        assertTrue(result.isPresent());
        assertEquals("a", result.get());
        verify(mockDirectory, times(1)).printStructure();
    }

    @Test
    void testExecute_InvalidArgs() {
        assertThrows(IllegalArgumentException.class, () -> new ListCommand(new String[]{"arg1"}));
    }

    @Test
    void testConstructor_NullArgs() {
        assertThrows(NullPointerException.class, () -> new ListCommand(null));
    }
}