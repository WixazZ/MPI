package minimisation;

public class MiniTransition {
    private MiniEtat depart;
    private char word;
    private MiniEtat arrive;

    /**Constructeurs */
    public MiniTransition(MiniEtat depart, char word, MiniEtat arrive){
        this.depart = depart;
        this.word = word;
        this.arrive =arrive;
    }

    /**Accesseurs */
    public MiniEtat getDepart(){
        return depart;
    }

    public char getWord(){
        return word;
    }

    public MiniEtat getArrive(){
        return arrive;
    }

    public void setDepart(MiniEtat depart){
        this.depart = depart;
    }

    public void setWord(char word){
        this.word = word;
    }

    public void setArrive(MiniEtat arrive){
        this.arrive = arrive;
    }

    /**Methode */
    
}
