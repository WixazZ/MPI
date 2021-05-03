package determination;

import automaton.Automaton;

import java.util.ArrayList;
import java.util.List;

public class Determinise {

    private boolean isDeterministe;
    private final Automaton AFD;

    public Determinise(Automaton autom){
        this.AFD = autom;
    }

    public void isDeterministe(){
        if(AFD.getInitState().length != 1){
            System.out.println("L'auto");
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
                        asVerify = false;
                    }
                    OutEtat.add(NameOutEtat);
                    j++;
                }
                i++;
            }
            isDeterministe = asVerify;
        }
    }

    public void determiniser(){
        if(!isDeterministe){


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
