package Structures.Effects;

import Player.Player;
import Structures.Structure;

public class CardVictoryPointBonus extends SpecialEffects {

	public static final int CardVictoryPointBonusID = 0x09;
	private int amountOfPoints;
	private boolean onlyNeighbors;
	private int cardColor;
	
	public CardVictoryPointBonus(int vPoints, boolean neighbors, int color)
	{
		super(CardVictoryPointBonusID, false, NO_RELOAD, END_OF_GAME);
		amountOfPoints = vPoints;
		onlyNeighbors = neighbors;
		cardColor = color;
	}
	
	public void acquireVictoryPoints(Player you, Player leftNeighbor, Player rightNeighbor)
	{
		if ( onlyNeighbors )
		{
			if ( !usedUp )
			{
				switch ( cardColor )
				{
					case Structure.RED_CARD:
						you.addVictoryPoints(amountOfPoints * (rightNeighbor.getWonderBoard().getRedCardAmount() + leftNeighbor.getWonderBoard().getRedCardAmount()));
						break;
					case Structure.BLUE_CARD:
						you.addVictoryPoints(amountOfPoints * (rightNeighbor.getWonderBoard().getBlueCardAmount() + leftNeighbor.getWonderBoard().getBlueCardAmount()));
						break;
					case Structure.BROWN_CARD:
						you.addVictoryPoints(amountOfPoints * (rightNeighbor.getWonderBoard().getBrownCardAmount() + leftNeighbor.getWonderBoard().getBrownCardAmount()));
						break;
					case Structure.GREY_CARD:
						you.addVictoryPoints(amountOfPoints * (rightNeighbor.getWonderBoard().getGreyCardAmount() + leftNeighbor.getWonderBoard().getGreyCardAmount()));
						break;
					case Structure.GREEN_CARD:
						you.addVictoryPoints(amountOfPoints * (rightNeighbor.getWonderBoard().getGreenCardAmount() + leftNeighbor.getWonderBoard().getGreenCardAmount()));
						break;
					case Structure.YELLOW_CARD:
						you.addVictoryPoints(amountOfPoints * (rightNeighbor.getWonderBoard().getYellowCardAmount() + leftNeighbor.getWonderBoard().getYellowCardAmount()));
						break;
					case Structure.PURPLE_CARD:
						you.addVictoryPoints(amountOfPoints * (rightNeighbor.getWonderBoard().getPurpleCardAmount() + leftNeighbor.getWonderBoard().getPurpleCardAmount()));
						break;
				}
				usedUp = true;				
			}
		}
		else
		{
			if ( !usedUp )
			{
				switch ( cardColor )
				{
					case Structure.RED_CARD:
						you.addVictoryPoints(amountOfPoints * (rightNeighbor.getWonderBoard().getRedCardAmount() + leftNeighbor.getWonderBoard().getRedCardAmount()));
						break;
					case Structure.BLUE_CARD:
						you.addVictoryPoints(amountOfPoints * (rightNeighbor.getWonderBoard().getBlueCardAmount() + leftNeighbor.getWonderBoard().getBlueCardAmount()));
						break;
					case Structure.BROWN_CARD:
						you.addVictoryPoints(amountOfPoints * (rightNeighbor.getWonderBoard().getBrownCardAmount() + leftNeighbor.getWonderBoard().getBrownCardAmount()));
						break;
					case Structure.GREY_CARD:
						you.addVictoryPoints(amountOfPoints * (rightNeighbor.getWonderBoard().getGreyCardAmount() + leftNeighbor.getWonderBoard().getGreyCardAmount()));
						break;
					case Structure.GREEN_CARD:
						you.addVictoryPoints(amountOfPoints * (rightNeighbor.getWonderBoard().getGreenCardAmount() + leftNeighbor.getWonderBoard().getGreenCardAmount()));
						break;
					case Structure.YELLOW_CARD:
						you.addVictoryPoints(amountOfPoints * (rightNeighbor.getWonderBoard().getYellowCardAmount() + leftNeighbor.getWonderBoard().getYellowCardAmount()));
						break;
					case Structure.PURPLE_CARD:
						you.addVictoryPoints(amountOfPoints * (rightNeighbor.getWonderBoard().getPurpleCardAmount() + leftNeighbor.getWonderBoard().getPurpleCardAmount()));
						break;
				}
			}
			usedUp = true;
		}
	}
	
}
