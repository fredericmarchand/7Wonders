package Structures.Effects;

import Player.Player;

public class ShieldBonus extends SpecialEffect {

	public static final int ShieldBonusID = 0x06;
	private int amountOfShields;
	
	public ShieldBonus(int shields)
	{
		super(ShieldBonusID, false, NO_RELOAD, END_OF_TURN);
		amountOfShields = shields;
	}
	
	public void acquireShields(Player p)
	{
		if ( !usedUp )
			p.addShields(amountOfShields);
		usedUp = true;
	}
	
}
