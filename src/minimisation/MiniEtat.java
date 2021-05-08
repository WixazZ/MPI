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
        this.transition = new MiniTransition[0];
    }

    public MiniEtat(Etat etat, MiniGroup miniGroup){
        this.etat = etat;
        this.miniGroup = miniGroup;
        this.transition = new MiniTransition[etat.getIndexOut()];
    }

    public MiniEtat(MiniEtat miniEtat, MiniGroup miniGroup){
        this.etat = miniEtat.etat;
        this.miniGroup = miniGroup;
        this.transition = miniEtat.transition;
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
    public void makeTransition(MiniGroup[] groupes, int lengthGroupes){
        char[] alphabet = new char[etat.getIndexOut()];
        for(int i = 0; i < etat.getIndexOut(); i++){
            alphabet[i] = (char) ('a' + i);
        }

        for(int i = 0; i < etat.getIndexOut(); i++){//Parcours des différentes transitions
            boolean created = false;
            int j = 0;
            while(j < etat.getIndexOut() && !created){//Recherche de la transition avec le bon mot

                if(etat.getOut()[j].getWord() == alphabet[i]){//Recherche de la transition correspond au mot

                    for(int k = 0; k < lengthGroupes && !created; k++){//Parcours des différents MiniGroupp
                        if(groupes[k].getFinish() == etat.getOut()[j].getArrive().getFinish()){//Permet de filtrer les groupes en fonction de leur finish

                            for(int l = 0; l < groupes[k].getIndexMiniEtats() && !created; l++){//Parcours des différents MiniEtat dans le MiniGroup
                                if(groupes[k].getMiniEtats()[l].etat == etat.getOut()[j].getArrive()){//Recherche du MiniEtat correspond à l'Etat de la transition
                                    transition[i] = new MiniTransition(this, alphabet[i], groupes[k].getMiniEtats()[l]);//Création de la MiniTransition
                                    created = true;
                                }
                            }
                        }
                    }
                }
                j++;

            }
        }
    }

    public void refreshTransition(MiniGroup[] groupes, int lengthGroupes){
        for(int i = 0; i < etat.getIndexOut(); i++){//Parcours des transitions du MiniEtat
            transition[i].setDepart(this);
            for(int j = 0; j < lengthGroupes; j++){//Parcours des MiniGroups
                boolean same = true;
                for(int k = 0; k < transition[i].getArrive().getMiniGroup().getName().length() && same; k++){//Parcours des lettres des noms
                    same = groupes[j].getName().charAt(k) == transition[i].getArrive().getMiniGroup().getName().charAt(k);
                }
                if(same){
                    boolean find = false;
                    int k = 0;
                    while(k < groupes[j].getIndexMiniEtats() && !find){
                        find = groupes[j].getMiniEtats()[k].etat == transition[i].getArrive().etat;
                        k++;
                    }
                    if(find){
                        k--;
                        transition[i].setArrive(groupes[j].getMiniEtats()[k]);
                    }
                }
            }
        }
    }

    public boolean compareTransition(MiniEtat miniEtat){

        boolean same = true;

        for(int i = 0; i < this.transition.length && same; i++){
            String group1 = miniEtat.transition[i].getArrive().miniGroup.getName();
            String group2 = transition[i].getArrive().miniGroup.getName();
            for(int j = 0; j < group2.length() && same; j++){
                if(group1.charAt(j) != group2.charAt(j)){
                    same = false;
                }
            }
        }
        return same;
    }
}
