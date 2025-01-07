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
            System.out.println("\nInput the action (add, remove, import, export, ask, exit):");
            action = scan.nextLine();
            if (action.equals("add")) {
                // Agregar una tarjeta
                System.out.println("The card:");
                String term = scan.nextLine();
                if(manager.existsTerm (term)){
                    System.out.println("The card \""+term+"\" already exists.");

                }else{
                    System.out.println("The definition of the card:");
                    String definition = scan.nextLine();

                    if(manager.existDefinition(definition)){
                        System.out.println("The definition \""+definition+"\" already exists.");
                    }else{
                        manager.addCard(term, definition);
                        System.out.println("The pair (\"" + term + "\":\"" + definition + "\") has been added.");

                    }
                }



            } else if (action.equals("remove")) {
                // Eliminar una tarjeta
                System.out.println("Which card?");
                String term = scan.nextLine();
                manager.removeCard(term);

            } else if (action.equals("ask")) {
                // Preguntar por una tarjeta
                System.out.println("How many times to ask?");
                int times = scan.nextInt();
                manager.askCard(times);

            } else if(action.equals("export")){
                System.out.println("File name:");
                String filename = scan.nextLine();
                manager.export(filename);


            }else if(action.equals("import")){

                System.out.println("File name:");
                String importFile = scan.nextLine();
                manager.importF(importFile);
            }



        } while (!action.equals("exit"));

        System.out.println("Bye bye!");

    }
}