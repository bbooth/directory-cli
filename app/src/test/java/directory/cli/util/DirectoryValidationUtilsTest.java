package directory.cli.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DirectoryValidationUtilsTest {
    @Test
    void testValidateDirectoryName_NullOrEmpty() {
        assertThrows(IllegalArgumentException.class, () -> DirectoryValidationUtils.validateDirectoryName(null));
        assertThrows(IllegalArgumentException.class, () -> DirectoryValidationUtils.validateDirectoryName(""));
    }

    @Test
    void testValidateDirectoryName_InvalidCharacters() {
        assertThrows(IllegalArgumentException.class, () -> DirectoryValidationUtils.validateDirectoryName("Invalid"));
        assertThrows(IllegalArgumentException.class, () -> DirectoryValidationUtils.validateDirectoryName("valid/Invalid"));
        assertThrows(IllegalArgumentException.class, () -> DirectoryValidationUtils.validateDirectoryName("1valid"));
        assertThrows(IllegalArgumentException.class, () -> DirectoryValidationUtils.validateDirectoryName("valid//valid"));
        assertThrows(IllegalArgumentException.class, () -> DirectoryValidationUtils.validateDirectoryName("valid///valid"));
        assertThrows(IllegalArgumentException.class, () -> DirectoryValidationUtils.validateDirectoryName("valid/"));
    }

    @Test
    void testValidateDirectoryName_Valid() {
        assertDoesNotThrow(() -> DirectoryValidationUtils.validateDirectoryName("valid"));
        assertDoesNotThrow(() -> DirectoryValidationUtils.validateDirectoryName("valid/subdir"));
    }
}
