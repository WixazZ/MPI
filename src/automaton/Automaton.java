package automaton;

import java.util.*;

public class Automaton {

    //Nombre de mot dans l'alphabet
    private final int numberAlphabet;

    //nombre d'état
    private final int numberState;

    //état initiaux
    private final int[] initState;

    //état finaux
    private final int[] finishState;

    //number of transition
    private final int numberTransition;

    //tableau of transition
    private final Etat[] etats;

    /**Contructeur**/
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
            etats[Integer.parseInt(start.toString())].addTransition(trans, true);
            etats[Integer.parseInt(arrive.toString())].addTransition(trans, false);
            i++;
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
}