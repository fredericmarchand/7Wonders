package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;
import WonderBoards.WonderBoard;

public class Circus extends Structure {

	public static final int CircusID = 47;
	
	public Circus()
	{
		super(new Resources(1, 3, 0, 0, 0, 0, 0, 0), CircusID, "Circus", RED_CARD, 3);
	}

	public boolean canBuildForFree(WonderBoard wb)
	{
		return wb.containsCard(TrainingGround.TrainingGroundID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
