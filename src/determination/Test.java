package determination;

import automaton.Automaton;
import automaton.Lecteur;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {

        File fichier = new File("ressource/automate/B1-5.txt");
        Scanner lecteur = new Scanner(fichier);

        Automaton autom = Lecteur.lecture(lecteur);

        autom.printAutomate();
        System.out.println("**********************DETERMINISER**********************\n");
        Determinise deter = new Determinise(autom);
        deter.isDeterministe();

        deter.determiniser();

        deter.getAFD().printAutomate();

    }
}
