package Structures.Cards;

import Resources.Resources;
import Structures.Structure;
import WonderBoards.WonderBoard;

public class Circus extends Structure {

	public static final int CircusID = 0x18;
	
	public Circus()
	{
		super(new Resources(1, 3, 0, 0, 0, 0, 0, 0), CircusID, "Circus", RED_CARD, 3);
	}

	public boolean CanBuildForFree(WonderBoard wb)
	{
		return wb.ContainsCard(TrainingGround.TrainingGroundID);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
