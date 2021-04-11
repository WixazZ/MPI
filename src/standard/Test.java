package standard;
import automaton.*;
import java.io.*;
import java.util.*;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {

        File fichier = new File("ressource/automate.txt");
        Scanner lecteur = new Scanner(fichier);

        Lecteur auto = new Lecteur();
        Automaton autom = auto.lecture(lecteur);

    }
}
