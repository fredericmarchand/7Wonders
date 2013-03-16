package Structures.Effects;

import Player.Player;
import WonderBoards.WonderBoardStage;

public class WonderStageCoinBonus extends SpecialEffect {

	public static final int WonderStageCoinBonusID = 0x08;
	private int amountOfCoins;
	
	public WonderStageCoinBonus(int coins)
	{
		super(WonderStageCoinBonusID, false, NO_RELOAD, END_OF_TURN);
		amountOfCoins = coins;
	}
	
	public void acquireCoins(Player p)
	{
		int count = 0;
		if ( !usedUp )
		{
			for ( WonderBoardStage s: p.getWonderBoard().getStages() )
			{
				if ( s.isBuilt() ) ++count;
			}
			p.getOwnedResources().addCoins(amountOfCoins * count);
		}	
		usedUp = true;
	}
	
}
