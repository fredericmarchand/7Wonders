package Structures.Cards;

import Structures.Structure;
import Structures.Effects.VictoryPointBonus;
import Tokens.Resources;

public class Baths extends Structure {

	public static final int BathsID = 29;
	
	public Baths()
	{
		super(new Resources(0, 1, 0, 0, 0, 0, 0, 0), BathsID, "Baths", BLUE_CARD, 1);
		effects.add(new VictoryPointBonus(3));
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
