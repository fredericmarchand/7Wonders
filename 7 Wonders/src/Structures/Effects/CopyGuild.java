package Structures.Effects;

import java.util.ArrayList;
import Player.Player;
import Structures.Structure;

public class CopyGuild extends SpecialEffect{
	public static final int CopyGuildID = 0x0C;
	
	public CopyGuild()
	{
		super(CopyGuildID, false, NO_RELOAD, END_OF_GAME);
	}
	
	public boolean copyGuildStructure(Player left, Player right, Player p, int index)
	{	
		ArrayList<Structure> guildList = new ArrayList<Structure>();
		if ( !usedUp )
		{
			guildList.addAll(left.getWonderBoard().getPurpleCards());
			guildList.addAll(right.getWonderBoard().getPurpleCards());
			p.assignCards(guildList);
			p.chooseCard(index);
			p.getWonderBoard().buildStructure(p.getChosenCard());
			usedUp = true;
			return true;
		}
		return false;
	}
}
