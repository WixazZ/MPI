package Automaton;

public class Transition {
    private int start;
    private char word;
    private int arrive;

    public Transition(String transition) {
        this.start = transition.charAt(0);
        this.word = transition.charAt(1);
        this.arrive = transition.charAt(2);
    }

    public char getWord() {
        return word;
    }

    public int getArrive() {
        return arrive;
    }

    public int getStart() {
        return start;
    }

    public void setArrive(int arrive) {
        this.arrive = arrive;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setWord(char word) {
        this.word = word;
    }
}
