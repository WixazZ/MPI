package menu;

import automaton.Automaton;
import determination.Determinise;
import standard.Standard;
import minimisation.*;

import java.util.Scanner;

import static minimisation.Minimisation.minimisation;
import static reconnaissance.Reco.read;

public class Trace {
    public static void affiche(int num, Automaton autom){


        System.out.println("\n*************************** AUTOMATE NUMERO "+num+" ***************************\n\n");
        System.out.println("***** Affichage de l'automate *****\n");
        autom.printAutomate();
        boolean synchrone = autom.is_synchrone(autom);
        if(synchrone){
            autom = affichage_standard(autom);
        }
        Determinise deter = new Determinise(autom);
        deter.isDeterministe();
        deter.determiniser();
        autom = deter.getAFD();




        if(synchrone){
            System.out.println("\n***** L'automate est synchrone *****\n");
            System.out.println("L'automate est synchrone nous n'avons donc pas besoin de faire d'elimination epsilon");

            if(deter.getisDeterministe()){
                System.out.println("L'automate est déjà Determiniser");
            }
        }else{
            System.out.println("\n***** L'automate est asynchrone *****\n");
            System.out.println("L'automate est asynchrone nous allons donc faire l'elimination des epsilon");
            System.out.println("\n***** Determination et suppression epsilon *****\n");

            if(!deter.getisDeterministe()) {
                System.out.println("L'automate est déjà Determiniser mais nous allons faire une elimination epsilon");
            }
        }



        System.out.println("***** Determinisation et Complétion *****");

        boolean iscomplet = autom.isComplet();
        if(!iscomplet){
            System.out.println("L'automate n'est pas complet");
        }

        if(iscomplet){
            System.out.println("L'automate est déjà complet");
        }else{
            System.out.println("Voici l'automate complet\n");
        }
        autom.completer();
        autom.printAutomate();
        if(synchrone){
            autom = affichage_standard(autom);
        }



        System.out.println("***** Minimisation *****");
        Automaton verif = minimisation(autom);
        if(verif == autom){
            System.out.println("\nL'automate est déja minimisé");
        }else {
            autom = verif;
            System.out.println("\nVoici L'automate minimisé\n");
            autom.printAutomate();
        }
        if(synchrone){
            autom = affichage_standard(autom);
        }

        System.out.println("***** Complémentarisation *****");
        System.out.println("\nVoici L'automate complémentaire\n");
        autom.complementaire();
        autom.printAutomate();


        System.out.println("Voulez vous essayer de reconnaitre des mots ? 1 = oui, 2=non");
        Scanner choix = new Scanner(System.in);
        boolean retest = true;
        int choice = choix.nextInt();
        while(choice!=1 && choice!=2){
            System.out.println("\nVous n'avez pas rentré un nombre valide\n");
            System.out.println("Voulez vous essayer de reconnaitre des mots ? 1 = oui, 2=non");
            choix = new Scanner(System.in);
            choice = choix.nextInt();
        }
        while(retest){
            if(choice == 1){
                System.out.println("Rentrez un mot:");
                Scanner s = new Scanner(System.in);
                String sc = s.nextLine();
                if(read(sc, autom)){
                    System.out.println("Le mot est reconu");
                }else{
                    System.out.println("Le mot n'est pas reconu");
                }
            }
            System.out.println("Voulez vous essayer de reconnaitre un autre mot ? 1 = oui, 2=non");
            choix = new Scanner(System.in);
            choice = choix.nextInt();
            while(choice!=1 && choice!=2){
                System.out.println("\nVous n'avez pas rentré un nombre valide\n");
                System.out.println("Voulez vous essayer de reconnaitre des mots ? 1 = oui, 2=non");
                choix = new Scanner(System.in);
                choice = choix.nextInt();
            }
            if(choice == 2){
                retest =false;
            }
        }


    }
    public static Automaton affichage_standard(Automaton autom){
        Standard s_autom = new Standard(autom);
        s_autom.isStandard();
        if(!s_autom.getIsStandard()){


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
                System.out.println("***** Voici l'automate standard *****");
                s_autom.getAF().printAutomate();
                s_autom.standardiser();
            }
        } else {
            System.out.println("\nVous ne pouvez pas standardiser votre automate car il est déjà standard\n");
        }
        return s_autom.getAF();

    }
}