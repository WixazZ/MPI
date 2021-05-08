package elemination;

import java.util.*;
import automaton.*;

public class Elemination {

    public Automaton elemination(Automaton automate){

        Ligne[] ligne = new Ligne[1];
        int length= 1;
        ligne[0] = new Ligne(automate.getEtats()[automate.getInitState()[1]], automate.getNumberAlphabet());
        ligne[0].makeColonne();

        int i = 0;
        while(i < length){
            ligne = newLigne(ligne[i], ligne);
            i++;
            length = ligne.length;
        }



    }



    public Automaton ligneToAutomaton(Ligne[] lignes){

        Automaton automate = new Automaton();
        automate.setNumberAlphabet(lignes[0].getColonne().length);
        automate.setNumberState(lignes.length);

        Etat[] etats = new Etat[lignes.length];

    }

    public Ligne[] newLigne(Ligne ligne, Ligne[] ligneTab){

        Colonne[] colonnes = ligne.getColonne();
        int lengthNewTab = 0;

        int colonneLength = colonnes.length;
        for (int i = 0; i < colonneLength; i++){
            Ligne newLigne = new Ligne(colonnes[i].getEtats(), colonneLength);
            if(alreadyExist(ligneTab, newLigne)){
                ligneTab = addLigne(ligneTab, newLigne);
            }
        }

        return ligneTab;
    }

    public boolean alreadyExist(Ligne[] lignes, Etat[] etats){
        boolean exist = false;
        int i = 0;
        int lengthLigne = lignes.length;
        int lengthEtats = etats.length;
        while(i < lengthLigne && !exist){
            if(lignes[i].getName().length == lengthEtats){
                int j = 0;
                boolean same = true;
                while(j < lengthEtats && same){
                    same = lignes[i].getName()[j].getName() == etats[j].getName();
                }
                exist = same;
            }
        }
        return exist;
    }

    public boolean alreadyExist(Ligne[] lignes, Ligne newLigne) {
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
                }
                exist = same;
            }
        }
        return exist;
    }

    public Ligne[] addLigne(Ligne[] lignes, int length, Ligne newLigne){
        lignes = Arrays.copyOf(lignes, length + 1);
        lignes[length] = newLigne;

        return lignes;
    }

    public Ligne[] addLigne(Ligne[] lignes, Ligne newLigne){
        int length = lignes.length;
        lignes = Arrays.copyOf(lignes, length + 1);
        lignes[length] = newLigne;

        return lignes;
    }
}
