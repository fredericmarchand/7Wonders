package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourceChoice;
import Tokens.Resources;

public class Excavation extends Structure {

	public static final int ExcavationID = 6;
	
	public Excavation()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), ExcavationID, "Excavation", BROWN_CARD, 1);
		effects.add(new ResourceChoice(0, 1, 0, 1, 0, 0, 0, true));
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
