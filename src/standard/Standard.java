package standard;

import automaton.*;

public class Standard {

    private boolean isStandard = true;
    private final Automaton autom;

    public Standard(Automaton autom){
        this.autom = autom;
    }

    public void isStandard(){
        if (autom.getInitState().length == 1){
            int[] init_list = autom.getInitState();
            int init = init_list[0];
            Etat[] etats = autom.getEtats();
            int i = 0;
            Etat etat;
            Transition[] trans;
            Etat sortant;
            while(isStandard == i < etats.length){
                etat = etats[i];
                trans = etat.getOut();
                if (trans[i] != null){
                    sortant = trans[i].getArrive();
                    if(sortant.getName() == init){
                        isStandard = false;
                    }
                }

                i++;
            }

        }else{
            isStandard = false;
        }

    }

    public boolean getIsStandard() {
        return isStandard;
    }
}
