package completion;
import java.io.*;

public class Completion {
	
	public void completer() {
		/*for (int i=0; i<numberTransition; i++){
			if(symtrans!=etats[i].getName())
				//on met la transition à la fin du tableau
		}*/

		numberStates++;
		etats= Arrays.copyOf(etats,numberStates);
		etats[numberStates-1]=new Etat("P",false,false,numberAlphabet);

		for (int j=0;j<numberTransition;j++){
			etats.out=Arrays.copyOf(etats.out,numberStates+1);
			etats[numberStates-1]=new Etat("P",false,false,numberAlphabet);
	}


	for(int i = 0; i < numberState; i++){
	    for(int j = 0; j < numberAlphabet; j++){
	        boolean complet = false;
	        int k = 0;
	        while(!complet && k < numberAlphabet){
	            if(etats[i].getOut[k].getWord == alphabet[j]){
	                complet = true;
	            }else{
	                k++;
	            }
	        }
	        
	        if(!complet){
	            etats[i].addTransition(new Transition(etats[i], alphabet[j], etats[numberState - 1], false);
	            numberTransition++;
	        }
	    }
	}

	}


	public bool isComplet(){
		if(numberAlphabet*numberStates<numberTransition)
			System.out.println("ERREUR du is_complet");
			
		if (numberAlphabet*numberStates==numberTransition) {
			System.out.println("L'automate est complet car tout les états ont une transition");
			return true;}
		else {
			System.out.println("L'automate n'est pas complet car il y a plus de transition que d'état");
			return false;}
	}
}
