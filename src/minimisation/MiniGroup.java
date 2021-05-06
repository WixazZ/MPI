package minimisation;

import java.util.Arrays;


public class MiniGroup {
    private String name;
    private MiniEtat[] miniEtats;
    private int indexEtats;

    /**Constructeur */
    public MiniGroup(){
        this.name = "";
        this.miniEtats = null;
        this.indexEtats = 0;
    }

    public MiniGroup(String name){
        this.name = name;
        this.miniEtats = null;
        this.indexEtats = 0;
    }

    public MiniGroup(String name, MiniEtat[] miniEtats, int indexEtats){
        this.name = name;
        this.miniEtats = miniEtats;
        this.indexEtats = indexEtats;
    }

    /**Accesseurs */
    public String getName(){
        return name;
    }

    public MiniEtat[] getEtats(){
        return miniEtats;
    }

    public int getIndexEtats(){
        return indexEtats;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setEtats(MiniEtat[] miniEtats){
        this.miniEtats = miniEtats;
    }

    public void setIndexEtats(int indexEtats){
        this.indexEtats = indexEtats;
    }

    /**Methode */
    public void addEtat(MiniEtat etat){
        if(indexEtats == 0){
            miniEtats = new MiniEtat[1];
            indexEtats++;
        } else{
            miniEtats = Arrays.copyOf(miniEtats, indexEtats + 1);
            miniEtats[indexEtats] = etat;
            indexEtats++;
        }
    
    }

    public void removeEtat(MiniEtat etat){
        int i = 0;
        for(; i < indexEtats && miniEtats[i] != etat; i++){}
        if(i < indexEtats && miniEtats[i] == etat){
            for(; i < indexEtats - 1; i++){
                miniEtats[i] = miniEtats[i + 1];
            }
            indexEtats--;
            miniEtats = Arrays.copyOf(miniEtats, indexEtats);
        }
    }

    public void removeEtat(int index){
        if(index < indexEtats){
            for(int i = index; i < indexEtats - 1; i++){
                miniEtats[i] = miniEtats[i + 1];
            }
            indexEtats--;
            miniEtats = Arrays.copyOf(miniEtats, indexEtats);
        }
    }

    public void makeTransition(){
        
    }
}
