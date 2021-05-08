package elemination;

import automaton.*;
import java.util.*;

public class Elemination {

    public static Automaton elemination(Automaton automate){

        Ligne[] lignes = new Ligne[1];
        int length= 1;
        lignes[0] = new Ligne(automate.getEtats()[automate.getInitState()[0]], automate.getNumberAlphabet());//Ajout de la premiere ligne
        lignes[0].makeColonne();//Actualisation de ses colonnes
        lignes[0].setInit(true);//Ligne initial
        lignes[0].setFinish(lignes[0].isFinish());//Ligne terminal

        int i = 0;
        while(i < length){
            lignes = newLigne(lignes[i], lignes);
            i++;
            length = lignes.length;
        }

        lignes = refreshedColLigne(lignes);

        return ligneToAutomaton(lignes);
    }

    public static Automaton ligneToAutomaton(Ligne[] lignes){

        Automaton automate = new Automaton();
        automate.setNumberAlphabet(lignes[0].getColonne().length);
        automate.setNumberState(lignes.length);

        Etat[] etats = new Etat[lignes.length];

        for(int i = 0; i < lignes.length; i++){
            lignes[i].setNumber(i);
            etats[i] = new Etat(i, lignes[i].isInit(), lignes[i].isFinish(), 0, 0);
        }

        int numberInit = 0;
        int[] initState = new int[0];

        int numberFinish = 0;
        int[] finishState = new int[0];

        for(int i = 0; i < automate.getNumberState(); i++){
            if(etats[i].getInit()){
                initState = Arrays.copyOf(initState, numberInit + 1);
                initState[numberInit] = etats[i].getName();
                numberInit++;
            }

            if(etats[i].getFinish()){
                finishState = Arrays.copyOf(finishState, numberFinish + 1);
                finishState[numberFinish] = etats[i].getName();
                numberFinish++;
            }
        }

        automate.setInitState(initState);
        automate.setFinishState(finishState);

        for(int i = 0; i < automate.getNumberState(); i++){
            for(int j = 0; j < automate.getNumberAlphabet(); j++){
                Transition trans = new Transition(etats[i], lignes[0].getColonne()[j].getName(), etats[lignes[i].getColonne()[j].getLigne().getNumber()]);
                etats[i].addTransition(trans, false);
                etats[lignes[i].getColonne()[j].getLigne().getNumber()].addTransition(trans, true);
            }
        }

        automate.setEtats(etats);

        return automate;
    }

    public static Ligne[] newLigne(Ligne ligne, Ligne[] ligneTab){

        Colonne[] colonnes = ligne.getColonne();
        int lengthNewTab = 0;
        int colonneLength = colonnes.length;

        for (int i = 0; i < colonneLength; i++){
            Ligne newLigne = new Ligne(colonnes[i].getEtats(), colonneLength);
            if(!alreadyExist(ligneTab, newLigne)){
                ligneTab = addLigne(ligneTab, newLigne);
                newLigne.makeColonne();
                newLigne.setInit(isInit(newLigne));
                newLigne.setFinish(isFinish(newLigne));
            }
        }

        return ligneTab;
    }

    public static Ligne[] refreshedColLigne(Ligne[] lignes){
        int lengthLignes = lignes.length;
        for(int i = 0; i < lengthLignes; i++){//Parcours des lignes
            for(int j = 0; j < lignes[i].getColonne().length; j++){//Parcours des colonnes
                Etat[] etats = lignes[i].getColonne()[j].getEtats();
                for(int k = 0; k < lengthLignes; k++){//Parcours des colonnes des lignes
                    if(etats.length == lignes[k].getName().length){
                        int l = 0;
                        boolean same = true;
                        int lengthName = lignes[k].getName().length;
                        while(l < lengthName && same){//Parcours des Etat des name
                            same = etats[l].getName() == lignes[k].getName()[l].getName();
                            l++;
                        }
                        if(same){
                            lignes[i].getColonne()[j].setLigne(lignes[k]);
                        }
                    }
                }
            }
        }
        return lignes;
    }

    public static boolean alreadyExist(Ligne[] lignes, Ligne newLigne) {
        boolean exist = false;
        int i = 0;
        int lengthLigne = lignes.length;
        int lengthNewLigne = newLigne.getName().length;
        while(i < lengthLigne && !exist){
            if(lignes[i].getName().length == lengthNewLigne){
                int j = 0;
                boolean same = true;
                while(j < lengthNewLigne && same){
                    same = lignes[i].getName()[j].getName() == newLigne.getName()[j].getName();
                    j++;
                }
                exist = same;
            }
            i++;
        }
        return exist;
    }

    public static boolean isFinish(Ligne ligne){
        boolean finish = false;
        int i = 0;
        while(i < ligne.getName().length && !finish){
            finish = ligne.getName()[i].parcoursIsFinish(ligne.getName()[i]);
            i++;
        }
        return finish;
    }

    public static boolean isInit(Ligne ligne){
        boolean init = false;
        int i = 0;
        while(i <  ligne.getName().length && !init){
            init = ligne.getName()[i].parcoursIsInit(ligne.getName()[i]);
            i++;
        }
        return init;
    }

    public static Ligne[] addLigne(Ligne[] lignes, Ligne newLigne){
        int length = lignes.length;
        lignes = Arrays.copyOf(lignes, length + 1);
        lignes[length] = newLigne;

        return lignes;
    }
}
