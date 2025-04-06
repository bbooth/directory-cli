package directory.cli.domain.commands;

import directory.cli.domain.Directory;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class BaseCommandTest {

    @Test
    void testConstructor_NullArgs() {
        assertThrows(NullPointerException.class, () -> new TestCommand(null, 1));
    }

    @Test
    void testConstructor_InvalidArgsCount() {
        assertThrows(IllegalArgumentException.class, () -> new TestCommand(new String[]{"arg1"}, 2));
    }

    @Test
    void testConstructor_ValidArgs() {
        TestCommand command = new TestCommand(new String[]{"valid"}, 1);
        assertArrayEquals(new String[]{"valid"}, command.getArgs());
    }



    // A simple implementation of BaseCommand for testing purposes
    static class TestCommand extends BaseCommand {
        protected TestCommand(String[] args, int expectedArgsCount) {
            super(args, expectedArgsCount);
        }

        @Override
        public Optional<String> execute(Directory directory) {
            return Optional.empty();
        }
    }
}