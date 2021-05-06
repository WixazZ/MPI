package minimisation;

import automaton.*;

public class MiniGroup {
    private String name;
    private Etat[] etats;
    private int indexEtats;

    /**Constructeur */
    public MiniGroup(){
        this.name = "";
        this.etats = null;
        this.indexEtats = 0;
    }

    public MiniGroup(String name, Etat[] etats, int indexEtats){
        this.name = name;
        this.etats = etats;
        this.indexEtats = indexEtats;
    }

    /**Accesseurs */
    public String getName(){
        return name;
    }

    public Etat[] getEtats(){
        return etats;
    }

    public int getIndexEtats(){
        return indexEtats;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEtats(Etats[] etats){
        this.etats = etats;
    }

    public void setIndexEtats(int indexEtats){
        this.indexEtats = indexEtats;
    }

    /**Methode */
    public void addEtat(Etat etat){
        etats = Array.copyof(etats, indexEtats + 1);
        etats[indexEtats] = etat;
        indexEtats++;
    }

    public void removeEtat(Etat etat){
        int i = 0;
        for(; i < indexEtats && etats[i] != etat; i++){}
        if(i < indexEtats && etats[i] == etat){
            for(; i < indexEtats - 1; i++){
                etats[i] = etats[i + 1];
            }
            indexEtats--;
            etats = Array.copyof(etats, indexEtats);
        }
    }

    public void removeEtat(int index){
        if(index < indexEtats){
            for(int i = index; i < indexEtats - 1; i++){
                etats[i] = etats[i + 1];
            }
            indexEtats--;
            etats = Array.copyof(etats, indexEtats);
        }
    }
}
