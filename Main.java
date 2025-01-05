package flashcards;
//fase 3 solo e .main
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String term="";
        String definition="";
        String answer="";

        term = scan.nextLine();
        definition = scan.nextLine();
        answer = scan.nextLine();
        scan.close();
        if(definition.equals(answer)){
            System.out.println("Your answer is right!");
        }else{
            System.out.println("Your answer is wrong...");
        }




    }
}
