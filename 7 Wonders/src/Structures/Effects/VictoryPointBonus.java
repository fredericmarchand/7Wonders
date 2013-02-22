package Structures.Effects;

import Player.Player;

public class VictoryPointBonus extends SpecialEffects {

	public static final int VictoryPointBonusID = 0x05;
	private int amountOfCoins;
	
	public VictoryPointBonus(int coins)
	{
		super(VictoryPointBonusID, true, NO_RELOAD);
		amountOfCoins = coins;
	}
	
	public void acquireVictoryPoints(Player p)
	{
		if ( !usedUp )
			p.addVictoryPoints(amountOfCoins);
		usedUp = true;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
