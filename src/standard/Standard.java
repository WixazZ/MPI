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
        //SI PLUSIEURS ETATS INITIALS
        int numberStateIfReturn = AFD.getEtats()[AFD.getInitState()[0]].getName();
        if (AFD.getInitState().length != 1) {

            //SUPPRESSION ET RASSEMBLEMENT DES INITS

            Transition[] trans_in = AFD.getEtats()[AFD.getInitState()[0]].getIn();

            Transition[] trans_out = AFD.getEtats()[AFD.getInitState()[0]].getOut();

            for (Etat elem : AFD.getEtats()) {
                if(elem.getName()> numberStateIfReturn){
                    numberStateIfReturn = elem.getName();
                }
            }
            numberStateIfReturn++;

            int modif_numberState = AFD.getNumberState();

            boolean initIsFinish = false;

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

                if(!initIsFinish && AFD.getEtats()[AFD.getInitState()[i]].getFinish()){
                    initIsFinish = true;
                }
                modif_numberState--;
            }
            if(initIsFinish){
                AFD.getEtats()[AFD.getInitState()[0]].setFinish(true);
            }
            Etat[] reetat = new Etat[modif_numberState];
            int ite = 0;
            int k = 0;
            List<Integer> etatSupr = new ArrayList<>();
            while (ite < AFD.getNumberState()){
                if(AFD.getInitState()[0] == ite || !AFD.getEtats()[ite].getInit()){
                    reetat[k] = AFD.getEtats()[ite];
                    k++;
                }else{
                    etatSupr.add(ite);
                }
                ite++;

            }
            AFD.setNumberState(modif_numberState);
            AFD.setEtats(reetat);

            int[] init = new int[1];
            init[0] = AFD.getInitState()[0];
            AFD.setInitState(init);

            for (int i = 0; i < AFD.getEtats().length; i++) {

                //parcours in de chaque état
                for (int j = 0; j < AFD.getEtats()[i].getIn().length; j++) {
                    //Si in contient un état supprimer le supprime et le remplace par l'unique init
                    if(AFD.getEtats()[i].getIn()[j] != null && etatSupr.contains(AFD.getEtats()[i].getIn()[j].getStart().getName())){
                        AFD.getEtats()[i].getIn()[j].setStart(AFD.getEtats()[AFD.getInitState()[0]]);
                    }
                    if(AFD.getEtats()[i].getIn()[j] != null && etatSupr.contains(AFD.getEtats()[i].getIn()[j].getArrive().getName())){
                        AFD.getEtats()[i].getIn()[j].setArrive(AFD.getEtats()[AFD.getInitState()[0]]);
                    }
                }

                //parcours out de chaque état
                for (int j = 0; j < AFD.getEtats()[i].getOut().length; j++) {
                    //Si in contient un état supprimer le supprime et le remplace par l'unique init
                    if(AFD.getEtats()[i].getOut()[j] != null && etatSupr.contains(AFD.getEtats()[i].getOut()[j].getStart().getName())){
                        AFD.getEtats()[i].getOut()[j].setStart(AFD.getEtats()[AFD.getInitState()[0]]);
                    }
                    if(AFD.getEtats()[i].getOut()[j] != null && etatSupr.contains(AFD.getEtats()[i].getOut()[j].getArrive().getName())){
                        AFD.getEtats()[i].getOut()[j].setArrive(AFD.getEtats()[AFD.getInitState()[0]]);
                    }
                }

                //supprimer les transitions en double
                Transition[] in = new Transition[AFD.getNumberTransition()];
                int indexin = 0;
                AFD.getEtats()[i].setIndexIn(0);

                Transition[] out = new Transition[AFD.getNumberTransition()];
                int indexout = 0;
                AFD.getEtats()[i].setIndexIn(0);
                for (int j = 0; j < AFD.getEtats()[i].getIn().length; j++) {
                    if (AFD.getEtats()[i].getIn()[j] != null && contient(in, AFD.getEtats()[i].getIn()[j])){
                        in[indexin] = AFD.getEtats()[i].getIn()[j];
                        indexin++;
                        AFD.getEtats()[i].setIndexIn(indexin);
                    }
                }

                for (int j = 0; j < AFD.getEtats()[i].getOut().length; j++) {
                    if (AFD.getEtats()[i].getOut()[j] != null && contient(out, AFD.getEtats()[i].getOut()[j])){
                        out[indexout] = AFD.getEtats()[i].getOut()[j];
                        indexout++;
                        AFD.getEtats()[i].setIndexOut(indexout);
                    }
                }
                AFD.getEtats()[i].setIn(in);
                AFD.getEtats()[i].setOut(out);

            }
        }

        //SI TRANSITION ENTRANTE VERS L ETAT INITIAL
        if(AFD.getEtats()[AFD.getInitState()[0]].getIn() != null){
            int numberTransition = AFD.getNumberTransition()+ AFD.getEtats()[AFD.getInitState()[0]].getIndexIn()+AFD.getEtats()[AFD.getInitState()[0]].getIndexOut();

            Etat initi = AFD.getEtats()[AFD.getInitState()[0]].copie();
            AFD.setNumberTransition(numberTransition);
            initi.setName(numberStateIfReturn);
            AFD.getEtats()[AFD.getInitState()[0]].setInit(false);
            AFD.getEtats()[AFD.getInitState()[0]].setFinish(false);

            for (int i = 0; i < initi.getIndexIn(); i++) {
                initi.getIn()[i].setStart(initi);
            }
            for (int i = 0; i < initi.getIndexOut(); i++) {
                initi.getOut()[i].setStart(initi);
            }
            Etat[] newStates = new Etat[AFD.getEtats().length+1];
            for (int i = 0; i < AFD.getEtats().length+1; i++) {
                if(i == 0){
                    newStates[i] = initi;
                } else {
                    newStates[i] = AFD.getEtats()[i-1];
                }
            }
            AFD.setEtats(newStates);
            majInitFinishState();
        }

    }
    private boolean contient(Transition[] tab, Transition var){

        for (Transition transition : tab) {
            if (transition != null && transition.getStart().getName() == var.getStart().getName() && transition.getArrive().getName() == var.getArrive().getName() && transition.getWord() == var.getWord()) {
                return false;
            }
        }
        return true;
    }

    public void majInitFinishState(){
        int[] tempInit = new int[AFD.getEtats().length];
        int[] tempFinish = new int[AFD.getEtats().length];
        int numberInitState = 0;
        int numberFinishState = 0;
        for (int i = 0; i < AFD.getEtats().length; i++) {
            if(AFD.getEtats()[i].getInit()){
                tempInit[numberInitState] = AFD.getEtats()[i].getName();
                numberInitState++;
            }
            if(AFD.getEtats()[i].getFinish()){
                tempFinish[numberFinishState] = AFD.getEtats()[i].getName();
                numberFinishState++;
            }
        }
        int[] init = new int[numberInitState];
        int[] finish = new int[numberFinishState];
        System.arraycopy(tempInit, 0, init, 0, numberInitState);
        System.arraycopy(tempFinish, 0, finish, 0, numberFinishState);
        AFD.setInitState(init);
        AFD.setFinishState(finish);
    }


    public Automaton getAFD() {
        return AFD;
    }

    public boolean getIsStandard() {
        return isStandard;
    }
}
