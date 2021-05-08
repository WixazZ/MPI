package automaton;

import java.util.Arrays;

public class Etat {
    private int name;
    private Transition[] in;
    private int indexIn;
    private Transition[] out;
    private int indexOut;
    private boolean init;
    private boolean finish;

    /**Contructeur**/
    public Etat(int name, boolean init, boolean finish, int numberTransition){
        this.name = name;
        this.init = init;
        this.finish = finish;
        indexIn = 0;
        in = new Transition[numberTransition];
        indexOut = 0;
        out = new Transition[numberTransition];
    }

    public Etat(int name, boolean init, boolean finish, int indexIn, int indexOut){
        this.name = name;
        this.init = init;
        this.finish = finish;
        this.indexIn = indexIn;
        in = new Transition[indexIn];
        this.indexOut = indexOut;
        out = new Transition[indexOut];
    }

    /**ACCESSEURS**/
    public int getName() {
        return name;
    }

    public Transition[] getIn() {
        return in;
    }

    public Transition[] getOut() {
        return out;
    }

    public boolean getInit() {
        return init;
    }

    public boolean getFinish() {
        return finish;
    }

    public int getIndexIn() {
        return indexIn;
    }

    public int getIndexOut() {
        return indexOut;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setIn(Transition[] input) {
        in = input;
    }

    public void setOut(Transition[] output) {
        out = output;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public void setIndexIn(int indexIn) {
        this.indexIn = indexIn;
    }

    public void setIndexOut(int indexOut) {
        this.indexOut = indexOut;
    }

    /**Methode**/
    public void printEtat(){
        System.out.println("*********************************************");
        System.out.println("Nom de l'état: " + name);
        if(init){
            System.out.println("Etat initial");
        }
        if(finish){
            System.out.println("Etat Terminal");
        }
        printTransition();
    }

    public void printTransition(){
        System.out.println("Transition entrante:");
        for (int i = 0; i < indexIn; i++){
            System.out.println(in[i].getStart().name + " " + in[i].getWord() + " " + in[i].getArrive().name);
        }
        System.out.println("Transition sortante:");
        for (int i = 0; i < indexOut; i++){
            System.out.println(out[i].getStart().name + " " + out[i].getWord() + " " + out[i].getArrive().name);
        }
    }

    public void addTransition(Transition transition, boolean inBool){
        if(inBool){
            in = Arrays.copyOf(in, indexIn + 1);
            in[indexIn] = transition;
            indexIn++;
        } else{
            out = Arrays.copyOf(out, indexOut + 1);
            out[indexOut] = transition;
            indexOut++;
        }
    }

    public Etat copie(){

        Etat etat = new Etat(this.name, this.init, this.finish, this.indexIn, this.indexOut);

        for(int i = 0; i < indexIn; i++){
            etat.in[i] = this.in[i].copie();
        }

        for(int i = 0; i < indexOut; i++){
            etat.out[i] = this.out[i].copie();
        }

        return etat;
    }

    /**Parcours Epsilon**/
    public Etat[] parcoursEpsilon(char word){
        Etat[] etats = new Etat[0];
        int length = 0;
        for(int i = 0; i < out.length; i++){
            if (out[i].getWord() == word){
                etats = Arrays.copyOf(etats, length + 1);
                etats[length] = out[i].getArrive();
                length++;
            } else if(out[i].getWord() == '*'){
                etats = mergeEtatTab(etats, parcoursEpsilon(word));
            }
        }
        return etats;
    }

    public static Etat[] mergeEtatTab(Etat[] first, Etat[] second){
        int firstLength = first.length;
        int secondLength = second.length;
        first = Arrays.copyOf(first, firstLength + secondLength);
        for(int i = 0; i < secondLength; i++){
            first[i + firstLength] = second[i];
        }
        return first;
    }

    public boolean parcoursIsFinish(Etat etat){
        boolean isFinish = finish;
        int i = 0;
        while (i < indexOut && !isFinish){
            if(out[i].getWord() == '*' && out[i].getArrive() != etat){
                isFinish = out[i].getArrive().parcoursIsFinish(etat);
            }
            i++;
        }
        return isFinish;
    }

    public boolean parcoursIsInit(Etat etat){
        boolean isInit = init;
        int i = 0;
        while(i < indexIn && !isInit){
            if(in[i].getWord() == '*' && in[i].getStart() != etat){
                isInit = in[i].getStart().parcoursIsInit(etat);
            }
            i++;
        }
        return isInit;
    }
}
