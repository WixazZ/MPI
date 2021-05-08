package elemination;

import automaton.Etat;

import java.util.Arrays;

public class Colonne {
    private char name;
    private Etat[] etats;
    private Ligne ligne;

    /**Constructeurs**/
    public Colonne(char name){
        this.name = name;
        this.etats = new Etat[0];
        this.ligne = null;
    }

    public Colonne(char name, Ligne ligne){
        this.name = name;
        this.etats = new Etat[0];
        this.ligne = ligne;
    }

    public Colonne(char name, Etat[] etats,Ligne ligne){
        this.name = name;
        this.etats = etats;
        this.ligne = ligne;
    }

    /**Accesseurs**/
    public char getName() {
        return name;
    }

    public Etat[] getEtats() {
        return etats;
    }

    public Ligne getLigne() {
        return ligne;
    }

    public void setName(char name) {
        this.name = name;
    }

    public void setEtats(Etat[] etats) {
        this.etats = etats;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

    /**Methodes**/
    public void addEtats(Etat etat){
        int length = etats.length;
        if(length == 0){
            etats = new Etat[1];
            etats[0] = etat;
        } else{
            etats = Arrays.copyOf(etats, length + 1);
            int i = 0;
            while(i < length && etats[i].getName() < etat.getName()){
                i++;
            }
            if(etats[i].getName() != etat.getName()){
                for(int j = length; j >= i; j--){
                    etats[j] = etats[j - 1];
                }
                etats[i] = etat;
            }
        }
    }

}