package standard;

import automaton.*;
import java.util.*;

public class Standard {

    private boolean isStandard = true;
    private final Automaton AFD;

    public Standard(Automaton autom){
        this.AFD = autom;
    }

    public void isStandard(){
        if (AFD.getInitState().length == 1){
            int[] init_list = AFD.getInitState();
            int init = init_list[0];
            Etat[] etats = AFD.getEtats();
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

    public void standardiser() {

        if (AFD.getInitState().length != 1) {

            Transition[] trans_in = AFD.getEtats()[AFD.getInitState()[0]].getIn();

            Transition[] trans_out = AFD.getEtats()[AFD.getInitState()[0]].getOut();

            int modif_numberState = AFD.getNumberState();
            for (int i = 1; i < AFD.getInitState().length; i++) {

                int k = 0;
                while(trans_in[k] !=null){
                    k++;
                }
                for (int j = 0; j < AFD.getEtats()[AFD.getInitState()[i]].getIn().length; j++) {
                    if(k < AFD.getNumberTransition()){
                        trans_in[k] = AFD.getEtats()[AFD.getInitState()[i]].getIn()[j];
                        k++;
                    }
                }

                k = 0;
                while(trans_out[k] !=null){
                    k++;
                }
                for (int j = 0; j < AFD.getEtats()[AFD.getInitState()[i]].getOut().length; j++) {
                    if(k < AFD.getNumberTransition()) {
                        trans_out[k] = AFD.getEtats()[AFD.getInitState()[i]].getOut()[j];
                        k++;
                    }
                }

                AFD.getEtats()[AFD.getInitState()[0]].setIndexIn(AFD.getEtats()[AFD.getInitState()[0]].getIndexIn() + AFD.getEtats()[AFD.getInitState()[i]].getIndexIn());
                AFD.getEtats()[AFD.getInitState()[0]].setIndexOut(AFD.getEtats()[AFD.getInitState()[0]].getIndexOut() + AFD.getEtats()[AFD.getInitState()[i]].getIndexOut());
                //AFD.getEtats()[AFD.getInitState()[i]] = null;
                if(i == 1){
                    AFD.getEtats()[AFD.getInitState()[0]].setName(AFD.getNumberState());
                }
                modif_numberState--;


            }

            Etat[] reetat = new Etat[modif_numberState];
            int ite = 0;
            int k = 0;
            while (ite < AFD.getNumberState()){
                if(AFD.getInitState()[0] == ite || !AFD.getEtats()[ite].getInit()){
                    reetat[k] = AFD.getEtats()[ite];
                    k++;
                }

                ite++;

            }

            AFD.setNumberState(modif_numberState);
            AFD.setEtats(reetat);

            int[] init = new int[1];
            init[0] = AFD.getInitState()[0];
            AFD.setInitState(init);



            System.out.println("\n");
            //AFD.setEtats(etats);
            //AFD.printAutomate();
        } else {

        }
        /*
        if(AFD.getEtats()[AFD.getInitState()[0]].getIn()[0] != null){
            Etat[] etats = new Etat[AFD.getNumberState() + 1];
            for (int i = 1; i < AFD.getNumberState() + 1; i++) {
                etats[i] = AFD.getEtats()[i - 1];
                etats[i].setName(i);
            }
            etats[0] = etats[1];
            AFD.setEtats(etats);
            AFD.printAutomate();
        }*/

        /*
        boolean ret_init = false;
        for (int i = 0; i < AFD.getNumberState(); i++) {
            for (int j = 0; j < AFD.getEtats()[i].getIn().length; j++) {
                if (AFD.getEtats()[i].getIn()[j] != null && AFD.getEtats()[i].getIn()[j].getArrive().getName() == 0){
                    ret_init = true;
                }
            }
            for (int j = 0; j < AFD.getEtats()[i].getOut().length; j++) {
                if (AFD.getEtats()[i].getOut()[j] != null && AFD.getEtats()[i].getOut()[j].getArrive().getName() == 0){
                    ret_init = true;
                }
            }
        }*/


    }

    public Automaton getAFD() {
        return AFD;
    }

    public boolean getIsStandard() {
        return isStandard;
    }
}
