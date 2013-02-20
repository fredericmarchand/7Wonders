package Structures;

import Resources.Resources;

public class GuardTower extends Structure {

	public static final int GuardTowerID = 0x12;
	
	public GuardTower()
	{
		super(new Resources(0, 0, 0, 1, 0, 0, 0, 0), GuardTowerID, "Guard Tower", RED_CARD, 1);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
