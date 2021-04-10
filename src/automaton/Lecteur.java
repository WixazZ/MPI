package automaton;

import java.util.*;

public class Lecteur {
    public Automaton lecture(Scanner lecteur){
        int numberAlphabet = Integer.parseInt(lecteur.nextLine());

        int numberState = Integer.parseInt(lecteur.nextLine());

        String State = lecteur.nextLine();
        String[] sep_State = State.split(" ");
        int[] initState = new int[sep_State.length-1];
        int i;
        for (i = 1; i<sep_State.length; i++){
            initState[i-1] = Integer.parseInt(sep_State[i]);
        }

        State = lecteur.nextLine();
        sep_State = State.split(" ");
        int[] finishState = new int[sep_State.length-1];
        for (i = 1; i<sep_State.length; i++){
            finishState[i-1] = Integer.parseInt(sep_State[i]);
        }

        int numberTransition = Integer.parseInt(lecteur.nextLine());

        String[] transition = new String[numberTransition];
        i = 0;
        while(lecteur.hasNextLine()){
            transition[i] = lecteur.nextLine();
        }


        return new Automaton(numberAlphabet, numberState,initState, finishState, numberTransition, transition);
    }
}
