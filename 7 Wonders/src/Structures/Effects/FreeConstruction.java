package Structures.Effects;
import Player.Player;
import Structures.Structure;

public class FreeConstruction extends SpecialEffect{

	public static final int FreeConstructionID = 0x0F;
	
	public FreeConstruction()
	{
		super(FreeConstructionID, false, RELOAD_EVERY_AGE, START_OF_TURN);
	}
	
	public boolean canBuildForFree()
	{
		if ( !usedUp )
			return true;
		return false;
	}
	
	public void build(Player p, Structure s)
	{
		if ( !usedUp )
		{
			usedUp = true;
			p.getWonderBoard().buildStructure(s);
			for ( SpecialEffect se: s.getEffects() )
			{
				p.activateBuildEffect(se);
			}
		}
	}
	
}
