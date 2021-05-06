package reconnaissance;

import automaton.Automaton;
import automaton.Etat;

public class Reco {

    public static boolean read(String mot, Automaton auto) {

        Etat actif;

        int j = 0;
        while (j < auto.getNumberState() && !auto.getEtats()[j].getInit()) {
            j++;
        }
        actif = auto.getEtats()[j];

        for (int i = 0; i < mot.length(); i++) {

            boolean find = false;
            char x = mot.charAt(i);
            int k = 0;
            while(k < actif.getIndexOut() && !find){
                if (actif.getOut()[k].getWord() == x) {
                    find = true;
                } else
                    k++;
            }
            if (find)
                actif = actif.getOut()[k].getArrive();
            else
                return false;
        }
        return actif.getFinish();
    }
}
