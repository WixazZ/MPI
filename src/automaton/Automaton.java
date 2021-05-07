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
        this.numberAlphabet = 0;
        this.numberState = 0;
        this.initState = new int[0];
        this.finishState = new int[0];
        this.numberTransition = 0;
        this.etats = new Etat[0];
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

    public void setNumberAlphabet(int numberAlphabet){
        this.numberAlphabet = numberAlphabet;
    }

    public void setNumberState(int numberState){
        this.numberState = numberState;
    }

    public void setInitState(int[] initState){
        this.initState = initState;
    }

    public void setFinishState(int[] finishState){
        this.finishState = finishState;
    }

    public void setNumberTransition(int numberTransition){
        this.numberTransition = numberTransition;
    }

    public void setEtats(Etat[] etats){
        this.etats = etats;
    }

    /**Methode**/
    public void printInitState(){
        for (int element: initState){
            System.out.println("Etat initial: " + element);
        }
    }

    public void printFinsihState(){
        for (int element: finishState){
            System.out.println("Etat Sortant: " + element);
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

    public void completer() {
		/*for (int i=0; i<numberTransition; i++){
			if(symtrans!=etats[i].getName())
				//on met la transition � la fin du tableau
		}*/
        char[] alphabet = new char[numberAlphabet];

        for (int i = 0; i < numberAlphabet; i++) {
            alphabet[i] = (char) ('a' + i);
        }

        numberState++;
        etats = Arrays.copyOf(etats, numberState);
        etats[numberState - 1] = new Etat(numberState - 1, false, false, numberAlphabet * (numberState - 1));

        /*for (int j = 0; j < numberTransition; j++) {
            etats = Arrays.copyOf(etats, numberState + 1);
            etats[numberState - 1] = new Etat(numberState - 1, false, false, numberAlphabet);
        }*/


        for (int i = 0; i < numberState; i++) {
            System.out.println("Je suis l'état " + i);
            for (int j = 0; j < numberAlphabet; j++) {
                System.out.println("Je suis la lettre " + alphabet[j]);
                boolean complet = false;
                int k = 0;
                while (!complet && k < etats[i].getIndexOut()) {
                    if (etats[i].getOut()[k].getWord() == alphabet[j]) {
                        complet = true;
                    } else {
                        k++;
                    }
                }
                System.out.println("J'ai " + numberState + " états");

                if (!complet) {
                    System.out.println("Je crée un transition ");
                    Transition trans = new Transition(etats[i], alphabet[j], etats[numberState - 1]);
                    etats[i].addTransition(trans, false);
                    etats[numberState - 1].addTransition(trans, true);
                    numberTransition++;
                }
            }
        }
    }

    public void complementaire(){
        /*if (isComplet(Automaton) == false) {
            return Completer(Automaton);
        }*/

        for (int i = 0; i < numberState; i++) {
            if (etats[i].getFinish()){
                etats[i].setFinish(false);
                System.out.println("ça marche");
            } else{
                etats[i].setFinish(true);
                System.out.println("ça marche");
            }
        }
    }

    public boolean isComplet(){
        if(numberAlphabet * numberState < numberTransition){
            System.out.println("ERREUR du is_complet");
            return false;
        }
        if (numberAlphabet * numberState == numberTransition) {
            System.out.println("L'automate est complet car tout les �tats ont une transition");
            return true;}
        else {
            System.out.println("L'automate n'est pas complet car il y a plus de transition que d'�tat");
            return false;}
    }


    /**Elimination epsilon */
    public void eliminationEpsilon(){
        for(int i = 0; i < numberState; i++){

            
        }
    }
}