import java.util.Scanner;
import org.example.FlashcardController;
import org.example.FlashcardManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class FlashcardControllerTest {
    private FlashcardManager manager;
    private Scanner scanner;
    private FlashcardController controller;

    @BeforeEach
    void setUp() {
        manager = mock(FlashcardManager.class);
        scanner = mock(Scanner.class);
        controller = new FlashcardController(manager, scanner);
    }

    @Test
    void processCommand_addCard_alreadyExists() {
        when(scanner.nextLine()).thenReturn("term", "definition");
        when(manager.existsTerm("term")).thenReturn(true);

        controller.processCommand("add");

        verify(manager).printLogMessage("The card:");
        verify(manager).printLogMessage("The card \"term\" already exists.");
    }

    @Test
    void processCommand_addCard_newCard() {
        when(scanner.nextLine()).thenReturn("term", "definition");
        when(manager.existsTerm("term")).thenReturn(false);
        when(manager.existDefinition("definition")).thenReturn(false);

        controller.processCommand("add");

        verify(manager).addCard("term", "definition", 0);
        verify(manager).printLogMessage("The pair (\"term\":\"definition\") has been added.");
    }

    @Test
    void processCommand_removeCard_exists() {
        when(scanner.nextLine()).thenReturn("term");

        controller.processCommand("remove");

        verify(manager).removeCard("term");
    }

    @Test
    void processCommand_import() {
        when(scanner.nextLine()).thenReturn("filename.txt");

        controller.processCommand("import");

        verify(manager).importF("filename.txt");
    }

    @Test
    void processCommand_export() {
        when(scanner.nextLine()).thenReturn("filename.txt");

        controller.processCommand("export");

        verify(manager).export("filename.txt");
    }

    @Test
    void processCommand_ask() {
        when(scanner.nextLine()).thenReturn("3");
        when(scanner.nextInt()).thenReturn(3);

        controller.processCommand("ask");

        verify(manager).askCard(3);
    }



    @Test
    void processCommand_log() {
        when(scanner.nextLine()).thenReturn("logfile.txt");

        controller.processCommand("log");

        verify(manager).writeLog("logfile.txt");
    }

    @Test
    void processCommand_resetStats() {
        controller.processCommand("reset stats");

        verify(manager).resetStats();
    }

    @Test
    void processCommand_exit() {
        controller.processCommand("exit");

        verify(manager).printLogMessage("Bye Bye");
    }

    @Test
    void processCommand_unknownCommand() {
        controller.processCommand("unknown");

        verify(manager).printLogMessage("Unknown command");
    }
}
