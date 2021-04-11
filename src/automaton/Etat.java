package automaton;

public class Etat {
    private int name;
    private Transition[] in;
    private int indexIn;
    private Transition[] out;
    private int indexOut;
    private boolean init;
    private boolean finish;

    /**Contructeur**/
    public Etat(int name, boolean init, boolean finish, int numberTransition){
        this.name = name;
        this.init = init;
        this.finish = finish;
        indexIn = 0;
        in = new Transition[numberTransition];
        indexOut = 0;
        out = new Transition[numberTransition];
    }

    /**ACCESSEURS**/
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

    public void setIn(Transition[] input) {
        in = input;
    }

    public void setOut(Transition[] output) {
        out = output;
    }

    public void setInit(boolean init) {
        this.init = init;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    /**Methode**/
    public void printEtat(){
        System.out.println("*********************************************");
        System.out.println("Nom de l'état: " + name);
        if(init){
            System.out.println("Etat initial");
        }
        if(finish){
            System.out.println("Etat Terminal");
        }
        printTransition();
    }

    public void printTransition(){
        System.out.println("Transition entrante:");
        for (int i = 0; i < indexIn; i++){
            System.out.println(in[i].getStart().name + " " + in[i].getWord() + " " + in[i].getArrive().name);
        }
        System.out.println("Transition sortante:");
        for (int i = 0; i < indexOut; i++){
            System.out.println(out[i].getStart().name + " " + out[i].getWord() + " " + out[i].getArrive().name);
        }
    }

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
