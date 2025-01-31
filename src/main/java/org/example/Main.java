package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FlashcardManager manager = new FlashcardManager();
        FlashcardController controller = new FlashcardController(manager, scanner);

        String importFile = null, exportFile = null;
        for (int i = 0; i < args.length; i++) {
            if ("-import".equals(args[i]) && i + 1 < args.length) {
                importFile = args[i + 1];
            } else if ("-export".equals(args[i]) && i + 1 < args.length) {
                exportFile = args[i + 1];
            }
        }

        if (importFile != null) manager.importF(importFile);

        // Main application loop
        String command;
        do {
            manager.printLogMessage("\nInput the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            command = scanner.nextLine();
            manager.saveLogMessage(command);
            controller.processCommand(command);
        } while (!"exit".equals(command));

        // Export if requested
        if (exportFile != null) {
            manager.export(exportFile);
        }

    }
}