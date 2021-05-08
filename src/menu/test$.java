package menu;

import automaton.Automaton;
import automaton.Lecteur;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static reconnaissance.Reco.read;

public class test$ {
    public static void main(String[] args) throws FileNotFoundException {
        boolean run = true;
        while (run) {
            String path2 = Menu.choixmenu();
            //System.out.println("/DEBUG/ path2 = " + path2);
            File fichier = new File(path2);
            Scanner lecteur = new Scanner(fichier);

            Automaton autom = Lecteur.lecture(lecteur);
            autom.printAutomate();
            System.out.println(read("abcd", autom));

            System.out.println("Voulez vous utiliser un autre automate\n 1 = oui \n 2 = non");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();
            while (choice !=1 && choice != 2){
                System.out.println("VEUILLEZ ENTRER UN NOMBRE VALIDE!");
                System.out.println("\n****************\n Voulez vous utiliser un autre automate ?\n 1 =oui \n 2 = non");
                scanner = new Scanner(System.in);
                choice = scanner.nextInt();
            }
            if (choice == 2){
                run = false;
            }
        }
    }
}
