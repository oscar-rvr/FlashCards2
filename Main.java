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
        String term="";
        String definition="";
        String answer="";
        int cardN=0;
        String removedCard="";
        String fileName="";
        int askTimes=0;
        LinkedHashMap<String,String> map = new LinkedHashMap<>();
        //System.out.println("Input the number of cards:");
        //cardN= scan.nextInt();
        //scan.nextLine();
//--------
        String action="";
        //fase 05

        do{
            System.out.println("\nInput the action (add, remove, import, export, ask, exit):");
            action=scan.nextLine();
                if(action.equals("add")){
                    System.out.println("The card:");
                    term= scan.nextLine();
                    if(existTerm(term,map)){
                        System.out.println("The card \""+ term +"\" already exists.");
                        continue;

                    }
                    System.out.println("The definition of the card:");
                    definition=scan.nextLine();
                    if(existDef(definition,map)){
                        System.out.println("The definition \""+definition+"\" already exists.");
                        continue;

                    }


                    map.put(term,definition);
                    System.out.println("The pair (\""+ term +"\":\""+definition+"\") has been added.");

                } else if(action.equals("remove")){
                    System.out.println("Which card?");
                    removedCard=scan.nextLine();
                    if(existTerm(removedCard,map)){
                        map.remove(removedCard);
                        System.out.println("The card has been removed.");
                    }else{
                        System.out.println("Can't remove \""+removedCard+"\": there is no such card.");
                    }
                } else if(action.equals("import")){

                } else if(action.equals("export")){
                    System.out.println("File name:");
                    fileName= scan.nextLine();
                    /*try{
                        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName+".txt"));
                        writer.write(String.valueOf(map));
                        System.out.println(map.size()+" cards have been saved.");
                        System.out.println(String.valueOf(map));
                        System.out.println(map);
                    } catch (Exception e) {
                        System.out.println("error");
                    }*/
                    try(BufferedWriter writer = new BufferedWriter(new FileWriter(fileName+".txt"))){
                        for(Map.Entry<String,String> entry : map.entrySet()){
                            writer.write(entry.getKey()+":"+entry.getValue()+",");
                            writer.newLine();
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(map.size()+" cards have been saved.");
                } else if(action.equals("ask")){
                    System.out.println("How many times to ask?");
                    askTimes=scan.nextInt();
                    int n = map.size();
                    int random=(int) (Math.random() * n);
                   // System.out.println("Print the definition of \"a brother of one's parent\":");
                } else {

                }




        } while (!action.equals("exit"));
        System.out.println("Bye bye!");
//--------
      /*  for (int i =0; i<cardN; i++){

            System.out.println("Card #"+(i+1)+":");
            term=scan.nextLine();

            while(existTerm(term,map)){
                System.out.println("The term \""+ term +"\" already exists. Try again:");

                term=scan.nextLine();
            }
            System.out.println("The definition for card #"+(i+1)+":");
            definition=scan.nextLine();

            while(existDef(definition,map)){
                System.out.println("The definition \""+definition+"\" already exists. Try again:");

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
        }*/

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