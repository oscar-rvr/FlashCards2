package org.example;

import java.io.*;
import java.util.*;


public class FlashcardManager {
    private Map<String,Flashcard> flashcards;
     private List<String> logList = new ArrayList<>();
    public FlashcardManager(){
        this.flashcards = new HashMap<>();
    }

    public void addCard(String term, String definition,int mistakes){
        Flashcard card = new Flashcard(term,definition,mistakes);
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
             printLogMessage("The card has been removed.");
        }else {
             printLogMessage("Can't remove \"" + term + "\": there is no such card.");
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
                 printLogMessage("Correct!");
            } else if(!whereDefinition(userAnswer).isEmpty()) {
                 printLogMessage("Wrong. The right answer is \""+card.getDefinition()+"\", but your definition is correct for \""+whereDefinition(userAnswer)+"\".");
                    card.sumMistake();
            }else{
                 printLogMessage("Wrong. The right answer is \"" + card.getDefinition() + "\".");
                card.sumMistake();
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
                      int mistakes = (entry.getValue().getMistakes());
                      writer.write(term+","+definition+","+mistakes);
                      writer.write("\n");
            }
             writer.flush();

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
         printLogMessage(getCardCount()+" cards have been saved.");
    }

    public void importF(String filename){
         try(BufferedReader reader = new BufferedReader(new FileReader(filename))){
            String line;


           int contI=0;
            while( (line = reader.readLine()) != null ){
                String term,definition;
                int mistakes=0;
                String[] cardReaded = line.split(",");
                term=cardReaded[0];
                definition=cardReaded[1];
                mistakes= Integer.parseInt(cardReaded[2]);


                contI++;
                addCard(term,definition,mistakes);
            }
             printLogMessage(contI+" cards have been loaded.");
        } catch (IOException e) {
             printLogMessage("File not found.");
        }
    }
    public void hardestCard(){
       if(flashcards.isEmpty()){
            printLogMessage("There are no cards with errors.");
           return;
       }

       int maxMistakes=0;
       List<String> hardestCards=new ArrayList<>();
       for (Map.Entry<String,Flashcard> entry : flashcards.entrySet()){
           Flashcard card=entry.getValue();
           int cardMistakes= card.getMistakes();

           if(cardMistakes>maxMistakes){
               maxMistakes=cardMistakes;
               hardestCards.clear();
               hardestCards.add(card.getTerm());

           }else if(cardMistakes == maxMistakes){
               hardestCards.add(card.getTerm());
           }


       }

       if(maxMistakes==0){

            printLogMessage("There are no cards with errors.");
       }else{
           List<String> quotedCards = new ArrayList<>();
           for (String card : hardestCards){
               quotedCards.add("\""+ card +"\"");
           }
           String cardList = String.join(",", quotedCards); // Convierte la lista en una cadena con los términos separados por ", "
           if(quotedCards.size()<2){
               printLogMessage("The hardest card is " + cardList + ". You have " + maxMistakes + " errors answering it.");

           }else{
               printLogMessage("The hardest cards are " + cardList + ". You have " + maxMistakes + " errors answering them.");

           }

        }


    }
    public int getCardCount(){
        return flashcards.size();
    }

    public void printLogMessage(String log){
        logList.add(log);
        System.out.println(log);
    }
    public void saveLogMessage(String line){
        logList.add(line);
    }
    public void writeLog(String filename){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filename))){
            for(String line : logList){
                writer.write(line);
                writer.newLine();
            }
            printLogMessage("The log has been saved.");
        } catch (IOException e) {

            printLogMessage("An error occurred while saving the log.");
            throw new RuntimeException(e);
        }
    }
    public void resetStats(){
        flashcards.clear();
        printLogMessage("Card statistics have been reset.");

    }

}
