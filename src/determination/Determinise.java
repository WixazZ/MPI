package determination;

import automaton.Automaton;
import automaton.Etat;
import automaton.Transition;
import elemination.*;

import java.util.*;

import static elemination.Elemination.elemination;

public class Determinise {

    private boolean isDeterministe;
    private Automaton AFD;


    public Determinise(Automaton autom){
        this.AFD = autom;
    }

    public void isDeterministe(){
        if(AFD.getInitState().length != 1){
            System.out.println("L'automate n'est pas deterministe car il contient plusieurs états initials");
            isDeterministe = false;
        } else {
            boolean asVerify = true;
            int i = 0;
            while(i< AFD.getEtats().length && asVerify){
                List<Character> OutEtat = new ArrayList<>();
                int j = 0;
                Character NameOutEtat;
                while(j < AFD.getEtats()[i].getOut().length &&AFD.getEtats()[i].getOut()[j] != null && asVerify){

                    NameOutEtat = AFD.getEtats()[i].getOut()[j].getWord();

                    if(OutEtat.contains(NameOutEtat)){
                        System.out.println("L'automate n'est pas deterministe car l'état "+ AFD.getEtats()[i]+ " contient plusieurs états identiaque");
                        asVerify = false;
                    }
                    OutEtat.add(NameOutEtat);
                    j++;
                }
                i++;
            }
            isDeterministe = asVerify;
            if(asVerify){
                System.out.println("L'automate est bien deterministe");
            }
        }
    }

    public void determiniser(){
        if(!isDeterministe){

            /*
            if (AFD.getInitState().length != 1){
                Etat recup = AFD.getEtats()[AFD.getInitState()[0]];

                for (Etat i : AFD.getEtats()) { // parcours des états
                    if(i.getInit() && i!= recup){ // si l'état est un init
                        sync_state_init(AFD, recup, i);
                    }
                }
                int[] init_states = new int [1];
                init_states[0] = recup.getName();
                AFD.setInitState(init_states);
            }*/
            synciit();



            Automaton autom_copy = AFD.copie();
            AFD = elemination(autom_copy);
            System.out.println("coucou");

/*
            List<Sync_state> a_traiter = new ArrayList<>();

            for (Etat p_state: AFD.getEtats()) {

                boolean create = false;
                Sync_state value = new Sync_state();
                List<Etat> Sync = new ArrayList<>();
                List<Character> char_list = new ArrayList<>();
                List<Sync_state> word_en_double = new ArrayList<>();

                for (int i = 0; i < p_state.getIndexOut(); i++) { // Parcour toutes les transitions de l'etat

                    for (int j = 0; j < p_state.getIndexOut(); j++) { //Parcour toutes les transitions de l'etat

                        if (i != j && p_state.getOut()[i].getWord() == p_state.getOut()[j].getWord()) { //compare si transition en double

                            if(!create){
                                value.setMaster(p_state);
                                value.setCharacter(p_state.getOut()[i].getWord());
                                char_list.add(p_state.getOut()[i].getWord());
                                create = true;
                            }
                            if(p_state.getOut()[i].getWord() == value.getCharacter()){
                                if(!Sync.contains(p_state.getOut()[j].getArrive())){
                                    Sync.add(p_state.getOut()[i].getArrive());
                                }
                                if(!Sync.contains(p_state.getOut()[j].getArrive())){
                                    Sync.add(p_state.getOut()[j].getArrive());
                                }
                            }else{
                                if(!char_list.contains(p_state.getOut()[i].getWord())){
                                    char_list.add(p_state.getOut()[i].getWord());
                                }
                            }
                            //sync_state(autom_copy, p_state.getOut()[i].getArrive(), p_state.getOut()[j].getArrive());
                        }
                    }

                }
                if(Sync.size() !=0){
                    value.setSync(Sync);
                }
                if (create) {
                    a_traiter.add(value);
                }
            }*/
            //System.out.println("tes");


        } else {
            System.out.println("L'automate est déjà deterministe");
        }

    }

    public void sync_state(Etat convert){

    }

