package Structures;

import Resources.Resources;

public class Sawmill extends Structure {

	public static final int SawmillID = 0x3D;
	
	public Sawmill()
	{
		super(new Resources(0, 0, 0, 0, 0, 0, 0, 1), SawmillID, "Sawmill", BROWN_CARD, 2);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
