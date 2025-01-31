package org.example;
import java.util.Scanner;

public class FlashcardController {
    private final FlashcardManager manager;
    private final Scanner scanner;


    public FlashcardController(FlashcardManager manager, Scanner scanner) {
        this.manager = manager;
        this.scanner = scanner;
    }

    public void processCommand(String command){
        switch (command){
            case "add" -> handleAdd();
            case "remove" -> handleRemove();
            case "import" -> handleImport();
            case "export" -> handleExport();
            case "ask" -> handleAsk();
            case "hardest card" -> manager.hardestCard();
            case "log" -> handleLog();
            case "reset stats" -> manager.resetStats();
            case "exit" -> manager.printLogMessage("Bye Bye");
            default -> manager.printLogMessage("Unknown command");
        }
    }
    private void handleAdd() {
        manager.printLogMessage("The card:");
        String term = scanner.nextLine();
        manager.saveLogMessage(term);

        if (manager.existsTerm(term)) {
            manager.printLogMessage("The card \"" + term + "\" already exists.");
            return;
        }

        manager.printLogMessage("The definition of the card:");
        String definition = scanner.nextLine();
        manager.saveLogMessage(definition);

        if (manager.existDefinition(definition)) {
            manager.printLogMessage("The definition \"" + definition + "\" already exists.");
        } else {
            manager.addCard(term, definition, 0);
            manager.printLogMessage("The pair (\"" + term + "\":\"" + definition + "\") has been added.");
        }
    }

    private void handleRemove() {
        manager.printLogMessage("Which card?");
        String term = scanner.nextLine();
        manager.saveLogMessage(term);
        manager.removeCard(term);
    }

    private void handleImport() {
        manager.printLogMessage("File name:");
        String filename = scanner.nextLine();
        manager.saveLogMessage(filename);
        manager.importF(filename);
    }

    private void handleExport() {
        manager.printLogMessage("File name:");
        String filename = scanner.nextLine();
        manager.saveLogMessage(filename);
        manager.export(filename);
    }

    private void handleAsk() {
        manager.printLogMessage("How many times to ask?");
        int times = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        manager.saveLogMessage(String.valueOf(times));
        manager.askCard(times);
    }

    private void handleLog() {
        manager.printLogMessage("File name:");
        String filename = scanner.nextLine();
        manager.saveLogMessage(filename);
        manager.writeLog(filename);
    }


}
