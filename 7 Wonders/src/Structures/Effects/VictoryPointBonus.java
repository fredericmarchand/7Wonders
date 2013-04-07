package Structures.Effects;

import Player.Player;

public class VictoryPointBonus extends SpecialEffect {

	public static final int VictoryPointBonusID = 0x05;
	private int amountOfPoints;
	
	public VictoryPointBonus(int points)
	{
		super(VictoryPointBonusID, false, NO_RELOAD, END_OF_GAME);
		amountOfPoints = points;
	}
	
	public void acquireVictoryPoints(Player p)
	{
		if ( !usedUp )
			p.addVictoryPoints(amountOfPoints);
		usedUp = true;
	}
	
	public int amountOfPoints()
	{
		return amountOfPoints;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
