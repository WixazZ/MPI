package minimisation;

import java.util.*;
import automaton.*;

public class Minimisation {

    public static Automaton minimisation(Automaton automate){

        MiniGroup[] groups = initMinimation(automate);

        boolean minimized = false;

        while(!minimized){

            MiniGroup[] newGroups = new MiniGroup[0];

            for(int i = 0; i < groups.length; i++){
                newGroups = appendMiniGroupArray(newGroups, split(groups[i]));
            }

            int newLength = newGroups.length;
            for(int i = 0; i < newLength; i++){
                newGroups[i].refreshTransition(newGroups, newLength);
            }
            minimized = isMinimized(groups, newGroups);
            groups = newGroups;
        }

        return miniToAutomaton(groups);
    }

    public static Automaton miniToAutomaton(MiniGroup[] groups){

        Automaton automate = new Automaton();
        automate.setNumberAlphabet(groups[0].getMiniEtats()[0].getEtat().getIndexOut());
        automate.setNumberState(groups.length);
        automate.setNumberTransition(automate.getNumberAlphabet() * automate.getNumberState());

        Etat[] etats = new Etat[automate.getNumberState()];

        for(int i = 0; i < groups.length; i++){//Parcours des MiniGroups
            boolean init = false;
            for(int j = 0; j < groups[i].getIndexMiniEtats() && !init; j++){
                init = groups[i].getMiniEtats()[j].getEtat().getInit();
            }
            etats[i] = new Etat(i, init, groups[i].getFinish(), 0, 0);
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

        for(int i = 0; i < automate.getNumberState(); i++){
            for(int j = 0; j < automate.getNumberAlphabet(); j++){
                int k = 0;
                boolean find = false;
                while(k < automate.getNumberState() && !find){
                    if(groups[k] == groups[i].getMiniEtats()[0].getTransition()[j].getArrive().getMiniGroup()){
                        find = true;
                    } else{
                        k++;
                    }
                }

                Transition trans = new Transition(etats[i], groups[i].getMiniEtats()[0].getTransition()[j].getWord(), etats[k]);
                etats[i].addTransition(trans, false);
                etats[k].addTransition(trans, true);
            }
        }

        automate.setInitState(initState);
        automate.setFinishState(finishState);
        automate.setEtats(etats);

        return automate;
    }

    public static MiniGroup[] appendMiniGroupArray(MiniGroup[] newGroups, MiniGroup[] tempGroup){
        int newLength = newGroups.length;
        int tempLength = tempGroup.length;
        newGroups = Arrays.copyOf(newGroups, newLength + tempLength);

        for(int i = 0; i < tempLength; i++){
            newGroups[newLength + i] = tempGroup[i];
        }
        return newGroups;
    }

    public static boolean isMinimized(MiniGroup[] oldGroup, MiniGroup[] currentGroup){
        return oldGroup.length == currentGroup.length;
    }

    public static MiniGroup[] split(MiniGroup group){//Split des différents MiniGroup

        MiniGroup[] groupes = new MiniGroup[group.getIndexMiniEtats()];
        groupes[0] = new MiniGroup(group.getName() + "0", group.getFinish());
        groupes[0].addEtat(new MiniEtat(group.getMiniEtats()[0], groupes[0]));
        int indexMiniGroup = 1;

        for(int i = 1; i < group.getIndexMiniEtats(); i++){
            boolean same = false;
            int j = 0;
            while(j < indexMiniGroup && !same){
                same = group.getMiniEtats()[i].compareTransition(groupes[j].getMiniEtats()[0]);
                j++;
            }
            if(same){
                j--;
                groupes[j].addEtat(new MiniEtat(group.getMiniEtats()[i], groupes[j]));
            } else{
                groupes[j] = new MiniGroup(group.getName() + j, group.getFinish());
                groupes[j].addEtat(new MiniEtat(group.getMiniEtats()[i], groupes[j]));
                indexMiniGroup++;
            }
        }
        for(int i = 0; i < indexMiniGroup; i++){
            groupes[i].makeTransition(groupes, indexMiniGroup);
        }
        groupes = Arrays.copyOf(groupes, indexMiniGroup);
        return groupes;
    }

    public static MiniGroup[] initMinimation(Automaton automate){//Initialisation des MiniGroups T et N

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