package Structures.Effects;

import Player.Player;

public class PlayLastCard extends SpecialEffect{
	
	public static final int PlayLastCardID = 0x0D;
	
	public PlayLastCard()
	{
		super(PlayLastCardID, false, RELOAD_EVERY_AGE, IN_TURN);
	}
	
	public boolean canBuildLastCard()
	{
		//int success = 0;
		if ( !usedUp )
		{
			
			return true;
			/*
			if ( p.getCards().size() == 2) 
			{
				p.chooseCard(0);
				success += p.buildStructure();
				p.chooseCard(0);
				success += p.buildStructure();
				if (success == 4)
					return true;
			}
			*/
		}
		return false;
	}

}
