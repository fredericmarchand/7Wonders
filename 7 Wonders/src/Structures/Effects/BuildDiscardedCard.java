package Structures.Effects;

import Player.Player;

public class BuildDiscardedCard extends SpecialEffect{
	
	public static final int BuildDiscardedCardID = 0x0E;
	
	public BuildDiscardedCard(){
		
		super(BuildDiscardedCardID, false, NO_RELOAD, START_OF_TURN);
	}
	
	public void playDiscardedCard()
	{
		
	}
	
}
