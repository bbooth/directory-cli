package directory.cli.domain;

import directory.cli.domain.exceptions.InvalidDirectoryException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryTest {

    private Directory directory;

    @BeforeEach
    void setUp() {
        directory = new Directory();
    }

    @Test
    void testAddSubDirectory_Valid() {
        assertDoesNotThrow(() -> directory.addSubDirectory("valid"));
        assertEquals("valid", directory.printStructure());
    }

    @Test
    void testAddSubDirectoryRoot() {
        assertThrows(InvalidDirectoryException.class, () -> directory.addSubDirectory("root"));
        assertEquals("", directory.printStructure());
    }


    @Test
    void testAddSubDirectoryToExistingDirectory_Valid() {
        assertDoesNotThrow(() -> directory.addSubDirectory("a"));
        assertDoesNotThrow(() -> directory.addSubDirectory("a/b"));
        assertEquals("a\n  b", directory.printStructure());
    }

    @Test
    void testAddSubDirectory_AlreadyExists() {
        assertDoesNotThrow(() -> directory.addSubDirectory("existing"));
        assertDoesNotThrow(() -> directory.addSubDirectory("existing"));
        assertEquals("existing", directory.printStructure());
    }

    @Test
    void testAddSubDirectory_InvalidName() {
        assertThrows(IllegalArgumentException.class, () -> directory.addSubDirectory("Invalid"));
    }

    @Test
    void testDeleteSubDirectory_Valid() {
        assertDoesNotThrow(() -> directory.addSubDirectory("valid"));
        assertEquals("valid", directory.printStructure());

        assertDoesNotThrow(() -> directory.deleteSubDirectory("valid"));
        assertEquals("", directory.printStructure());
    }

    @Test
    void testDeleteSubDirectoryRoot() {
        assertThrows(InvalidDirectoryException.class, () -> directory.deleteSubDirectory("root"));
    }

    @Test
    void testDeleteSubDirectory_DoesNotExist() {
        assertThrows(IllegalArgumentException.class, () -> directory.deleteSubDirectory("nonexistent"));
    }

    @Test
    void testListSubDirectories_Empty() {
        assertTrue(directory.printStructure().isEmpty());
    }

    @Test
    void testListSubDirectories_NonEmpty() {
        directory.addSubDirectory("a");
        directory.addSubDirectory("b");
        assertEquals("a\nb", directory.printStructure());
    }

    @Test
    void testMoveSubDirectory_Valid() {
        directory.addSubDirectory("source");
        directory.addSubDirectory("target");

        assertEquals("source\ntarget", directory.printStructure());
        assertDoesNotThrow(() -> directory.moveSubDirectory("source", "target"));
        assertEquals("target\n  source", directory.printStructure());
    }

    @Test
    void testMoveSubDirectoryToRoot_Valid() {
        directory.addSubDirectory("a/b");

        assertEquals("a\n  b", directory.printStructure());
        assertDoesNotThrow(() -> directory.moveSubDirectory("a/b", "root"));
        assertEquals("a\nb", directory.printStructure());
    }

    @Test
    void testMoveSubDirectoryRoot() {
        directory.addSubDirectory("a");

        assertEquals("a", directory.printStructure());
        assertThrows(InvalidDirectoryException.class, () -> directory.moveSubDirectory("root", "target"));
        assertEquals("a", directory.printStructure());
    }

    @Test
    void testMoveSubDirectoryMultiLevel_Valid() {
        directory.addSubDirectory("a/b/c");
        directory.addSubDirectory("d");

        assertEquals("a\n  b\n    c\nd", directory.printStructure());
        assertDoesNotThrow(() -> directory.moveSubDirectory("a/b", "d"));
        assertEquals("a\nd\n  b\n    c", directory.printStructure());
    }

    @Test
    void testMoveSubDirectory_SourceDoesNotExist() {
        directory.addSubDirectory("target");
        assertThrows(IllegalArgumentException.class, () -> directory.moveSubDirectory("nonexistentsource", "target"));
    }

    @Test
    void testMoveSubDirectory_TargetDoesNotExist() {
        directory.addSubDirectory("source");
        assertThrows(IllegalArgumentException.class, () -> directory.moveSubDirectory("source", "nonexistenttarget"));
    }
}