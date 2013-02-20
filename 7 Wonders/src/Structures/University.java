package Structures;

import Resources.Resources;

public class University extends Structure {

	public static final int UniversityID = 0x6C;
	
	public University()
	{
		super(new Resources(0, 0, 2, 0, 1, 0, 1, 0), UniversityID, "University", GREEN_CARD, 3);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
