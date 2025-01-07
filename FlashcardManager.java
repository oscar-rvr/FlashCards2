package flashcards;

import java.io.*;
import java.util.*;

public class FlashcardManager {
    private Map<String,Flashcard> flashcards;

    public FlashcardManager(){
        this.flashcards = new HashMap<>();
    }

    public void addCard(String term, String definition){
        Flashcard card = new Flashcard(term,definition);
        flashcards.put(term,card);

    }
    public boolean existsTerm(String term){
        if(flashcards.containsKey(term)){
            return true;
        }
        return false;
    }
public boolean existDefinition(String definition){
        for(Map.Entry<String, Flashcard> entry : flashcards.entrySet()){
            if (entry.getValue().getDefinition().equals(definition)){
                return true;
            }
        }
        return false;

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
        for (int i =0; i< times; i++){
            Random rand = new Random();
            String[] terms = flashcards.keySet().toArray(new String[0]);
            String randomTerm = terms[rand.nextInt(terms.length)];
            Flashcard card = flashcards.get(randomTerm);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Print the definition of \"" + randomTerm + "\":");
            String userAnswer = scanner.nextLine();
            if (userAnswer.equals(card.getDefinition())) {
                System.out.println("Correct!");
            } else if(!whereDefinition(userAnswer).isEmpty()) {
                System.out.println("Wrong. The right answer is \""+card.getDefinition()+"\", but your definition is correct for \""+whereDefinition(userAnswer)+"\".");
            }else{
                System.out.println("Wrong. The right answer is \"" + card.getDefinition() + "\".");
               // System.out.println(!whereDefinition(userAnswer).isEmpty());
            }
        }

    }

    public String whereDefinition(String definition){
        for(Map.Entry<String, Flashcard> entry : flashcards.entrySet()){
            if (entry.getValue().getDefinition().equals(definition)){
                return entry.getValue().getTerm().toString();
            }
        }
        return "";

    }
    public void export(String filename){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for (  Map.Entry<String,Flashcard> entry : flashcards.entrySet() ){
                      String term = entry.getKey();
                      String definition = (entry.getValue().getDefinition());
                      writer.write(term+","+definition);
                      writer.write("\n");
            }
           // writer.close();
            writer.flush();

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
        System.out.println(getCardCount()+" cards have been saved.");
    }

    public void importF(String filename){
 //flashcards.clear();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;


           int contI=0;
            while( (line = reader.readLine()) != null ){
                String term,definition;
                String[] cardReaded = line.split(",");
                term=cardReaded[0];
                definition=cardReaded[1];


                contI++;
                addCard(term,definition);
            }
            System.out.println(contI+" cards have been loaded.");
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println("File not found.");
        }
    }
    public int getCardCount(){
        return flashcards.size();
    }

}
