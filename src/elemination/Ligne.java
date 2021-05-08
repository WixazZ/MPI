package elemination;

import java.util.*;
import automaton.*;

import static automaton.Etat.mergeEtatTab;

public class Ligne {

    private Etat[] name;
    private Colonne[] colonne;
    private boolean init;
    private boolean finish;

    /**Constructeurs */
    public Ligne(Etat etat, int numberAlphabet){
        this.name = new Etat[1];
        this.name[0] = etat;
        this.colonne = new Colonne[numberAlphabet];
        this.init = etat.getInit();
        this.finish = etat.getFinish();
    }

    public Ligne(Etat[] etat, int numberAlphabet){
        this.name = etat;
        this.colonne = new Colonne[numberAlphabet];
        this.init = false;
        this.finish = false;
        int nameLength = name.length;
        int i = 0;
        while (i < nameLength && (!init || !finish)){
            init = name[i].getInit();
            finish = name[i].getFinish();
        }
    }

    /**Accesseurs */
    public Etat[] getName(){
        return name;
    }

    public Colonne[] getColonne(){
        return colonne;
    }

    public void setName(Etat[] name){
        this.name = name;
    }

    public void setColonne(Colonne[] colonne){
        this.colonne = colonne;
    }

    /**Methode */
    public void makeColonne(){
        for(int i = 0; i < name.length; i++){//Parcours de différent Etat concernés
            for(int j = 0; j < colonne.length; j++){//Remplissage des colonnes
                Etat[] etats = name[i].parcoursEpsilon((char) ('a' + j));
                colonne[j].setEtats(suppDoublon(mergeEtatTab(colonne[j].getEtats(), etats)));
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
