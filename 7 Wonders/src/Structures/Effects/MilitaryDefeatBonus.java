package Structures.Effects;

import Player.Player;

public class MilitaryDefeatBonus extends SpecialEffects {

	public static final int MilitaryDefeatBonusID = 0x0A;
	
	public MilitaryDefeatBonus()
	{
		super(MilitaryDefeatBonusID, false, NO_RELOAD, END_OF_GAME);
	}
	
	public void acquireVictoryPoints(Player you, Player leftNeighbor, Player rightNeighbor)
	{
		if ( !usedUp )
			you.addVictoryPoints(leftNeighbor.getMilitaryVictoryTokens().getMinusOneTokens() + rightNeighbor.getMilitaryVictoryTokens().getMinusOneTokens());
		usedUp = true;
	}
	
}
