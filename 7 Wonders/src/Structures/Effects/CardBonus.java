package Structures.Effects;

import Structures.Structure;
import Player.Player;

public class CardBonus extends SpecialEffects {

	public static final int CardBonusID = 0x04;
	
	private int amountOfCoins;
	private int cardColor;
	
	public CardBonus(int coins, int color)
	{
		super(CardBonusID, false, NO_RELOAD);
		amountOfCoins = coins;
		cardColor = color;
	}
	
	public void acquireCoins(Player you, Player rightNeighbor, Player leftNeighbor)
	{
		if ( !usedUp )
		{
			switch ( cardColor )
			{
				case Structure.RED_CARD:
					you.getResources().addCoins(amountOfCoins * (you.getWonderBoard().GetRedCardAmount() + rightNeighbor.getWonderBoard().GetRedCardAmount() + leftNeighbor.getWonderBoard().GetRedCardAmount()));
					break;
				case Structure.BLUE_CARD:
					you.getResources().addCoins(amountOfCoins * (you.getWonderBoard().GetBlueCardAmount() + rightNeighbor.getWonderBoard().GetBlueCardAmount() + leftNeighbor.getWonderBoard().GetBlueCardAmount()));
					break;
				case Structure.BROWN_CARD:
					you.getResources().addCoins(amountOfCoins * (you.getWonderBoard().GetBrownCardAmount() + rightNeighbor.getWonderBoard().GetBrownCardAmount() + leftNeighbor.getWonderBoard().GetBrownCardAmount()));
					break;
				case Structure.GREY_CARD:
					you.getResources().addCoins(amountOfCoins * (you.getWonderBoard().GetGreyCardAmount() + rightNeighbor.getWonderBoard().GetGreyCardAmount() + leftNeighbor.getWonderBoard().GetGreyCardAmount()));
					break;
				case Structure.GREEN_CARD:
					you.getResources().addCoins(amountOfCoins * (you.getWonderBoard().GetGreenCardAmount() + rightNeighbor.getWonderBoard().GetGreenCardAmount() + leftNeighbor.getWonderBoard().GetGreenCardAmount()));
					break;
				case Structure.YELLOW_CARD:
					you.getResources().addCoins(amountOfCoins * (you.getWonderBoard().GetYellowCardAmount() + rightNeighbor.getWonderBoard().GetYellowCardAmount() + leftNeighbor.getWonderBoard().GetYellowCardAmount()));
					break;
				case Structure.PURPLE_CARD:
					you.getResources().addCoins(amountOfCoins * (you.getWonderBoard().GetPurpleCardAmount() + rightNeighbor.getWonderBoard().GetPurpleCardAmount() + leftNeighbor.getWonderBoard().GetPurpleCardAmount()));
					break;
			}
		}
		usedUp = true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
