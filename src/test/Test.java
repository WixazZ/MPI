package test;

import automaton.*;
import comp.*;
import determination.*;
import elemination.*;
import reconnaissance.*;
import standard.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static reconnaissance.Reco.read;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {


        File fichier = new File("ressource/automate/R1-5.txt");


        Scanner lecteur = new Scanner(fichier);

        Automaton autom = Lecteur.lecture(lecteur);

        autom.printAutomate();


        System.out.println( read("abcd", autom));

        /*System.out.println("**********************STANDARDISER**********************\n");
        Standard stand = new Standard(autom);

        //autom.completer();

        stand.isStandard();
        System.out.println(stand.getIsStandard());

        stand.standardiser();


        stand.getAF().printAutomate();

        stand.isStandard();
        System.out.println(stand.getIsStandard());*/

        System.out.println("**********************DETERMINISER**********************\n");
        Determinise deter = new Determinise(autom);

        deter.isDeterministe();
        System.out.println(deter.getisDeterministe());

        deter.determiniser();
        deter.getAFD().printAutomate();

        deter.isDeterministe();
        System.out.println(deter.getisDeterministe());


    }
}
