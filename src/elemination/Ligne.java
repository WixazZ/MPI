package elemination;

import automaton.*;
import java.util.*;

import static automaton.Etat.mergeEtatTab;

public class Ligne {
    private Etat[] name;
    private int number;
    private Colonne[] colonne;
    private boolean init;
    private boolean finish;

    /**Constructeurs**/
    public Ligne(Etat etat, int numberAlphabet){
        this.name = new Etat[1];
        this.name[0] = etat;
        this.number = 0;
        this.colonne = new Colonne[numberAlphabet];
        for(int i = 0; i < numberAlphabet; i++){
            colonne[i] = new Colonne((char) ('a' + i));
        }
        this.init = etat.getInit();
        this.finish = etat.getFinish();
    }

    public Ligne(Etat[] etat, int numberAlphabet){
        this.name = etat;
        this.number = 0;
        this.colonne = new Colonne[numberAlphabet];
        for(int i = 0; i < numberAlphabet; i++){
            colonne[i] = new Colonne((char) ('a' + i));
        }
        this.init = false;
        this.finish = false;
        int nameLength = name.length;
        int i = 0;
        while (i < nameLength && (!init || !finish)){
            init = name[i].getInit();
            finish = name[i].getFinish();
        }
    }

    /**Accesseurs**/
    public Etat[] getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }

    public Colonne[] getColonne() {
        return colonne;
    }

    public boolean isInit() {
        return init;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setName(Etat[] name) {
        this.name = name;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setColonne(Colonne[] colonne) {
        this.colonne = colonne;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    /**Methodes**/
    public void makeColonne(){
        for(int i = 0; i < name.length; i++){//Parcours de différent Etat concernés
            for(int j = 0; j < colonne.length; j++){//Remplissage des colonnes
                Etat[] etats = name[i].parcoursEpsilon(colonne[i].getName());
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
