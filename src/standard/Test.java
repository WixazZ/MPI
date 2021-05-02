package standard;
import automaton.*;
import java.io.*;
import java.util.*;

public class Test {

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
            if (find == true)
                actif = actif.getOut()[k].getArrive();
            else
                return false;
        }
        return actif.getFinish();
    }


        public static void main(String[] args) throws FileNotFoundException {

        File fichier = new File("ressource/automate6.txt");
        Scanner lecteur = new Scanner(fichier);

        Automaton autom = Lecteur.lecture(lecteur);

        autom.printAutomate();

        System.out.println( read("abcd", autom));

        /*Standard stand = new Standard(autom);
        stand.isStandard();
        System.out.println(stand.getIsStandard());
        stand.standardiser();

        System.out.println("**********************STANDARDISER**********************\n");
        stand.getAFD().printAutomate();
        //autom.complementaire();*/
    }


}
