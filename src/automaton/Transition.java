package automaton;

public class Transition {
    private Etat start;
    private char word;
    private Etat arrive;

    /**Constructeurs**/
    public Transition(Etat start,char word,Etat arrive){
        this.start = start;
        this.word = word;
        this.arrive = arrive;
    }

    /**ACCESSEURS**/
    public char getWord() {
        return word;
    }

    public Etat getArrive() {
        return arrive;
    }

    public Etat getStart() {
        return start;
    }

    public void setArrive(Etat arrive) {
        this.arrive = arrive;
    }

    public void setStart(Etat start) {
        this.start = start;
    }

    public void setWord(char word) {
        this.word = word;
    }

    /**Méthode**/
    public Transition copie(){
        return new Transition(this.start, this.word, this.arrive);
    }
}
