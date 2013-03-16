package Player;

import Structures.Structure;
import java.util.ArrayList;

public class AIPlayer extends Player {

	
	public AIPlayer(){
		super();
	}
	
	public void pickCard(ArrayList<Structure> disca)
	{
		chooseCard(0);
		discard(disca);
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
