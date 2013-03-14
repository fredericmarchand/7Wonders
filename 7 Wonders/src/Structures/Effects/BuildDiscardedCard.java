package Structures.Effects;

import java.util.ArrayList;
import Player.Player;
import Structures.Structure;

public class BuildDiscardedCard extends SpecialEffect{
	
	public static final int BuildDiscardedCardID = 0x0E;
	
	public BuildDiscardedCard(){
		
		super(BuildDiscardedCardID, false, NO_RELOAD, START_OF_TURN);
	}
	
	public void playDiscardedCard(Player p, ArrayList<Structure> s, int index)
	{
		if ( !usedUp )
		{
			p.assignCards(s);
			p.chooseCard(index);
			p.getWonderBoard().buildStructure(p.getChosenCard());
		}
		usedUp = true;
	}
	
}
