package Complementaire;
import java.util.*;
import automaton;


public class Comp {

	
	if (isComplet(Automaton) == false) {
		return Completer(Automaton);
		}
		
	for (int i=0;i<numberTransition;i++) {
		if(Etat[i]=="alphabet")
			Etat[i].indexOut=false;
		else
			Etat[i].indexOut=true;
	}
	
	
	public void printComp() {
	System.out.println("*********************************************");
	System.out.println("Init state : ")
	for (int n=0;n<numberState;n++) {
		System.out.println(initState[n]);
	}
	
	System.out.println("Finish state : ");
	for (int u=0;u<numberState;u++) {
		System.out.println(finishState[u]);
	}
	
	}
}