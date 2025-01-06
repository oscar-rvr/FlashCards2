package flashcards;
//fase 05
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
          /* if(existTerm(term,map)){
               System.out.println("The term \""+ term +"\" already exists. Try again:");
               continue;
           }*/
            while(existTerm(term,map)){
                System.out.println("The term \""+ term +"\" already exists. Try again:");
                //System.out.println("Card #"+(i+1)+":");
                term=scan.nextLine();
            }
            System.out.println("The definition for card #"+(i+1)+":");
            definition=scan.nextLine();

            while(existDef(definition,map)){
                System.out.println("The definition \""+definition+"\" already exists. Try again:");
                //System.out.println("Card #"+(i+1)+":");
                definition=scan.nextLine();
            }



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
            }else if(map.containsValue(defMap)){
                System.out.println("Wrong. The right answer is \""+   defMap  +"\", but your definition is correct for \""+getKey(aux,map)+"\"");
            }else{
                System.out.println("Wrong. The right answer is \""+defMap+"\".");

            }
        }

    }

    public static boolean existTerm(String term, LinkedHashMap<String,String> map){
        for (Map.Entry<String,String> entry : map.entrySet()){
            String termMap = entry.getKey();
            String defMap = entry.getValue();
            if(term.equals(termMap) ){
                return true;
            }

        }
        return false;
    }

    public static boolean existDef(String def, LinkedHashMap<String,String> map){
        for (Map.Entry<String,String> entry : map.entrySet()){
            String termMap = entry.getKey();
            String defMap = entry.getValue();

            if(def.equals(defMap) ){
                return true;

            }
        }
        return false;
    }

    public static String getKey(String value, LinkedHashMap<String,String> map){
        for (Map.Entry<String, String> entry : map.entrySet()){
            if(entry.getValue().equals(value)){
                return entry.getKey();
            }


        }
        return null;
    }
}