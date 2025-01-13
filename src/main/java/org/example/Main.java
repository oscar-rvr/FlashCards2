package org.example;
//stage07
import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);



       String importFileArg=null;
       String exportFileArg="";
       FlashcardManager manager = new FlashcardManager();
if(args!=null){
    for(int i=0; i< args.length;i++){
        if( args[i].equals("-import") && i + 1 < args.length){
            importFileArg=args[i+1];
            manager.saveLogMessage("-import"+importFileArg);

        } else if (args[i].equals("-export") && i + 1 < args.length) {
            exportFileArg=args[i+1];
            manager.saveLogMessage("-export"+importFileArg);
        }
    }

}
        if(importFileArg!=null){
            manager.importF(importFileArg);
        }

        String action="";
        do {
             manager.printLogMessage("\nInput the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            action = scan.nextLine();
            manager.saveLogMessage(action);
            if (action.equals("add")) {
                  manager.printLogMessage("The card:");
                String term = scan.nextLine();
                manager.saveLogMessage(term);
                if(manager.existsTerm (term)){
                     manager.printLogMessage("The card \""+term+"\" already exists.");
                }else{
                     manager.printLogMessage("The definition of the card:");
                    String definition = scan.nextLine();
                    manager.saveLogMessage(definition);

                    if(manager.existDefinition(definition)){
                         manager.printLogMessage("The definition \""+definition+"\" already exists.");
                    }else{
                        int mistakes=0;
                        manager.addCard(term, definition,mistakes);

                         manager.printLogMessage("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
                    }
                }



            } else if (action.equals("remove")) {
                 manager.printLogMessage("Which card?");
                String term = scan.nextLine();
                manager.saveLogMessage(term);
                manager.removeCard(term);

            } else if (action.equals("ask")) {
                 manager.printLogMessage("How many times to ask?");
                int times = scan.nextInt();
                scan.nextLine();
                manager.saveLogMessage(times+"");
                manager.askCard(times);

            } else if(action.equals("export")){
                 manager.printLogMessage("File name:");
                String filename = scan.nextLine();
                manager.export(filename);
                manager.saveLogMessage(filename);



            }else if(action.equals("import")){

                 manager.printLogMessage("File name:");
                String importFile = scan.nextLine();
                manager.importF(importFile);
                manager.saveLogMessage(importFile);
            } else if(action.equals("hardest card")){

                manager.hardestCard();

            }else if(action.equals("log")){
                manager.printLogMessage("File name:");
               String filename = scan.nextLine();

               manager.saveLogMessage(filename);


               manager.writeLog(filename);
            }
            else if(action.equals("reset stats")){
                manager.resetStats();
            }else if(action.equals("exit") && exportFileArg != ""){

                manager.export(exportFileArg);

                //break;
            }


        } while (!action.equals("exit"));

         manager.printLogMessage("Bye bye!");
    }
}