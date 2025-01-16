import org.example.Flashcard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlashcardTest {


    @Test
    void givenFlashcardAttributes_whenInitialized_thenValuesAreSetCorrectly(){
        Flashcard card = new Flashcard("term1", "definition1", 0);

        assertEquals("term1", card.getTerm());
        assertEquals("definition1", card.getDefinition());
        assertEquals(0, card.getMistakes());
    }

    @Test
    void givenNewValues_whenSettersCalled_thenValuesAreUpdated() {
        Flashcard card = new Flashcard("term1", "definition1", 0);

        card.setTerm("newTerm");
        card.setDefinition("newDefinition");
        card.setMiskates(5);

        assertEquals("newTerm", card.getTerm());
        assertEquals("newDefinition", card.getDefinition());
        assertEquals(5, card.getMistakes());
    }


    @Test
    void givenFlashcard_whenSumMistakeCalled_thenMisktakesAreIncremented(){
        Flashcard card = new Flashcard("term1","definition1",0);

        card.sumMistake();
        assertEquals(1,card.getMistakes());

        card.sumMistake();
        assertEquals(2,card.getMistakes());
    }






}
