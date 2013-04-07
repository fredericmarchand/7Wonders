package Structures.Effects;

import Player.Player;
import Tokens.Resources;

public class ResourceChoice extends SpecialEffect {

	public  static final int ResourceChoiceID = 0x01;
	
	private Resources possible;
	private boolean purchaseable;
	
	public ResourceChoice(int ore, int stone, int wood, int clay, int glass, int loom, int papyrus, boolean bought)
	{
		super(ResourceChoiceID, true, RELOAD_EVERY_TURN, START_OF_TURN);
		possible = new Resources(ore, stone, wood, clay, glass, loom, papyrus, 0);
		purchaseable = bought;
	}
	
	public boolean chooseResource(int id, Player p)
	{
		if ( usedUp ) return false;
		switch ( id )
		{
			case Resources.ORE_ID:
				if ( possible.getOre() > 0 )
				{
					if ( purchaseable )
						p.addExtraResources(new Resources(1, 0, 0, 0, 0, 0, 0, 0));
					else
						p.addUnvResources(new Resources(1, 0, 0, 0, 0, 0, 0, 0));
					return true;
				}
				break;
			case Resources.STONE_ID:
				if ( possible.getStone() > 0 )
				{
					if ( purchaseable )
						p.addExtraResources(new Resources(0, 1, 0, 0, 0, 0, 0, 0));
					else
						p.addUnvResources(new Resources(0, 1, 0, 0, 0, 0, 0, 0));
					return true;
				}
				break;
			case Resources.WOOD_ID:
				if ( possible.getWood() > 0 )
				{
					if ( purchaseable )
						p.addExtraResources(new Resources(0, 0, 1, 0, 0, 0, 0, 0));
					else
						p.addUnvResources(new Resources(0, 0, 1, 0, 0, 0, 0, 0));
					return true;
				}
				break;
			case Resources.CLAY_ID:
				if ( possible.getClay() > 0 )
				{
					if ( purchaseable )
						p.addExtraResources(new Resources(0, 0, 0, 1, 0, 0, 0, 0));
					else
						p.addUnvResources(new Resources(0, 0, 0, 1, 0, 0, 0, 0));
					return true;
				}
				break;
			case Resources.GLASS_ID:
				if ( possible.getGlass() > 0 )
				{
					if ( purchaseable )
						p.addExtraResources(new Resources(0, 0, 0, 0, 1, 0, 0, 0));
					else
						p.addUnvResources(new Resources(0, 0, 0, 0, 1, 0, 0, 0));
					return true;
				}
				break;
			case Resources.LOOM_ID:
				if ( possible.getLoom() > 0 )
				{
					if ( purchaseable )
						p.addExtraResources(new Resources(0, 0, 0, 0, 0, 1, 0, 0));
					else
						p.addUnvResources(new Resources(0, 0, 0, 0, 0, 1, 0, 0));
					return true;
				}
				break;
			case Resources.PAPYRUS_ID:
				if ( possible.getPapyrus() > 0 )
				{
					if ( purchaseable )
						p.addExtraResources(new Resources(0, 0, 0, 0, 0, 0, 1, 0));
					else
						p.addUnvResources(new Resources(0, 0, 0, 0, 0, 0, 1, 0));
					return true;
				}
				break;
				default: return false;
		}
		return false;
	}
	
	public Resources getPossibilities()
	{
		return possible;
	}

	public boolean canBeBought()
	{
		return purchaseable;
	}
	
	//dont call the following functions
	public void setPossibilities(Resources r)
	{
		possible = r;
	}
	
	public void setBuyable(int i)
	{
		if ( i == 1 )
			purchaseable = true;
		else purchaseable = false;
	}
	
	public static void main(String args[])
	{
		ResourceChoice c = new ResourceChoice(1, 0, 0, 1, 0, 0, 0, true);
		System.out.println(c.getPossibilities().toString());
	}
	
}
