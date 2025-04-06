package directory.cli.app;

import directory.cli.domain.commands.CommandFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * The App class serves as the entry point for the directory CLI application.
 */
public class Application {

    /**
     * The main method that starts the application. The processing of input is not thread safe.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        CommandFactory commandFactory = new CommandFactory();
        InputStream inputStream = System.in;

        if (args.length > 0) {
            try {
                inputStream = new FileInputStream(args[0]);
            } catch (FileNotFoundException e) {
                System.err.println("File not found: " + args[0]);
                System.exit(1);
            }
        }

        InputProcessor inputProcessor = new InputProcessor(commandFactory, inputStream);
        inputProcessor.startProcessingInput(System.out);
    }
}