package Structures.Effects;

import Resources.Resources;

public class CoinBonus extends SpecialEffects {

	public static final int CoinBonusID = 0x03;
	private int amountOfCoins;
	
	public CoinBonus(int coins)
	{
		super(CoinBonusID, false, NO_RELOAD);
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
		// TODO Auto-generated method stub
		
	}

}
