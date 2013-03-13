package Structures.Effects;
import Player.Player;

public class CopyGuild extends SpecialEffect{
	public static final int CopyGuildID = 0x11;
	
	public CopyGuild()
	{
		super(CopyGuildID, false, NO_RELOAD, END_OF_GAME);
	}
	
	public boolean copyGuildStructure()
	{
		return false;
	}
}
