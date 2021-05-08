package standard;

import automaton.*;
import java.util.*;

public class Standard {

    private boolean isStandard;
    private final Automaton AF;

    public Standard(Automaton autom){
        this.AF = autom;
    }

    public void isStandard(){
        if (AF.getInitState().length == 1){
            int[] init_list = AF.getInitState();
            int init = init_list[0];
            Etat[] etats = AF.getEtats();
            int i = 0;
            Etat etat;
            Transition[] trans;
            Etat sortant;
            boolean verif = false;
            while(isStandard == i < etats.length && !verif){
                etat = etats[i];
                trans = etat.getOut();
                if (trans[i] != null){
                    sortant = trans[i].getArrive();
                    if(sortant.getName() == init){
                        System.out.println("L'automate n'est pas standard car l' etat "+etat.getName()+" contient une transition vers l'etat initial "+init);
                        verif = true;
                        isStandard = false;
                    }
                }

                i++;
            }
        }else{
            System.out.println("L'automate n'est pas standard car il contient plusieurs états initials");
            isStandard = false;
        }

    }

    public void standardiser() {
        //SI PLUSIEURS ETATS INITIALS
        int numberStateIfReturn = AF.getEtats()[AF.getInitState()[0]].getName();
        if (AF.getInitState().length != 1) {
            //SUPPRESSION ET RASSEMBLEMENT DES INITS

            Transition[] trans_in = AF.getEtats()[AF.getInitState()[0]].getIn();

            Transition[] trans_out = AF.getEtats()[AF.getInitState()[0]].getOut();

            for (Etat elem : AF.getEtats()) {
                if(elem.getName()> numberStateIfReturn){
                    numberStateIfReturn = elem.getName();
                }
            }
            numberStateIfReturn++;

            int modif_numberState = AF.getNumberState();

            boolean initIsFinish = false;

            for (int i = 1; i < AF.getInitState().length; i++) {

                int k = 0;
                while(trans_in[k] !=null){
                    k++;
                }
                for (int j = 0; j < AF.getEtats()[AF.getInitState()[i]].getIn().length; j++) {
                    if(k < AF.getNumberTransition()){
                        trans_in[k] = AF.getEtats()[AF.getInitState()[i]].getIn()[j];
                        k++;
                    }
                }

                k = 0;
                while(trans_out[k] !=null){
                    k++;
                }
                for (int j = 0; j < AF.getEtats()[AF.getInitState()[i]].getOut().length; j++) {
                    if(k < AF.getNumberTransition()) {
                        trans_out[k] = AF.getEtats()[AF.getInitState()[i]].getOut()[j];
                        k++;
                    }
                }

                AF.getEtats()[AF.getInitState()[0]].setIndexIn(AF.getEtats()[AF.getInitState()[0]].getIndexIn() + AF.getEtats()[AF.getInitState()[i]].getIndexIn());
                AF.getEtats()[AF.getInitState()[0]].setIndexOut(AF.getEtats()[AF.getInitState()[0]].getIndexOut() + AF.getEtats()[AF.getInitState()[i]].getIndexOut());

                if(!initIsFinish && AF.getEtats()[AF.getInitState()[i]].getFinish()){
                    initIsFinish = true;
                }
                modif_numberState--;
            }
            if(initIsFinish){
                AF.getEtats()[AF.getInitState()[0]].setFinish(true);
            }
            Etat[] reetat = new Etat[modif_numberState];
            int ite = 0;
            int k = 0;
            List<Integer> etatSupr = new ArrayList<>();
            while (ite < AF.getNumberState()){
                if(AF.getInitState()[0] == ite || !AF.getEtats()[ite].getInit()){
                    reetat[k] = AF.getEtats()[ite];
                    k++;
                }else{
                    etatSupr.add(ite);
                }
                ite++;

            }
            AF.setNumberState(modif_numberState);
            AF.setEtats(reetat);

            int[] init = new int[1];
            init[0] = AF.getInitState()[0];
            AF.setInitState(init);

            for (int i = 0; i < AF.getEtats().length; i++) {

                //parcours in de chaque état
                for (int j = 0; j < AF.getEtats()[i].getIn().length; j++) {
                    //Si in contient un état supprimer le supprime et le remplace par l'unique init
                    if(AF.getEtats()[i].getIn()[j] != null && etatSupr.contains(AF.getEtats()[i].getIn()[j].getStart().getName())){
                        AF.getEtats()[i].getIn()[j].setStart(AF.getEtats()[AF.getInitState()[0]]);
                    }
                    if(AF.getEtats()[i].getIn()[j] != null && etatSupr.contains(AF.getEtats()[i].getIn()[j].getArrive().getName())){
                        AF.getEtats()[i].getIn()[j].setArrive(AF.getEtats()[AF.getInitState()[0]]);
                    }
                }

                //parcours out de chaque état
                for (int j = 0; j < AF.getEtats()[i].getOut().length; j++) {
                    //Si in contient un état supprimer le supprime et le remplace par l'unique init
                    if(AF.getEtats()[i].getOut()[j] != null && etatSupr.contains(AF.getEtats()[i].getOut()[j].getStart().getName())){
                        AF.getEtats()[i].getOut()[j].setStart(AF.getEtats()[AF.getInitState()[0]]);
                    }
                    if(AF.getEtats()[i].getOut()[j] != null && etatSupr.contains(AF.getEtats()[i].getOut()[j].getArrive().getName())){
                        AF.getEtats()[i].getOut()[j].setArrive(AF.getEtats()[AF.getInitState()[0]]);
                    }
                }

                //supprimer les transitions en double
                Transition[] in = new Transition[AF.getNumberTransition()];
                int indexin = 0;
                AF.getEtats()[i].setIndexIn(0);

                Transition[] out = new Transition[AF.getNumberTransition()];
                int indexout = 0;
                AF.getEtats()[i].setIndexIn(0);
                for (int j = 0; j < AF.getEtats()[i].getIn().length; j++) {
                    if (AF.getEtats()[i].getIn()[j] != null && contient(in, AF.getEtats()[i].getIn()[j])){
                        in[indexin] = AF.getEtats()[i].getIn()[j];
                        indexin++;
                        AF.getEtats()[i].setIndexIn(indexin);
                    }
                }

                for (int j = 0; j < AF.getEtats()[i].getOut().length; j++) {
                    if (AF.getEtats()[i].getOut()[j] != null && contient(out, AF.getEtats()[i].getOut()[j])){
                        out[indexout] = AF.getEtats()[i].getOut()[j];
                        indexout++;
                        AF.getEtats()[i].setIndexOut(indexout);
                    }
                }
                AF.getEtats()[i].setIn(in);
                AF.getEtats()[i].setOut(out);

            }
        }
        //SI TRANSITION ENTRANTE VERS L ETAT INITIAL
        if(AF.getEtats()[AF.getInitState()[0]].getIn() != null){
            int numberTransition = AF.getNumberTransition()+ AF.getEtats()[AF.getInitState()[0]].getIndexIn()+AF.getEtats()[AF.getInitState()[0]].getIndexOut();

            Etat initi = AF.getEtats()[AF.getInitState()[0]].copie();
            AF.setNumberTransition(numberTransition);
            initi.setName(numberStateIfReturn);
            AF.getEtats()[AF.getInitState()[0]].setInit(false);
            AF.getEtats()[AF.getInitState()[0]].setFinish(false);

            for (int i = 0; i < initi.getIndexIn(); i++) {
                initi.getIn()[i].setStart(initi);
            }
            for (int i = 0; i < initi.getIndexOut(); i++) {
                initi.getOut()[i].setStart(initi);
            }
            Etat[] newStates = new Etat[AF.getEtats().length+1];
            for (int i = 0; i < AF.getEtats().length+1; i++) {
                if(i == 0){
                    newStates[i] = initi;
                } else {
                    newStates[i] = AF.getEtats()[i-1];
                }
            }
            AF.setEtats(newStates);
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
        int[] tempInit = new int[AF.getEtats().length];
        int[] tempFinish = new int[AF.getEtats().length];
        int numberInitState = 0;
        int numberFinishState = 0;
        for (int i = 0; i < AF.getEtats().length; i++) {
            if(AF.getEtats()[i].getInit()){
                tempInit[numberInitState] = AF.getEtats()[i].getName();
                numberInitState++;
            }
            if(AF.getEtats()[i].getFinish()){
                tempFinish[numberFinishState] = AF.getEtats()[i].getName();
                numberFinishState++;
            }
        }
        int[] init = new int[numberInitState];
        int[] finish = new int[numberFinishState];
        System.arraycopy(tempInit, 0, init, 0, numberInitState);
        System.arraycopy(tempFinish, 0, finish, 0, numberFinishState);
        AF.setInitState(init);
        AF.setFinishState(finish);
    }


    public Automaton getAF() {
        return AF;
    }

    public boolean getIsStandard() {
        return isStandard;
    }
}
