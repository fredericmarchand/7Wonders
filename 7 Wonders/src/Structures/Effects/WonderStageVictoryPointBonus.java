package Structures.Effects;

import Player.Player;
import WonderBoards.WonderBoardStage;

public class WonderStageVictoryPointBonus extends SpecialEffect {
	
	public static final int WonderStageVictoryPointBonusID = 0x0B;
	private int amountOfPoints;
	private boolean includeNeighbors;
	
	public WonderStageVictoryPointBonus(int vPoints, boolean neighbors)
	{
		super(WonderStageVictoryPointBonusID, false, NO_RELOAD, END_OF_GAME);
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

	
	public int amountOfPoints()
	{
		return amountOfPoints();
	}
	
	public int includeNeighbors()
	{
		return includeNeighbors ? 1 : 0;
	}
	
	
}
