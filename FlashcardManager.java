package flashcards;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class FlashcardManager {
    private Map<String,Flashcard> flashcards;

    public FlashcardManager(){
        this.flashcards = new HashMap<>();
    }

    public void addCard(String term, String definition){
        Flashcard card = new Flashcard(term,definition);
        flashcards.put(term,card);
        System.out.println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
    }

    public void removeCard(String term){
        if (flashcards.containsKey(term)){
            flashcards.remove(term);
            System.out.println("The card has been removed.");
        }else {
            System.out.println("Can't remove \"" + term + "\": there is no such card.");
        }
    }

    public void askCard(int times) {
        Random rand = new Random();
        String[] terms = flashcards.keySet().toArray(new String[0]);
        String randomTerm = terms[rand.nextInt(terms.length)];
        Flashcard card = flashcards.get(randomTerm);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Print the definition of \"" + randomTerm + "\":");
        String userAnswer = scanner.nextLine();
        if (userAnswer.equals(card.getDefinition())) {
            System.out.println("Correct!");
        } else {
            System.out.println("Wrong. The right answer is \"" + card.getDefinition() + "\".");
        }
    }

    public int getCardCount(){
        return flashcards.size();
    }

}
