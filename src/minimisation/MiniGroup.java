package minimisation;

import java.util.Arrays;

public class MiniGroup {
    private String name;
    private MiniEtat[] miniEtats;
    private int indexMiniEtats;
    private boolean finish;

    /**Constructeur */
    public MiniGroup(){
        this.name = "";
        this.miniEtats = new MiniEtat[0];
        this.indexMiniEtats = 0;
    }

    public MiniGroup(String name, boolean finish){
        this.name = name;
        this.miniEtats = new MiniEtat[0];
        this.indexMiniEtats = 0;
        this.finish = finish;
    }

    public MiniGroup(String name, MiniEtat[] miniEtats, int indexMiniEtats, boolean finish){
        this.name = name;
        this.miniEtats = miniEtats;
        this.indexMiniEtats = indexMiniEtats;
        this.finish = finish;
    }

    /**Accesseurs */
    public String getName(){
        return name;
    }

    public MiniEtat[] getMiniEtats(){
        return miniEtats;
    }

    public int getIndexMiniEtats(){
        return indexMiniEtats;
    }

    public boolean getFinish(){
        return finish;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMiniEtats(MiniEtat[] miniEtats){
        this.miniEtats = miniEtats;
    }

    public void setIndexMiniEtats(int indexMiniEtats){
        this.indexMiniEtats = indexMiniEtats;
    }

    /**Methode */
    public void addEtat(MiniEtat etat){
        if(indexMiniEtats == 0){
            miniEtats = new MiniEtat[1];
            miniEtats[0] = etat;
            indexMiniEtats++;
        } else{
            miniEtats = Arrays.copyOf(miniEtats, indexMiniEtats + 1);
            miniEtats[indexMiniEtats] = etat;
            indexMiniEtats++;
        }
    
    }

    public void removeEtat(MiniEtat etat){
        int i = 0;
        for(; i < indexMiniEtats && miniEtats[i] != etat; i++){}
        if(i < indexMiniEtats && miniEtats[i] == etat){
            for(; i < indexMiniEtats - 1; i++){
                miniEtats[i] = miniEtats[i + 1];
            }
            indexMiniEtats--;
            miniEtats = Arrays.copyOf(miniEtats, indexMiniEtats);
        }
    }

    public void removeEtat(int index){
        if(index < indexMiniEtats){
            for(int i = index; i < indexMiniEtats - 1; i++){
                miniEtats[i] = miniEtats[i + 1];
            }
            indexMiniEtats--;
            miniEtats = Arrays.copyOf(miniEtats, indexMiniEtats);
        }
    }

    public void makeTransition(MiniGroup[] groupes, int lengthGroupes){
        for(int i = 0; i < indexMiniEtats; i++){
            miniEtats[i].makeTransition(groupes, lengthGroupes);
        }
    }

    public void refreshTransition(MiniGroup[] groupes, int lengthGroupes){
        for(int i = 0; i < indexMiniEtats; i++){
            miniEtats[i].refreshTransition(groupes, lengthGroupes);
        }
    }
}
