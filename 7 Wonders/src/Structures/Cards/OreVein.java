package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import Structures.Effects.*;

public class OreVein extends Structure {
	
	public static final int OreVeinID = 4;
	
	public OreVein()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 0), OreVeinID, "Ore Vein", BROWN_CARD, 1);
		effects.add(new ResourcesBonus(1, 0, 0, 0, 0, 0, 0));
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
