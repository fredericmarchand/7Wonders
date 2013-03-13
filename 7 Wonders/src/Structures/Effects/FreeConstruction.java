package Structures.Effects;
import Player.Player;

public class FreeConstruction extends SpecialEffect{

	public static final int FreeConstructionID = 0x0F;
	
	public FreeConstruction()
	{
		super(FreeConstructionID, false, RELOAD_EVERY_AGE, START_OF_TURN);
	}
	
	public void buildForFree(Player p)
	{
		if ( !usedUp )
			p.getWonderBoard().buildStructure(p.getChosenCard());
		usedUp = true;
	}
	
}
