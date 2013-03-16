package Player;

import WonderBoards.WonderBoard;
import Structures.Structure;
import java.util.ArrayList;
import Tokens.*;
import java.util.Random;

public class AIPlayer extends Player {

	
	public AIPlayer(){
		super();
		isAI = true;
	}
	
	public void pickCard(ArrayList<Structure> disca)
	{
		getCards().remove(0);
		getResources().addCoins(3);
		//chooseCard(0);
		//discard(disca);
		int l = 0;
		//for ( Structure s: getCards() ) 
	//	{
//		 	System.out.println(l++ + s.getName());
//		}
//		System.out.println("");
		/*int chosenIndex = -1;
		for (Structure s : getCards())
		{
			chosenIndex = getCards().indexOf(s);
			break;				
		}
		if (chosenIndex != -1)
		{
			chooseCard(chosenIndex);
			discard(disca);
		}*/
	}
	

}
