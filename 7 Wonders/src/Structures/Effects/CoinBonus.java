package Structures.Effects;

import Tokens.Resources;

public class CoinBonus extends SpecialEffect {

	public static final int CoinBonusID = 0x03;
	private int amountOfCoins;
	
	public CoinBonus(int coins)
	{
		super(CoinBonusID, false, NO_RELOAD, END_OF_TURN);
		amountOfCoins = coins;
	}
	
	public void acquireCoins(Resources funds)
	{
		if ( !usedUp )
			funds.addCoins(amountOfCoins);
		usedUp = true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
