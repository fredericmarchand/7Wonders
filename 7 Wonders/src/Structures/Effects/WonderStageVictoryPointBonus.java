package Structures.Effects;

import Player.Player;
import WonderBoards.WonderBoardStage;

public class WonderStageVictoryPointBonus extends SpecialEffects {
	
	public static final int WonderStageCoinBonusID = 0x08;
	private int amountOfPoints;
	private boolean includeNeighbors;
	
	public WonderStageVictoryPointBonus(int vPoints, boolean neighbors)
	{
		super(WonderStageCoinBonusID, false, NO_RELOAD);
		amountOfPoints = vPoints;
		includeNeighbors = neighbors;
	}
	
	public void acquirePoints(Player you, Player rightNeighbor, Player leftNeighbor)
	{
		int count = 0;
		if ( includeNeighbors )
		{
			if ( !usedUp )
			{
				for ( WonderBoardStage s: you.getWonderBoard().getStages() )
				{
					if ( s.isBuilt() ) ++count;
				}
				for ( WonderBoardStage s: rightNeighbor.getWonderBoard().getStages() )
				{
					if ( s.isBuilt() ) ++count;
				}
				for ( WonderBoardStage s: leftNeighbor.getWonderBoard().getStages() )
				{
					if ( s.isBuilt() ) ++count;
				}
				you.addVictoryPoints(amountOfPoints * count);
			}
			usedUp = true;
		}
		else
		{
			if ( !usedUp )
			{
				for ( WonderBoardStage s: you.getWonderBoard().getStages() )
				{
					if ( s.isBuilt() ) ++count;
				}
				you.addVictoryPoints(amountOfPoints * count);
			}
			usedUp = true;
		}
	}

	
}
