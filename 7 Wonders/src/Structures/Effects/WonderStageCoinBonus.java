package Structures.Effects;

import Player.Player;
import WonderBoards.WonderBoardStage;

public class WonderStageCoinBonus extends SpecialEffects {

	public static final int WonderStageCoinBonusID = 0x08;
	private int amountOfCoins;
	
	public WonderStageCoinBonus(int coins)
	{
		super(WonderStageCoinBonusID, false, NO_RELOAD);
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
			p.getResources().addCoins(amountOfCoins * count);
		}	
		usedUp = true;
	}
	
}
