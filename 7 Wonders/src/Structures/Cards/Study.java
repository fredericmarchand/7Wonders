
package Structures.Cards;

import Structures.Structure;
import Structures.Effects.ScientificSymbolBonus;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Study extends Structure {

	public static final int StudyID = 75;
	
	public Study()
	{
		super(new Resources(0, 0, 1, 0, 0, 1, 1, 0), StudyID, "Study", GREEN_CARD, 3);
		effects.add(new ScientificSymbolBonus(2));
	}

	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(School.SchoolID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

