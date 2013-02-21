package Structures.Effects;

import Resources.Resources;

public class ResourceChoice extends SpecialEffects {

	public  static final int ResourceChoiceID = 0x01;
	
	private Resources possible;
	private Resources choice;
	private boolean purchaseable;
	
	public ResourceChoice(int ore, int stone, int wood, int clay, int glass, int loom, int papyrus, boolean bought)
	{
		super(ResourceChoiceID, true, RELOAD_EVERY_TURN);
		possible = new Resources(ore, stone, wood, clay, glass, loom, papyrus, 0);
		choice = null;
		purchaseable = bought;
	}
	
	public boolean ChooseResource(int id)
	{
		if ( usedUp ) return false;
		switch ( id )
		{
			case Resources.ORE_ID:
				if ( possible.GetOre() > 0 )
				{
					choice = new Resources(1, 0, 0, 0, 0, 0, 0, 0);
					return true;
				}
				break;
			case Resources.STONE_ID:
				if ( possible.GetStone() > 0 )
				{
					choice = new Resources(0, 1, 0, 0, 0, 0, 0, 0);
					return true;
				}
				break;
			case Resources.WOOD_ID:
				if ( possible.GetWood() > 0 )
				{
					choice = new Resources(0, 0, 1, 0, 0, 0, 0, 0);
					return true;
				}
				break;
			case Resources.CLAY_ID:
				if ( possible.GetClay() > 0 )
				{
					choice = new Resources(0, 0, 0, 1, 0, 0, 0, 0);
					return true;
				}
				break;
			case Resources.GLASS_ID:
				if ( possible.GetGlass() > 0 )
				{
					choice = new Resources(0, 0, 0, 0, 1, 0, 0, 0);
					return true;
				}
				break;
			case Resources.LOOM_ID:
				if ( possible.GetLoom() > 0 )
				{
					choice = new Resources(0, 0, 0, 0, 0, 1, 0, 0);
					return true;
				}
				break;
			case Resources.PAPYRUS_ID:
				if ( possible.GetPapyrus() > 0 )
				{
					choice = new Resources(0, 0, 0, 0, 0, 0, 1, 0);
					return true;
				}
				break;
				default: return false;
		}
		return false;
	}
	
	public Resources GetPossibilities()
	{
		return possible;
	}
	
	public Resources GetChoice()
	{
		return choice;
	}

	public boolean CanBeBought()
	{
		return purchaseable;
	}
	
	
	public static void main(String args[])
	{
		ResourceChoice c = new ResourceChoice(1, 0, 0, 1, 0, 0, 0, true);
		System.out.println(c.GetPossibilities().toString());
		if ( c.ChooseResource(4) )
			System.out.println("\n" + c.GetChoice().toString());
		else System.out.println("\nInvalid");
	}
	
}
