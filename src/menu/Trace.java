package menu;

import automaton.Automaton;
import standard.Standard;

import java.util.Scanner;

public class Trace {
    public static void affiche(int num, Automaton autom){

        System.out.println("\n*************************** AUTOMATE NUMERO "+num+" ***************************\n\n");
        System.out.println("***** Affichage de l'automate *****\n");
        autom.printAutomate();
        boolean synchrone = autom.is_synchrone(autom);
        if(synchrone){
            affichage_standard(autom);
        }
        if(synchrone){
            System.out.println("\n***** L'automate est synchrone *****\n");
            System.out.println("L'automate est synchrone nous n'avons donc pas besoin de faire d'elimination epsilon");
            System.out.println("\n***** Determination *****\n");
            System.out.println("Voici l'automate determiner");
        }else{
            System.out.println("\n***** L'automate est asynchrone *****\n");
            System.out.println("L'automate est asynchrone nous allons donc faire l'elimination des epsilon");
            System.out.println("\n***** Determination et suppression epsilon *****\n");
            System.out.println("Voici l'automate determiner et ");
        }
    }
    public static void affichage_standard(Automaton autom){
        System.out.println("\nVoulez vous rendre l'automate standard 1 = oui, 2 = non\n");

        Scanner choix = new Scanner(System.in);
        int choice = choix.nextInt();
        while(choice!=1 && choice!=2){
            System.out.println("Vous n'avez pas rentré un nombre valide");
            System.out.println("Voulez vous rendre l'automate standard 1 = oui, 2 = non\n");
            choix = new Scanner(System.in);
            choice = choix.nextInt();
        }
        if(choice == 1){
            Standard s_autom = new Standard(autom);
            s_autom.isStandard();
            s_autom.standardiser();
            System.out.println("***** Voici l'automate standard *****");
            s_autom.getAF().printAutomate();
        }


    }
}
