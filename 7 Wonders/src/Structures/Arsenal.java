package Structures;

import Resources.Resources;

public class Arsenal extends Structure {

	public static final int ArsenalID = 0x1B;
	
	public Arsenal()
	{
		super(new Resources(1, 0, 2, 0, 0, 1, 0, 0), ArsenalID, "Arsenal", RED_CARD, 3);
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
