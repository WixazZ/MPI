package automaton;

import java.util.*;

public class Automaton {

    //Nombre de mot dans l'alphabet
    private int numberAlphabet;

    //nombre d'état
    private int numberState;

    //état initiaux
    private int[] initState;

    //état finaux
    private int[] finishState;

    //number of transition
    private int numberTransition;

    //tableau of transition
    private Etat[] etats;

    /**Contructeur**/
    public Automaton(){
        this.etats = new Etat[0];
        this.initState = new int[0];
        this.finishState = new int[0];
    }

    public Automaton(int numberAlphabet, int numberState, int[] initState, int[] finishState, int numberTransition, String[] transition){
        this.numberAlphabet = numberAlphabet;
        this.numberState = numberState;
        this.initState = initState;
        this.finishState = finishState;
        this.numberTransition = numberTransition;
        this.etats = new Etat[numberState];

        for (int element: initState) {
            etats[element] = new Etat(element, true, false, numberTransition);
        }

        for (int element: finishState) {
            if(etats[element] == null) {
                etats[element] = new Etat(element, false, true, numberTransition);
            } else {
                etats[element].setFinish(true);
            }
        }

        for (int i = 0; i < numberState;  i++){
            if (etats[i] == null){
                etats[i] = new Etat(i, false, false, numberTransition);
            }
        }

        int i = 0;
        while (i < numberTransition){
            String str = transition[i];
            int lengthStr = str.length();
            int j = 0;
            StringBuilder start = new StringBuilder();
            char word;
            StringBuilder arrive = new StringBuilder();
            while((str.charAt(j)) >= '0' && str.charAt(j) <= '9'){
                start.append(str.charAt(j));
                j++;
            }
            word = str.charAt(j);
            j++;
            for (;j < lengthStr; j++){
                arrive.append(str.charAt(j));
            }
            Transition trans = new Transition(etats[Integer.parseInt(start.toString())], word, etats[Integer.parseInt(arrive.toString())]);
            etats[Integer.parseInt(start.toString())].addTransition(trans, false);
            etats[Integer.parseInt(arrive.toString())].addTransition(trans, true);
            i++;
        }
    }

    public Automaton(int numberAlphabet, int numberState, int[] initState, int[] finishState, int numberTransition, Etat[] etats){
        this.numberAlphabet = numberAlphabet;
        this.numberState = numberState;
        this.initState = initState;
        this.finishState = finishState;
        this.numberTransition = numberTransition;
        this.etats = new Etat[numberState];

        for (int i = 0; i < numberState; i++){
            this.etats[i] = etats[i].copie();
        }
    }

    /**ACCESSEURS**/
    public int getNumberAlphabet() {
        return numberAlphabet;
    }

    public int getNumberState() {
        return numberState;
    }

    public int[] getInitState() {
        return initState;
    }

    public int[] getFinishState() {
        return finishState;
    }

    public int getNumberTransition() {
        return numberTransition;
    }

    public Etat[] getEtats() {
        return etats;
    }

    public void setEtats(Etat[] etats) {
        this.etats = etats;
    }

    public void setNumberTransition(int numberTransition) {
        this.numberTransition = numberTransition;
    }

    public void setNumberState(int numberState) {
        this.numberState = numberState;
    }

    public void setInitState(int[] initState) {
        this.initState = initState;
    }

    public void setFinishState(int[] finishState) {
        this.finishState = finishState;
    }

    public void setNumberAlphabet(int numberAlphabet) {
        this.numberAlphabet = numberAlphabet;
    }

    /**Methode**/
    public void printInitState(){
        for (int element: initState){
            System.out.println("l'Etat est initial: " + element);
        }
    }

    public void printFinsihState(){
        for (int element: finishState){
            System.out.println("l'Etat est Sortant: " + element);
        }
    }

    public void printAutomate(){
        System.out.println("Nombre alphabet: " + numberAlphabet);
        System.out.println("Nombre d'états: " + numberState);
        printInitState();
        printFinsihState();
        System.out.println("Nombre de transition: " + numberTransition);
        for (Etat element: etats){
            element.printEtat();
        }
    }

    public Automaton copie(){
        return new Automaton(this.numberAlphabet, this.numberState, this.initState, this.finishState, this.numberTransition, this.etats);
    }

    public void complementaire(){
        completer();
        for (int i = 0; i < numberState; i++) {
            if (etats[i].getFinish())
                etats[i].setFinish(false);

            if (!etats[i].getFinish())
                etats[i].setFinish(true);
        }
    }

    public void completer() {
        if (!isComplet()) {
            char[] alphabet = new char[numberAlphabet];

            for (int i = 0; i < numberAlphabet; i++) {
                alphabet[i] = (char) ('a' + i);
            }

            numberState++;
            etats = Arrays.copyOf(etats, numberState);
            etats[numberState - 1] = new Etat(numberState - 1, false, false, numberAlphabet * (numberState - 1));

            for (int i = 0; i < numberState; i++) {
                for (int j = 0; j < numberAlphabet; j++) {
                    boolean complet = false;
                    int k = 0;
                    while (!complet && k < etats[i].getIndexOut()) {
                        if (etats[i].getOut()[k].getWord() == alphabet[j]) {
                            complet = true;
                        } else {
                            k++;
                        }
                    }

                    if (!complet) {
                        Transition trans = new Transition(etats[i], alphabet[j], etats[numberState - 1]);
                        etats[i].addTransition(trans, false);
                        etats[numberState - 1].addTransition(trans, true);
                        numberTransition++;
                    }
                }
            }
        }
    }

    public boolean isComplet(){
        if (numberAlphabet * numberState == numberTransition) {
            boolean complet = true;
            int i = 0;
            while (i < numberState && complet){
                complet = etats[i].getIndexOut() == numberAlphabet;
                i++;
            }
            if(complet){
                System.out.println("L'automate est complet");
            } else{
                System.out.println("L'automate n'est pas complet, il existe plusieurs possibilité pour la meme lettre");
            }
            return complet;
        }
        else {
            System.out.println("Le nombre de transition n'est pas bon");
            return false;
        }
    }
    public boolean is_synchrone(Automaton auto){
        for (int i = 0; i < auto.getEtats().length; i++) {
            for (int j = 0; j < auto.getEtats()[i].getIn().length; j++) {
                if (auto.getEtats()[i].getIn()[j]!= null && auto.getEtats()[i].getIn()[j].getWord() == '*'){
                    return false;
                }
            }
        }
        return true;
    }
}