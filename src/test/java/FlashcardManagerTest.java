import org.example.FlashcardManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.junit.jupiter.api.Assertions.*;

class FlashcardManagerTest {

    private FlashcardManager flashcardManager;

    @BeforeEach
    void setUp() {
        flashcardManager = new FlashcardManager();
    }

    @Test
    void givenNewFlashcard_whenAddCard_thenCardIsAdded() {
        flashcardManager.addCard("term", "definition", 0);

        assertTrue(flashcardManager.existsTerm("term"));
        assertFalse(flashcardManager.existDefinition("wrong_definition"));
    }

    @Test
    void givenExistingFlashcard_whenRemoveCard_thenCardIsRemoved() {
        flashcardManager.addCard("term", "definition", 0);
        flashcardManager.removeCard("term");

        assertFalse(flashcardManager.existsTerm("term"));
    }

    @Test
    void givenValidUserAnswer_whenAskCard_thenCorrectResponseLogged() {
        flashcardManager.addCard("term", "definition", 0);

        simulateUserInput("definition");
        ByteArrayOutputStream outputStream = simulateOutput();

        flashcardManager.askCard(1);

        assertTrue(outputStream.toString().contains("Correct!"));
    }

    @Test
    void givenWrongUserAnswer_whenAskCard_thenWrongResponseLogged() {
        flashcardManager.addCard("term", "definition", 0);

        simulateUserInput("wrong_answer");
        ByteArrayOutputStream outputStream = simulateOutput();

        flashcardManager.askCard(1);

        assertTrue(outputStream.toString().contains("Wrong."));
    }

    @Test
    void givenDefinitionExists_whenWhereDefinition_thenReturnCorrectTerm() {
        flashcardManager.addCard("term", "definition", 0);

        assertEquals("term", flashcardManager.whereDefinition("definition"));
    }

    @Test
    void givenEmptyManager_whenHardestCard_thenLogsNoCards() {
        ByteArrayOutputStream outputStream = simulateOutput();

        flashcardManager.hardestCard();

        assertTrue(outputStream.toString().contains("There are no cards with errors."));
    }

    @Test
    void givenCardsWithErrors_whenHardestCard_thenLogsHardestCards() {
        flashcardManager.addCard("term1", "definition1", 3);
        flashcardManager.addCard("term2", "definition2", 3);

        ByteArrayOutputStream outputStream = simulateOutput();

        flashcardManager.hardestCard();

        assertTrue(outputStream.toString().contains("The hardest cards are"));
    }

    @Test
    void givenValidFilename_whenExport_thenFileCreatedWithCorrectData() throws Exception {
        flashcardManager.addCard("term", "definition", 2);
        String filename = "test_export.txt";

        flashcardManager.export(filename);

        Path path = Path.of(filename);
        assertTrue(Files.exists(path));
        assertTrue(Files.readString(path).contains("term,definition,2"));

        Files.delete(path);
    }

    @Test
    void givenValidFile_whenImport_thenCardsAreLoaded() throws Exception {
        String filename = "test_import.txt";
        Files.writeString(Path.of(filename), "term,definition,2\n");

        flashcardManager.importF(filename);

        assertTrue(flashcardManager.existsTerm("term"));

        Files.delete(Path.of(filename));
    }

    @Test
    void givenLog_whenWriteLog_thenFileContainsLogs() throws Exception {
        flashcardManager.saveLogMessage("Log message");
        String filename = "test_log.txt";

        flashcardManager.writeLog(filename);

        Path path = Path.of(filename);
        assertTrue(Files.exists(path));
        assertTrue(Files.readString(path).contains("Log message"));

        Files.delete(path);
    }

    private void simulateUserInput(String input) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    private ByteArrayOutputStream simulateOutput() {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        return out;
    }
}
