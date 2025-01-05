package flashcards;
//fase 3 solo e .main
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String term="";
        String definition="";
        String answer="";
        int cardN=0;
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        System.out.println("Input the number of cards:");
        cardN= scan.nextInt();
        scan.nextLine();
        //System.out.println("_______");
        for (int i =0; i<cardN; i++){

            System.out.println("Card #"+(i+1)+":");

            term=scan.nextLine();

            System.out.println("The definition for card #"+(i+1)+":");
            definition=scan.nextLine();

            //fullfilling
            map.put(term,definition);
        }

       for(Map.Entry<String,String> entry : map.entrySet()){
           String aux ="";
           String termMap=entry.getKey();
           String defMap= entry.getValue();
           System.out.println("Print the definition of \""+termMap+"\":");
           aux=scan.nextLine();

           if(aux.equals(defMap))
           {
               System.out.println("Correct!");
           }else{
               System.out.println("Wrong. The right answer is \""+defMap+"\".");
           }
       }

    }
}
