package minimisation;

import automaton.*;

public class Minimisation {

    public Automaton minimisation(Automaton automate){

        MiniGroup[] groups = initMinimation(automate);

    }

    public MiniGroup[] initMinimation(Automaton automate){

        Etat[] etats = automate.getEtats();

        MiniGroup[] groupes = new MiniGroup[2];
        int lengthGroupes = 2;

        groupes[0] = new MiniGroup("T", true);
        groupes[1] = new MiniGroup("N", false);

        for(int i = 0; i < automate.getNumberState(); i++){
            if(etats[i].getFinish()){
                groupes[0].addEtat(new MiniEtat(etats[i], groupes[0]));
            } else{
                groupes[1].addEtat(new MiniEtat(etats[i], groupes[1]));
            }
        }

        groupes[0].makeTransition(groupes, lengthGroupes);
        groupes[1].makeTransition(groupes, lengthGroupes);

        return groupes;

    }
    
}
