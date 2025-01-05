package flashcards;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String cardInput ="";
        String definitionInput ="";

        System.out.println("Card:");
        cardInput = scan.nextLine();
        System.out.println(cardInput);


        System.out.println("Definition:");
        definitionInput = scan.nextLine();
        System.out.println(definitionInput);

    }
}
