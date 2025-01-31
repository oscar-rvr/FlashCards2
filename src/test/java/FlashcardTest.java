import org.example.Flashcard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlashcardTest {
    private static final String INITIAL_TERM = "term1";
    private static final String INITIAL_DEFINITION = "definition1";
    private static final String NEW_TERM = "newTerm";
    private static final String NEW_DEFINITION = "newDefinition";
    private Flashcard card;

    @BeforeEach
    void setUp() {
        card = new Flashcard(INITIAL_TERM, INITIAL_DEFINITION, 0);
    }

    @Test
    void givenFlashcardAttributes_whenInitialized_thenValuesAreSetCorrectly() {
        assertEquals(INITIAL_TERM, card.getTerm());
        assertEquals(INITIAL_DEFINITION, card.getDefinition());
        assertEquals(0, card.getMistakes());
    }

    @Test
    void givenNewValues_whenSettersCalled_thenValuesAreUpdated() {
        card.setTerm(NEW_TERM);
        card.setDefinition("newDefinition");
        card.setMistakes(5);

        assertEquals(NEW_TERM, card.getTerm());
        assertEquals(NEW_DEFINITION, card.getDefinition());
        assertEquals(5, card.getMistakes());
    }

    @Test
    void givenFlashcard_whenSumMistakeCalled_thenMisktakesAreIncremented() {
        card.sumMistake();
        assertEquals(1,card.getMistakes());

        card.sumMistake();
        assertEquals(2,card.getMistakes());
    }
}