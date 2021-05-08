package menu;
import java.util.*;

public class Menu {
    public static String choixmenu(){

        /*public Automaton programRun(boolean run) throws FileNotFoundException {*/

        String path1 = "ressource/automate/R1-";
        Scanner choix = new Scanner(System.in);
        System.out.println("Veuillez entrer le numéro de l'automate que vous souhaitez utiliser et appuyez sur entrée");
        int str = choix.nextInt();
        System.out.println("Vous avez saisi : " + str);
        while (str < 1 || str > 45){
            System.out.println("Veuillez entrer un nombre entre 1 et 45");
            System.out.println("Veuillez entrer le numéro de l'automate que vous souhaitez utiliser et appuyez sur entrée");
            str = choix.nextInt();
            System.out.println("Vous avez saisi : " + str+"\n");
        }

        path1 += str;
        path1 += ".txt";
        //System.out.println("/DEBUG/ path = " + path1);
        return path1;

        //}
    }
}

