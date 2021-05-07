package determination;

import automaton.Automaton;
import automaton.Etat;
import automaton.Transition;
import standard.Standard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Determinise {

    private boolean isDeterministe;
    private final Automaton AFD;

    public Determinise(Automaton autom){
        this.AFD = autom;
    }

    public void isDeterministe(){
        if(AFD.getInitState().length != 1){
            System.out.println("L'automate n'est pas deterministe car il contient plusieurs états initials");
            isDeterministe = false;
        } else {
            boolean asVerify = true;
            int i = 0;
            while(asVerify && i< AFD.getEtats().length ){
                List<Character> OutEtat = new ArrayList<>();
                int j = 0;
                Character NameOutEtat;
                while(AFD.getEtats()[i].getOut()[j] != null && j < AFD.getEtats()[i].getOut().length && asVerify){

                    NameOutEtat = AFD.getEtats()[i].getOut()[j].getWord();

                    if(OutEtat.contains(NameOutEtat)){
                        System.out.println("L'automate n'est pas deterministe car l'état "+ AFD.getEtats()[i]+ " contient plusieurs états identiaque");
                        asVerify = false;
                    }
                    OutEtat.add(NameOutEtat);
                    j++;
                }
                i++;
            }
            isDeterministe = asVerify;
            if(asVerify){
                System.out.println("L'automate est bien deterministe");
            }
        }
    }

    public void determiniser(){
        if(!isDeterministe){
            Automaton new_AFD = new Automaton();
            sync_init_state();

        } else {
            System.out.println("L'automate est déjà deterministe");
        }

    }
    public void sync_init_state(){
        if (AFD.getInitState().length != 1){
            Etat init_state = AFD.getEtats()[AFD.getInitState()[0]]; // récupère l'état initial
            for (Etat i : AFD.getEtats()) { // parcours des états
                if(i.getName() != init_state.getName()){ //si c'est pas l'état initial
                    if(i.getInit()){ // si l'état est un init
                        List<Transition> supp_double = new ArrayList<>();
                        supp_double = Arrays.asList(init_state.getOut());
                        Transition[] new_out = new Transition[i.getOut().length];
                        for (int j = 0; j < i.getOut().length; j++) {
                            if(i.getOut()[j] != null){
                                new_out[j] = i.getOut()[j].copie();
                                if (new_out[j] != null) {
                                    new_out[j].setStart(init_state);
                                    boolean equals = false;
                                    int k = 0;
                                    while (k < supp_double.size() && !equals) {
                                        if (supp_double.get(k).getStart().getName() == new_out[j].getStart().getName() &&
                                                supp_double.get(k).getWord() == new_out[j].getWord() &&
                                                supp_double.get(k).getArrive().getName() == new_out[j].getArrive().getName() ){
                                            equals = true;
                                        }
                                        k++;
                                    }
                                    if (!equals ){
                                        supp_double.add(new_out[j]);
                                    }
                                }
                            }
                        }
                        int taille = 0;
                        while(supp_double.get(taille) != null){
                            taille++;
                        }
                        Transition[] insert_out_trans = new Transition[taille];
                        for (int j = 0; j < taille; j++) {
                            insert_out_trans[j] = supp_double.get(j);
                        }
                        init_state.setIndexOut(taille);
                        init_state.setOut(insert_out_trans);
                        i.setInit(false);
                    }
                    if (i.getFinish()){
                        init_state.setFinish(true);
                    }
                }
            }
            int[] init_states = new int [1];
            init_states[0] = init_state.getName();
            AFD.setInitState(init_states);
        }
    }
    public Automaton getAFD() {
        return AFD;
    }

    public boolean getisDeterministe() {
        return isDeterministe;
    }

    public void setDeterministe(boolean deterministe) {
        isDeterministe = deterministe;
    }
}
