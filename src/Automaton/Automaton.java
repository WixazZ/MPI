package Automaton;

import java.util.Scanner;

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
    private Transition[] transition;


    public Automaton(int numberAlphabet, int numberState, int[] initState, int[] finishState, int numberTransition, String[] transition){
        this.numberAlphabet = numberAlphabet;
        this.numberState = numberState;
        this.initState = initState;
        this.finishState = finishState;
        this.numberTransition = numberTransition;
        Transition[] tab_transition = new Transition[numberTransition];
        int i = 0;
        while (i < transition.length){
            tab_transition[i] = new Transition(transition[i]);
            i++;
        }
        this.transition = tab_transition;
    }

    public Automaton(Scanner lecteur) {

        this.numberAlphabet = Integer.parseInt(lecteur.nextLine());

        this.numberState = Integer.parseInt(lecteur.nextLine());

        String State = lecteur.nextLine();
        String[] sep_State = State.split(" ");
        int[] initState = new int[sep_State.length-1];
        int i;
        for (i = 1; i<sep_State.length; i++){
            initState[i-1] = Integer.parseInt(sep_State[i]);
        }
        this.initState = initState;

        State = lecteur.nextLine();
        sep_State = State.split(" ");
        int[] finishState = new int[sep_State.length-1];
        for (i = 1; i<sep_State.length; i++){
            finishState[i-1] = Integer.parseInt(sep_State[i]);
        }
        this.finishState = finishState;

        this.numberTransition = Integer.parseInt(lecteur.nextLine());

        String[] transition = new String[numberTransition];
        i = 0;
        while(lecteur.hasNextLine()){
            transition[i] = lecteur.nextLine();
        }

        Transition[] tab_transition = new Transition[numberTransition];
        i = 0;
        while (i < transition.length){
            tab_transition[i] = new Transition(transition[i]);
            i++;
        }
        this.transition = tab_transition;
    }
}
