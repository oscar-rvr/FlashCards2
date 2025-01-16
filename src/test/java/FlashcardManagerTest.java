import org.example.Flashcard;
import org.example.FlashcardManager;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class FlashcardManagerTest {



    @Test
    void givenNewCard_whenAdded_thenCardIsStored() {
        FlashcardManager manager = new FlashcardManager();
        manager.addCard("term1", "definition1", 0);

        assertTrue(manager.existsTerm("term1"));
    }

    @Test
    void givenExistingTerm_whenChecked_thenReturnsTrue() {

        FlashcardManager manager = new FlashcardManager();
        manager.addCard("term1", "definition1", 0);

        assertTrue(manager.existsTerm("term1"));
    }

    @Test
    void givenNonExistingTerm_whenChecked_thenReturnsFalse() {
        FlashcardManager manager = new FlashcardManager();

        assertFalse(manager.existsTerm("term1"));
    }


    @Test
    void givenExistingDefinition_whenChecked_thenReturnsTrue() {
        FlashcardManager manager = new FlashcardManager();
        manager.addCard("term1", "definition1", 0);

        assertTrue(manager.existDefinition("definition1"));
    }

    @Test
    void givenNonExistingDefinition_whenChecked_thenReturnsFalse() {
        FlashcardManager manager = new FlashcardManager();

        assertFalse(manager.existDefinition("definition1"));
    }

    @Test
    void givenExistingCard_whenRemoved_thenCardIsDeleted() {
        FlashcardManager manager = new FlashcardManager();
        manager.addCard("term1", "definition1", 0);

        manager.removeCard("term1");

        assertFalse(manager.existsTerm("term1"));
    }

    @Test
    void givenNonExistingCard_whenRemoved_thenLogShowsErrorMessage() {

        FlashcardManager manager = new FlashcardManager();

        manager.removeCard("term1");


        assertTrue(manager.getlogList().contains("Can't remove \"term1\": there is no such card."));
    }

    @Test
    void givenCards_whenAskCard_thenCorrectAnswerLogsCorrectMessage() {
        FlashcardManager manager = new FlashcardManager();
        manager.addCard("term1", "definition1", 0);

        InputStream in = new ByteArrayInputStream("definition1\n".getBytes());
        System.setIn(in);

        manager.askCard(1);

        assertTrue(manager.getlogList().contains("Correct!"));
    }

    @Test
    void givenCards_whenAskCard_thenWrongAnswerLogsErrorMessage() {
        FlashcardManager manager = new FlashcardManager();
        manager.addCard("term1", "definition1", 0);

        InputStream in = new ByteArrayInputStream("wrongDefinition\n".getBytes());
        System.setIn(in);

        manager.askCard(1);

        assertTrue(manager.getlogList().contains("Wrong. The right answer is \"definition1\"."));
    }

    @Test
    void givenCards_whenAskCardWithCorrectButDifferentDefinition_thenLogsAlternateTermMessage() {
        FlashcardManager manager = new FlashcardManager();
        manager.addCard("term1", "definition1", 0);
        manager.addCard("term2", "definition2", 0);

        InputStream in = new ByteArrayInputStream("definition2\n".getBytes());
        System.setIn(in);

        manager.askCard(1);

        assertTrue(manager.getlogList().stream().anyMatch(log -> log.contains("but your definition is correct for \"term2\".")));
    }

    @Test
    void givenNoCards_whenAskCard_thenNoInteraction() {
        FlashcardManager manager = new FlashcardManager();

        manager.askCard(1);

         assertTrue(manager.getlogList().isEmpty());
    }

    @Test
    void givenExistingDefinition_whenWhereDefinition_thenReturnsCorrectTerm() {
        FlashcardManager manager = new FlashcardManager();
        manager.addCard("term1", "definition1", 0);

        String result = manager.whereDefinition("definition1");

        assertEquals("term1", result);
    }

    @Test
    void givenNonExistingDefinition_whenWhereDefinition_thenReturnsEmptyString() {
        FlashcardManager manager = new FlashcardManager();
        manager.addCard("term1", "definition1", 0);

        String result = manager.whereDefinition("nonExistentDefinition");

        assertEquals("", result);
    }

    @Test
    void givenMultipleDefinitions_whenWhereDefinition_thenReturnsCorrectTerm() {
        FlashcardManager manager = new FlashcardManager();
        manager.addCard("term1", "definition1", 0);
        manager.addCard("term2", "definition2", 0);

        String result = manager.whereDefinition("definition2");

        assertEquals("term2", result);
    }


    // Test para export
    @Test
    void givenValidFilename_whenExport_thenSavesAllCardsToFile() {
        FlashcardManager manager = new FlashcardManager();
        manager.addCard("term1", "definition1", 0);
        manager.addCard("term2", "definition2", 1);

        manager.export("test_export.txt");

        Path path = Paths.get("test_export.txt");
        assertTrue(Files.exists(path));
    }




    @Test
    void givenNoCardsWithErrors_whenHardestCard_thenLogsNoErrors() {
        FlashcardManager manager = new FlashcardManager();

        manager.hardestCard();


    }

    @Test
    void givenCardsWithErrors_whenHardestCard_thenLogsHardestCards() {
        FlashcardManager manager = new FlashcardManager();
        manager.addCard("term1", "definition1", 3);
        manager.addCard("term2", "definition2", 5);

        manager.hardestCard();


    }


    @Test
    void givenValidPath_whenAllowed_thenReturnsTrue() {
        FlashcardManager manager = new FlashcardManager();
         boolean result = manager.allowed("valid_path.txt");

        assertTrue(result);
    }



    @Test
    void whenGetCardCount_thenReturnsCorrectCount() {
        FlashcardManager manager = new FlashcardManager();
        manager.addCard("term1", "definition1", 0);
        manager.addCard("term2", "definition2", 0);

        int count = manager.getCardCount();

        assertEquals(2, count);
    }


    @Test
    void whenResetStats_thenAllCardsAreCleared() {
        FlashcardManager manager = new FlashcardManager();
        manager.addCard("term1", "definition1", 0);
        manager.addCard("term2", "definition2", 0);

        manager.resetStats();

        assertEquals(0, manager.getCardCount());
    }



}
