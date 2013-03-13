package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourcesBonus;
import Tokens.Resources;

public class Glassworks extends Structure {

	public static final int GlassworksID = 16;
	
	public Glassworks()
	{
		super(new Resources(), GlassworksID, "Glassworks", GREY_CARD, 1);
		effects.add(new ResourcesBonus(0, 0, 0, 0, 1, 0, 0));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
