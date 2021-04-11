package automaton;

public class Etat {
    private int name;
    private Transition[] in;
    private int indexIn;
    private Transition[] out;
    private int indexOut;
    private boolean init;
    private boolean finish;
    
    public Etat(int name, boolean init, boolean finish, int numberTransition){
        this.name = name;
        this.init = init;
        this.finish = finish;
        indexIn = 0;
        in = new Transition[numberTransition];
        indexOut = 0;
        out = new Transition[numberTransition];
    }

    /**Contructeur**/
    public int getName() {
        return name;
    }

    public Transition[] getIn() {
        return in;
    }

    public Transition[] getOut() {
        return out;
    }

    public boolean getInit() {
        return init;
    }

    public boolean getFinish() {
        return finish;
    }

    public void setName(int name) {
        this.name = name;
    }

    public void setIn(Transition[] in) {
        this.in = in;
    }

    public void setOut(Transition[] out) {
        this.out = out;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    /**Methode**/
    public void addTransition(Transition transition, boolean inBool){
        if(inBool){
            in[indexIn] = transition;
            indexIn++;
        } else{
            out[indexOut] = transition;
            indexOut++;
        }
    }
}
