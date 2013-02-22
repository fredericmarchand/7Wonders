package Structures.Effects;

public class CardVictoryPointBonus extends SpecialEffects {

	public static final int CardVictoryPointBonusID = 0x09;
	private int amountOfPoints;
	private boolean includeNeighbors;
	
	public CardVictoryPointBonus(int vPoints, boolean neighbors)
	{
		super(CardVictoryPointBonusID, false, NO_RELOAD);
		amountOfPoints = vPoints;
		includeNeighbors = neighbors;
	}
	
}
