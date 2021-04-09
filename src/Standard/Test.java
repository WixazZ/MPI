package Standard;


import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {

        java.io.File fichier = new java.io.File("ressource/automate.txt");
        java.util.Scanner lecteur = new java.util.Scanner(fichier);

        while (lecteur.hasNextLine()){
            System.out.println(lecteur.nextLine());
        }

    }
}
