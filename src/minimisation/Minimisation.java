package minimisation;

import automaton.*;

public class Minimisation {

    public MiniGroup[] initMinimation(Automaton automate){

        Etat[] etats = automate.getEtats();

        MiniGroup[] groupes = new MiniGroup[2];

        groupes[0] = new MiniGroup("T");
        groupes[1] = new MiniGroup("N");

        for(int i = 0; i < automate.getNumberState(); i++){
            if(etats[i].getFinish()){
                groupes[0].addEtat(new MiniEtat(etats[i], groupes[0]));
            } else{
                groupes[1].addEtat(new MiniEtat(etats[i], groupes[1]));
            }
        }

        
    }
    
}
