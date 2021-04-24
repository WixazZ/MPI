package standard;
import automaton.*;
import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {

        File fichier = new File("ressource/automate4.txt");
        Scanner lecteur = new Scanner(fichier);

        Automaton autom = Lecteur.lecture(lecteur);

        autom.printAutomate();

        Standard stand = new Standard(autom);
        stand.isStandard();
        System.out.println(stand.getIsStandard());
        stand.standardiser();

        System.out.println("**********************STANDARDISERZ**********************");
        stand.getAFD().printAutomate();
        //autom.complementaire();
    }


}
