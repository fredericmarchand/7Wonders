package Structures.Effects;

import Structures.Structure;
import Player.Player;

public class CardCoinBonus extends SpecialEffects {

	public static final int CardBonusID = 0x04;
	
	private int amountOfCoins;
	private int cardColor;
	private boolean includeNeighbors;
	
	public CardCoinBonus(int coins, int color, boolean neighbors)
	{
		super(CardBonusID, false, NO_RELOAD, END_OF_TURN);
		amountOfCoins = coins;
		cardColor = color;
		includeNeighbors = neighbors;
	}
	
	public void acquireCoins(Player you, Player rightNeighbor, Player leftNeighbor)
	{
		if ( includeNeighbors )
		{
			if ( !usedUp )
			{
				switch ( cardColor )
				{
					case Structure.RED_CARD:
						you.getResources().addCoins(amountOfCoins * (you.getWonderBoard().getRedCardAmount() + rightNeighbor.getWonderBoard().getRedCardAmount() + leftNeighbor.getWonderBoard().getRedCardAmount()));
						break;
					case Structure.BLUE_CARD:
						you.getResources().addCoins(amountOfCoins * (you.getWonderBoard().getBlueCardAmount() + rightNeighbor.getWonderBoard().getBlueCardAmount() + leftNeighbor.getWonderBoard().getBlueCardAmount()));
						break;
					case Structure.BROWN_CARD:
						you.getResources().addCoins(amountOfCoins * (you.getWonderBoard().getBrownCardAmount() + rightNeighbor.getWonderBoard().getBrownCardAmount() + leftNeighbor.getWonderBoard().getBrownCardAmount()));
						break;
					case Structure.GREY_CARD:
						you.getResources().addCoins(amountOfCoins * (you.getWonderBoard().getGreyCardAmount() + rightNeighbor.getWonderBoard().getGreyCardAmount() + leftNeighbor.getWonderBoard().getGreyCardAmount()));
						break;
					case Structure.GREEN_CARD:
						you.getResources().addCoins(amountOfCoins * (you.getWonderBoard().getGreenCardAmount() + rightNeighbor.getWonderBoard().getGreenCardAmount() + leftNeighbor.getWonderBoard().getGreenCardAmount()));
						break;
					case Structure.YELLOW_CARD:
						you.getResources().addCoins(amountOfCoins * (you.getWonderBoard().getYellowCardAmount() + rightNeighbor.getWonderBoard().getYellowCardAmount() + leftNeighbor.getWonderBoard().getYellowCardAmount()));
						break;
					case Structure.PURPLE_CARD:
						you.getResources().addCoins(amountOfCoins * (you.getWonderBoard().getPurpleCardAmount() + rightNeighbor.getWonderBoard().getPurpleCardAmount() + leftNeighbor.getWonderBoard().getPurpleCardAmount()));
						break;
				}
			}
			usedUp = true;
		}
		else
		{
			if ( !usedUp )
			{
				switch ( cardColor )
				{
					case Structure.RED_CARD:
						you.getResources().addCoins(amountOfCoins * you.getWonderBoard().getRedCardAmount());
						break;
					case Structure.BLUE_CARD:
						you.getResources().addCoins(amountOfCoins * you.getWonderBoard().getBlueCardAmount());
						break;
					case Structure.BROWN_CARD:
						you.getResources().addCoins(amountOfCoins * you.getWonderBoard().getBrownCardAmount());
						break;
					case Structure.GREY_CARD:
						you.getResources().addCoins(amountOfCoins * you.getWonderBoard().getGreyCardAmount());
						break;
					case Structure.GREEN_CARD:
						you.getResources().addCoins(amountOfCoins * you.getWonderBoard().getGreenCardAmount());
						break;
					case Structure.YELLOW_CARD:
						you.getResources().addCoins(amountOfCoins * you.getWonderBoard().getYellowCardAmount());
						break;
					case Structure.PURPLE_CARD:
						you.getResources().addCoins(amountOfCoins * you.getWonderBoard().getPurpleCardAmount());
						break;
				}
			}
			usedUp = true;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
