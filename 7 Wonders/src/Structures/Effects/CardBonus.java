package Structures.Effects;

import Resources.Resources;
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
	
	public void AcquireCoins(Player you, Player rightNeighbor, Player leftNeighbor)
	{
		if ( !usedUp )
		{
			switch ( cardColor )
			{
				case Structure.RED_CARD:
					you.GetResources().AddCoins(amountOfCoins * (you.GetWonderBoard().GetRedCardAmount() + rightNeighbor.GetWonderBoard().GetRedCardAmount() + leftNeighbor.GetWonderBoard().GetRedCardAmount()));
					break;
				case Structure.BLUE_CARD:
					you.GetResources().AddCoins(amountOfCoins * (you.GetWonderBoard().GetBlueCardAmount() + rightNeighbor.GetWonderBoard().GetBlueCardAmount() + leftNeighbor.GetWonderBoard().GetBlueCardAmount()));
					break;
				case Structure.BROWN_CARD:
					you.GetResources().AddCoins(amountOfCoins * (you.GetWonderBoard().GetBrownCardAmount() + rightNeighbor.GetWonderBoard().GetBrownCardAmount() + leftNeighbor.GetWonderBoard().GetBrownCardAmount()));
					break;
				case Structure.GREY_CARD:
					you.GetResources().AddCoins(amountOfCoins * (you.GetWonderBoard().GetGreyCardAmount() + rightNeighbor.GetWonderBoard().GetGreyCardAmount() + leftNeighbor.GetWonderBoard().GetGreyCardAmount()));
					break;
				case Structure.GREEN_CARD:
					you.GetResources().AddCoins(amountOfCoins * (you.GetWonderBoard().GetGreenCardAmount() + rightNeighbor.GetWonderBoard().GetGreenCardAmount() + leftNeighbor.GetWonderBoard().GetGreenCardAmount()));
					break;
				case Structure.YELLOW_CARD:
					you.GetResources().AddCoins(amountOfCoins * (you.GetWonderBoard().GetYellowCardAmount() + rightNeighbor.GetWonderBoard().GetYellowCardAmount() + leftNeighbor.GetWonderBoard().GetYellowCardAmount()));
					break;
				case Structure.PURPLE_CARD:
					you.GetResources().AddCoins(amountOfCoins * (you.GetWonderBoard().GetPurpleCardAmount() + rightNeighbor.GetWonderBoard().GetPurpleCardAmount() + leftNeighbor.GetWonderBoard().GetPurpleCardAmount()));
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
