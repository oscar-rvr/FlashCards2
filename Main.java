package flashcards;
//fase 05
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        FlashcardManager manager = new FlashcardManager();
        String action="";
        do {
            //System.out.println("\nInput the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            manager.printLogMessage("\nInput the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            action = scan.nextLine();
            manager.saveLogMessage(action);
            if (action.equals("add")) {
                // Agregar una tarjeta
                //System.out.println("The card:");
                manager.printLogMessage("The card:");
                String term = scan.nextLine();
                manager.saveLogMessage(term);
                if(manager.existsTerm (term)){
                   // System.out.println("The card \""+term+"\" already exists.");
                    manager.printLogMessage("The card \""+term+"\" already exists.");
                }else{
                   // System.out.println("The definition of the card:");
                    manager.printLogMessage("The definition of the card:");
                    String definition = scan.nextLine();
                    manager.saveLogMessage(definition);

                    if(manager.existDefinition(definition)){
                        //System.out.println("The definition \""+definition+"\" already exists.");
                        manager.printLogMessage("The definition \""+definition+"\" already exists.");
                    }else{
                        manager.addCard(term, definition);

                       // System.out.println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
                        manager.printLogMessage("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
                    }
                }



            } else if (action.equals("remove")) {
                // Eliminar una tarjeta
                //System.out.println("Which card?");
                manager.printLogMessage("Which card?");
                String term = scan.nextLine();
                manager.saveLogMessage(term);
                manager.removeCard(term);

            } else if (action.equals("ask")) {
                // Preguntar por una tarjeta
                //System.out.println("How many times to ask?");
                manager.printLogMessage("How many times to ask?");
                int times = scan.nextInt();
                manager.saveLogMessage(times+"");
                manager.askCard(times);

            } else if(action.equals("export")){
                //System.out.println("File name:");
                manager.printLogMessage("File name:");
                String filename = scan.nextLine();
                manager.export(filename);
                manager.saveLogMessage(filename);



            }else if(action.equals("import")){

                //System.out.println("File name:");
                manager.printLogMessage("File name:");
                String importFile = scan.nextLine();
                manager.importF(importFile);
                manager.saveLogMessage(importFile);
            } else if(action.equals("hardest card")){

                manager.hardestCard();

            }else if(action.equals("log")){
               String filename = scan.nextLine();

               manager.saveLogMessage(filename);
               manager.writeLog(filename);
            }


        } while (!action.equals("exit"));

        //System.out.println("Bye bye!");
        manager.printLogMessage("Bye bye!");
    }
}