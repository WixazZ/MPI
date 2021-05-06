package minimisation;

import automaton.*;

public class MiniEtat {
    private MiniGroup miniGroup;
    private Etat etat;
    private MiniTransition[] transition;

    /**Constructeurs */
    public MiniEtat(Etat etat){
        this.etat = etat;
        this.miniGroup = null;
        this.transition = null;
    }

    public MiniEtat(Etat etat, MiniGroup miniGroup){
        this.etat = etat;
        this.miniGroup = miniGroup;
        this.transition = null;
    }

    /**Accesseurs */
    public MiniGroup getMiniGroup(){
        return miniGroup;
    }

    public Etat getEtat(){
        return etat;
    }

    public MiniTransition[] getTransition(){
        return transition;
    }

    public void setMiniGroup(MiniGroup miniGroup){
        this.miniGroup = miniGroup;
    }

    public void setWord(Etat etat){
        this.etat = etat;
    }

    public void setTransition(MiniTransition[] transition){
        this.transition = transition;
    }

    /**Methode */

}


