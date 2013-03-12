package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ResourceChoice;
import Tokens.Resources;

public class TreeFarm extends Structure {

	public static final int TreeFarmID = 5;
	
	public TreeFarm()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), TreeFarmID, "Tree Farm", BROWN_CARD, 1);
		effects.add(new ResourceChoice(0, 0, 1, 1, 0, 0, 0, true));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
