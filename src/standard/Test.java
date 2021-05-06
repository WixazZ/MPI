package standard;
import automaton.*;
import reconnaissance.Reco;
import java.io.*;
import java.util.*;

import static reconnaissance.Reco.read;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
      
      
        File fichier = new File("ressource/automate1.txt");

        File fichier = new File("ressource/automate6.txt");

        File fichier = new File("ressource/automate3.txt");
      
        Scanner lecteur = new Scanner(fichier);

        Automaton autom = Lecteur.lecture(lecteur);

        autom.printAutomate();


        System.out.println( read("abcd", autom));

        /*Standard stand = new Standard(autom);


        autom.completer();

        autom.printAutomate();
        Standard stand = new Standard(autom);

        stand.isStandard();
        System.out.println(stand.getIsStandard());
        stand.standardiser();

        System.out.println("**********************STANDARDISER**********************\n");
        stand.getAFD().printAutomate();

        //autom.complementaire();*/

        //autom.complementaire();

    }
}
