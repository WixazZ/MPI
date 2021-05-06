package minimisation;

import java.io.*;
import java.util.*;
import automaton.*;

public class Minimisation {

    public Automaton minimisation(Automaton automate){

        MiniGroup[] groups = initMinimation(automate);

        boolean minimized = false;

        while(!minimized){

            MiniGroup[] newGroups = new MiniGroup[0];

            for(int i = 0; i < groups.length; i++){
                newGroups = appendMiniGroupArray(newGroups, split(groups[i]));
            }

            int newLength = newGroups.length;
            for(int i = 0; i < newLength; i++){
                newGroups[i].makeTransition(newGroups, newLength);
            }
            minimized = isMinimized(groups, newGroups);
        }

    }

    public MiniGroup[] appendMiniGroupArray(MiniGroup newGroups[], MiniGroup tempGroup[]){
        int newLength = newGroups.length;
        int tempLength = tempGroup.length;
        newGroups = Arrays.copyOf(newGroups, newLength + tempLength);
        
        for(int i = 0; i < tempLength; i++){
            newGroups[newLength + i] = tempGroup[i];
        }
        return newGroups;
    }

    public boolean isMinimized(MiniGroup oldGroup[], MiniGroup currentGroup[]){
        return oldGroup.length == currentGroup.length;
    }

    public MiniGroup[] split(MiniGroup group){//Split des différents MiniGroup

        MiniGroup[] groupes = new MiniGroup[group.getIndexMiniEtats()];
        groupes[0] = new MiniGroup(group.getName() + "0", group.getFinish());
        groupes[0].addEtat(new MiniEtat(group.getMiniEtats()[1], groupes[0]));
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
                indexMiniGroup++;
            }
        }

        return groupes;
    }

    public MiniGroup[] initMinimation(Automaton automate){//Initialisation des MiniGroups T et N

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
