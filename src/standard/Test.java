package standard;
import automaton.*;
import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {

        File fichier = new File("ressource/automate1.txt");
        Scanner lecteur = new Scanner(fichier);

        Automaton autom = Lecteur.lecture(lecteur);

        autom.printAutomate();

        autom.complementaire();

        autom.printAutomate();
    }


}
