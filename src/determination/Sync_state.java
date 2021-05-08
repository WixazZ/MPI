package determination;

import automaton.Etat;

import java.util.ArrayList;
import java.util.List;

public class Sync_state {
    private Etat master;
    private char character;
    private List<Etat> Sync = new ArrayList<>();

    public List<Etat> getSync() {
        return Sync;
    }

    public Etat getMaster() {
        return master;
    }

    public void setMaster(Etat master) {
        this.master = master;
    }

    public void setSync(List<Etat> sync) {
        Sync = sync;
    }

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

}
