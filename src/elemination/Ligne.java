package elemination;

import java.util.*;
import automaton.*;

public class Ligne {

    private Etat[] name;
    private Colonne[] colonne;

    /**Constructeurs */
    public Ligne(Etat etat, int numberAlphabet){
        this.name = new Etat[1];
        this.name[0] = etat;
        this.colonne = new Colonne[numberAlphabet];
    }

    /**Accesseurs */
    public String getName(){
        return name;
    }

    public Colonne[] getColonne(){
        return colonne;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setColonne(Colonne[] colonne){
        this.colonne = colonne;
    }

    /**Methode */
    public void makeColonne(){
        for(int i = 0; i < name.length; i++){//Parcours de différent Etat concernés
            for(int j = 0; j < colonne.length; j++){//Remplissage des colonnes
                Etat[] etats = name[i].parcoursEpsilon('a' + j);
                colonne[j].setEtats(suppDoublon(mergeEtatTab(colonne[j].getEtat, etats)));
            }
        }
    }

    public Etat[] suppDoublon(Etat[] etats){
        int suppG = 0;
        for(int i = 0; i < etats.length - suppG; i++){
            int supp = 0;
            for(int j = i; j < etats.length - supp; j++){
                if(etats[i].getName() == etats[j].getName()){
                    supp++;
                    suppG++;
                }
                etats[j] = etats[j + supp];
            }
        }

        etats = Arrays.copyOf(etats, etats.length - suppG);
        return etats;
    }

    
}