    public void synciit(){
        int numberStateIfReturn = AFD.getEtats()[AFD.getInitState()[0]].getName();
        if (AFD.getInitState().length != 1) {
            //SUPPRESSION ET RASSEMBLEMENT DES INITS

            Transition[] trans_in = AFD.getEtats()[AFD.getInitState()[0]].getIn();

            Transition[] trans_out = AFD.getEtats()[AFD.getInitState()[0]].getOut();

            for (Etat elem : AFD.getEtats()) {
                if(elem.getName()> numberStateIfReturn){
                    numberStateIfReturn = elem.getName();
                }
            }
            numberStateIfReturn++;

            int modif_numberState = AFD.getNumberState();

            boolean initIsFinish = false;

            for (int i = 1; i < AFD.getInitState().length; i++) {

                int lengthTransOut = trans_out.length;
                int lengthTransIn = trans_in.length;
                int k = 0;
                while(k < lengthTransIn && trans_in[k] !=null){
                    k++;
                }
                for (int j = 0; j < AFD.getEtats()[AFD.getInitState()[i]].getIn().length; j++) {
                    if(k < AFD.getNumberTransition()){
                        if(k >= lengthTransIn){
                            trans_in = Arrays.copyOf(trans_in, lengthTransIn+ 1);
                            lengthTransIn++;
                        }
                        trans_in[k] = AFD.getEtats()[AFD.getInitState()[i]].getIn()[j];
                        k++;
                    }
                }

                k = 0;
                while(k < lengthTransOut && trans_out[k] !=null){
                    k++;
                }
                for (int j = 0; j < AFD.getEtats()[AFD.getInitState()[i]].getOut().length; j++) {
                    if(k < AFD.getNumberTransition()) {
                        if(k >= lengthTransOut){
                            trans_out = Arrays.copyOf(trans_out, lengthTransOut + 1);
                            lengthTransOut++;
                        }
                        trans_out[k] = AFD.getEtats()[AFD.getInitState()[i]].getOut()[j];
                        k++;
                    }
                }

                AFD.getEtats()[AFD.getInitState()[0]].setIndexIn(AFD.getEtats()[AFD.getInitState()[0]].getIndexIn() + AFD.getEtats()[AFD.getInitState()[i]].getIndexIn());
                AFD.getEtats()[AFD.getInitState()[0]].setIndexOut(AFD.getEtats()[AFD.getInitState()[0]].getIndexOut() + AFD.getEtats()[AFD.getInitState()[i]].getIndexOut());

                if(!initIsFinish && AFD.getEtats()[AFD.getInitState()[i]].getFinish()){
                    initIsFinish = true;
                }
                modif_numberState--;
            }
            if(initIsFinish){
                AFD.getEtats()[AFD.getInitState()[0]].setFinish(true);
            }
            Etat[] reetat = new Etat[modif_numberState];
            int ite = 0;
            int k = 0;
            List<Integer> etatSupr = new ArrayList<>();
            while (ite < AFD.getNumberState()){
                if(AFD.getInitState()[0] == ite || !AFD.getEtats()[ite].getInit()){
                    reetat[k] = AFD.getEtats()[ite];
                    k++;
                }else{
                    etatSupr.add(ite);
                }
                ite++;

            }
            AFD.setNumberState(modif_numberState);
            AFD.setEtats(reetat);

            int[] init = new int[1];
            init[0] = AFD.getInitState()[0];
            AFD.setInitState(init);

            for (int i = 0; i < AFD.getEtats().length; i++) {

                //parcours in de chaque état
                for (int j = 0; j < AFD.getEtats()[i].getIn().length; j++) {
                    //Si in contient un état supprimer le supprime et le remplace par l'unique init
                    if(AFD.getEtats()[i].getIn()[j] != null && etatSupr.contains(AFD.getEtats()[i].getIn()[j].getStart().getName())){
                        AFD.getEtats()[i].getIn()[j].setStart(AFD.getEtats()[AFD.getInitState()[0]]);
                    }
                    if(AFD.getEtats()[i].getIn()[j] != null && etatSupr.contains(AFD.getEtats()[i].getIn()[j].getArrive().getName())){
                        AFD.getEtats()[i].getIn()[j].setArrive(AFD.getEtats()[AFD.getInitState()[0]]);
                    }
                }

                //parcours out de chaque état
                for (int j = 0; j < AFD.getEtats()[i].getOut().length; j++) {
                    //Si in contient un état supprimer le supprime et le remplace par l'unique init
                    if(AFD.getEtats()[i].getOut()[j] != null && etatSupr.contains(AFD.getEtats()[i].getOut()[j].getStart().getName())){
                        AFD.getEtats()[i].getOut()[j].setStart(AFD.getEtats()[AFD.getInitState()[0]]);
                    }
                    if(AFD.getEtats()[i].getOut()[j] != null && etatSupr.contains(AFD.getEtats()[i].getOut()[j].getArrive().getName())){
                        AFD.getEtats()[i].getOut()[j].setArrive(AFD.getEtats()[AFD.getInitState()[0]]);
                    }
                }

                //supprimer les transitions en double
                Transition[] in = new Transition[AFD.getNumberTransition()];
                int indexin = 0;
                AFD.getEtats()[i].setIndexIn(0);

                Transition[] out = new Transition[AFD.getNumberTransition()];
                int indexout = 0;
                AFD.getEtats()[i].setIndexIn(0);
                for (int j = 0; j < AFD.getEtats()[i].getIn().length; j++) {
                    if (AFD.getEtats()[i].getIn()[j] != null && contient(in, AFD.getEtats()[i].getIn()[j])){
                        in[indexin] = AFD.getEtats()[i].getIn()[j];
                        indexin++;
                        AFD.getEtats()[i].setIndexIn(indexin);
                    }
                }

                for (int j = 0; j < AFD.getEtats()[i].getOut().length; j++) {
                    if (AFD.getEtats()[i].getOut()[j] != null && contient(out, AFD.getEtats()[i].getOut()[j])){
                        out[indexout] = AFD.getEtats()[i].getOut()[j];
                        indexout++;
                        AFD.getEtats()[i].setIndexOut(indexout);
                    }
                }
                AFD.getEtats()[i].setIn(in);
                AFD.getEtats()[i].setOut(out);

            }
        }
    }
    public void sync_state_init(Automaton autom_copy, Etat ele, Etat convert){

        Transition[] trans_in = ele.getIn();
        Transition[] trans_out = ele.getOut();

        int modif_numberState = autom_copy.getNumberState();

        int k = 0;
        while(trans_in[k] !=null){
            k++;
        }
        for (int j = 0; j < convert.getIn().length; j++) {
            if(k < autom_copy.getNumberTransition()){
                trans_in[k] = convert.getIn()[j];
                k++;
            }
        }

        k = 0;
        while(k < trans_out.length && trans_out[k] !=null){
            k++;
        }
        for (int j = 0; j < convert.getOut().length; j++) {
            if(k < trans_out.length && trans_out[k] == null) {
                trans_out[k] = convert.getOut()[j];
                k++;
            }
        }

        ele.setIndexIn(ele.getIndexIn() + convert.getIndexIn());
        ele.setIndexOut(ele.getIndexOut() + convert.getIndexOut());

        if(convert.getFinish()){
            ele.setFinish(true);
        }

        modif_numberState--;

        Etat[] reetat = new Etat[modif_numberState];
        int ite = 0;
        k = 0;
        List<Integer> etatSupr = new ArrayList<>();
        while (ite <= modif_numberState){
            if(convert != autom_copy.getEtats()[ite]){
                reetat[k] = autom_copy.getEtats()[ite];
                k++;
            }else{
                etatSupr.add(ite);
            }
            ite++;

        }
        autom_copy.setNumberState(modif_numberState);
        autom_copy.setEtats(reetat);



        for (int i = 0; i < autom_copy.getEtats().length; i++) {

            //parcours in de chaque état
            for (int j = 0; j < autom_copy.getEtats()[i].getIn().length; j++) {
                //Si in contient un état supprimer le supprime et le remplace par l'unique init
                if(autom_copy.getEtats()[i].getIn()[j] != null && etatSupr.contains(autom_copy.getEtats()[i].getIn()[j].getStart().getName())){
                    autom_copy.getEtats()[i].getIn()[j].setStart(ele);
                }
                if(autom_copy.getEtats()[i].getIn()[j] != null && etatSupr.contains(autom_copy.getEtats()[i].getIn()[j].getArrive().getName())){
                    autom_copy.getEtats()[i].getIn()[j].setArrive(ele);
                }
            }

            //parcours out de chaque état
            for (int j = 0; j < autom_copy.getEtats()[i].getOut().length; j++) {
                //Si in contient un état supprimer le supprime et le remplace par l'unique init
                if(autom_copy.getEtats()[i].getOut()[j] != null && etatSupr.contains(autom_copy.getEtats()[i].getOut()[j].getStart().getName())){
                    autom_copy.getEtats()[i].getOut()[j].setStart(ele);
                }
                if(autom_copy.getEtats()[i].getOut()[j] != null && etatSupr.contains(autom_copy.getEtats()[i].getOut()[j].getArrive().getName())){
                    autom_copy.getEtats()[i].getOut()[j].setArrive(ele);
                }
            }

            //supprimer les transitions en double
            Transition[] in = new Transition[autom_copy.getNumberTransition()];
            int indexin = 0;
            autom_copy.getEtats()[i].setIndexIn(0);

            Transition[] out = new Transition[autom_copy.getNumberTransition()];
            int indexout = 0;
            autom_copy.getEtats()[i].setIndexIn(0);
            for (int j = 0; j < autom_copy.getEtats()[i].getIn().length; j++) {
                if (autom_copy.getEtats()[i].getIn()[j] != null && contient(in, autom_copy.getEtats()[i].getIn()[j])){
                    in[indexin] = autom_copy.getEtats()[i].getIn()[j];
                    indexin++;
                    autom_copy.getEtats()[i].setIndexIn(indexin);
                }
            }

            for (int j = 0; j < autom_copy.getEtats()[i].getOut().length; j++) {
                if (autom_copy.getEtats()[i].getOut()[j] != null && contient(out, autom_copy.getEtats()[i].getOut()[j])){
                    out[indexout] = autom_copy.getEtats()[i].getOut()[j];
                    indexout++;
                    autom_copy.getEtats()[i].setIndexOut(indexout);
                }
            }
            autom_copy.getEtats()[i].setIn(in);
            autom_copy.getEtats()[i].setOut(out);
        }
        ele.setName(create_name_state());
    }
    private boolean contient(Transition[] tab, Transition var){

        for (Transition transition : tab) {
            if (transition != null && transition.getStart().getName() == var.getStart().getName() && transition.getArrive().getName() == var.getArrive().getName() && transition.getWord() == var.getWord()) {
                return false;
            }
        }
        return true;
    }
//    public void sync_init_state(Etat recup, Etat i){
//
//        recup.setName(create_name_state());
//
//            if(i.getName() != recup.getName()){ //si c'est pas l'état initial
//
//                    List<Transition> supp_double = new ArrayList<>(Arrays.asList(recup.getOut()));
//                    Transition[] new_out = new Transition[i.getOut().length];
//                    for (int j = 0; j < i.getOut().length; j++) {
//                        if(i.getOut()[j] != null){
//                            new_out[j] = i.getOut()[j].copie();
//                            if (new_out[j] != null) {
//                                new_out[j].setStart(recup);
//                                boolean equals = false;
//                                int k = 0;
//                                int l = 0;
//                                int supp_double_size = 0;
//                                while (supp_double.get(l) != null) {
//                                    l++;
//                                    supp_double_size = l;
//                                }
//                                while (k < supp_double_size && !equals) {
//                                    if (supp_double.get(k).getStart().getName() == new_out[j].getStart().getName() &&
//                                            supp_double.get(k).getWord() == new_out[j].getWord() &&
//                                            supp_double.get(k).getArrive().getName() == new_out[j].getArrive().getName() ){
//                                        equals = true;
//                                    }
//                                    k++;
//                                }
//                                if (!equals ){
//                                    AFD.setNumberState(AFD.getNumberState()+1);
//                                    supp_double.add(new_out[j]);
//                                }
//                            }
//                        }
//                    }
//                    int pa = 0;
//                    int taille = 0;
//                    while(pa < supp_double.size()){
//                        if(supp_double.get(pa) != null){
//                            taille++;
//                        }
//                        pa++;
//                    }
//                    pa = 0;
//                    Transition[] insert_out_trans = new Transition[taille];
//                    int j = 0;
//                    while (pa < supp_double.size()) {
//                        if(supp_double.get(pa) != null) {
//                            insert_out_trans[j] = supp_double.get(pa);
//                            j++;
//                        }
//                        pa++;
//                    }
//                    recup.setIndexOut(taille);
//                    recup.setOut(insert_out_trans);
//                    i.setInit(false);
//
//                if (i.getFinish()){
//                    recup.setFinish(true);
//                }
//            }
//
//    }

    public Automaton getAFD() {
        return AFD;
    }

    public int create_name_state(){
     ArrayList<Integer> max_state_name = new ArrayList<>();
        for (Etat i :
                AFD.getEtats()) {
            max_state_name.add(i.getName());
        }
     return Collections.max(max_state_name)+1;
    }

    public boolean getisDeterministe() {
        return isDeterministe;
    }

    public void setDeterministe(boolean deterministe) {
        isDeterministe = deterministe;
    }
}
