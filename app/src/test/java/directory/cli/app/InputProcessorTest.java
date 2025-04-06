package directory.cli.app;

import directory.cli.domain.Directory;
import directory.cli.domain.commands.Command;
import directory.cli.domain.commands.CommandFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class InputProcessorTest {


    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
    }

    @Test
    void testStartProcessingInput() {
        String input = "TEST source destination\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        Command mockCommand = mock(Command.class);
        when(mockCommand.execute(any(Directory.class))).thenReturn(Optional.of("Command executed"));

        CommandFactory commandFactory = mock(CommandFactory.class);
        when(commandFactory.getCommand("TEST source destination")).thenReturn(mockCommand);

        InputProcessor inputProcessor = new InputProcessor(commandFactory, inputStream);
        inputProcessor.startProcessingInput(printStream);

        String expectedOutput = "TEST source destination\nCommand executed\n";
        assertEquals(expectedOutput, outputStream.toString().replace("\r\n", "\n"));
    }

    @Test
    void testStartProcessingInputWithInvalidCommand() {
        String input = "INVALID_COMMAND\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());

        Command mockCommand = mock(Command.class);
        when(mockCommand.execute(any(Directory.class))).thenThrow(new IllegalArgumentException("Invalid command"));

        CommandFactory commandFactory = mock(CommandFactory.class);
        when(commandFactory.getCommand("INVALID_COMMAND")).thenReturn(mockCommand);

        InputProcessor inputProcessor = new InputProcessor(commandFactory, inputStream);
        inputProcessor.startProcessingInput(printStream);

        String expectedOutput = "INVALID_COMMAND\nInvalid command\n";
        assertEquals(expectedOutput, outputStream.toString().replace("\r\n", "\n"));
    }

    @Test
    void validateFullOutput() throws IOException {
        try (
                InputStream input = getClass().getClassLoader().getResourceAsStream("command-input.txt");
                InputStream expectedOutputStream = getClass().getClassLoader().getResourceAsStream("command-output.txt");
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                PrintStream printStream = new PrintStream(outputStream)
        ) {
            CommandFactory commandFactory = new CommandFactory();
            InputProcessor inputProcessor = new InputProcessor(commandFactory, input);
            inputProcessor.startProcessingInput(printStream);

            assertNotNull(expectedOutputStream);


            String expectedOutput = new String(expectedOutputStream.readAllBytes()).replace("\r\n", "\n");
            String actualOutput = outputStream.toString().replace("\r\n", "\n");

            assertEquals(expectedOutput, actualOutput);
        }
    }
}
