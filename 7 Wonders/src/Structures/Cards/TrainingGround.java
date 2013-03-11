package Structures.Cards;

import Structures.Structure;
import Tokens.Resources;

public class TrainingGround extends Structure {

	public static final int TrainingGroundID = 45;
	
	public TrainingGround()
	{
		super(new Resources(2, 0, 1, 0, 0, 0, 0, 0), TrainingGroundID, "Training Ground", RED_CARD, 2);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
