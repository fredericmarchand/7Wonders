package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ShieldBonus;
import Tokens.Resources;

public class Barracks extends Structure {

	public static final int BarrackID = 42;
	
	public Barracks()
	{
		super(new Resources(1, 0, 0, 0, 0, 0, 0, 0), BarrackID, "Barracks", RED_CARD, 1);
		effects.add(new ShieldBonus(1));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
