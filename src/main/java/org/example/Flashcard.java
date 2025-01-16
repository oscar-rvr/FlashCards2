package org.example;

public class Flashcard {

    private String term;
    private String definition;
    private int mistakes;


    public Flashcard( String term, String definition,int mistakes){

        this.term=term;
        this.definition=definition;
        this.mistakes=mistakes;

    }

    public String getTerm(){
        return term;
    }

    public String getDefinition(){
        return definition;
    }

    public int getMistakes() {
        return mistakes;
    }


    public void setTerm(String term) {
        this.term = term;
    }

    public void setDefinition(String definition){
        this.definition=definition;
    }

    public void setMiskates(int mistakes) {
        this.mistakes=mistakes;

    }

    public void sumMistake() {
        mistakes++;

    }

}
