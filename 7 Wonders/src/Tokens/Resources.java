package Tokens;

import java.util.ArrayList;

import Player.Player;
import Structures.Effects.TradingPerks;

public class Resources {

	public static final int ORE_ID = 1;
	public static final int STONE_ID = 2;
	public static final int WOOD_ID = 3;
	public static final int CLAY_ID = 4;
	public static final int GLASS_ID = 5;
	public static final int LOOM_ID = 6;
	public static final int PAPYRUS_ID = 7;
	public static final int COINS_ID = 8;
	
	private int ore, stone, wood, clay, glass, loom, papyrus, coins;
	
	public Resources()
	{
		ore = 0;
		stone = 0;
		wood = 0;
		clay = 0;
		glass = 0;
		loom = 0;
		papyrus = 0;
		coins = 0;
	}
	
	public Resources(int ore, int stone, int wood, int clay, int glass, int loom, int papyrus, int coins)
	{
		this.ore = ore;
		this.stone = stone;
		this.wood = wood;
		this.clay = clay;
		this.glass = glass;
		this.loom = loom;
		this.papyrus = papyrus;
		this.coins = coins;
	}
	
	public boolean canAfford(Resources funds)
	{
		//If any of these cases are not satisfied return false;
		if ( funds.clay < clay )
			return false;
		if ( funds.glass < glass )
			return false;
		if ( funds.loom < loom )
			return false;
		if ( funds.ore < ore )
			return false;
		if ( funds.papyrus < papyrus )
			return false;
		if ( funds.stone < stone )
			return false;
		if ( funds.wood < wood )
			return false;
		if ( funds.coins < coins )
			return false;
		
		//otherwise return true;
		return true;
	}
	
	public boolean hasRequiredResources(Resources required)
	{
		if ( required.clay > clay )
			return false;
		if ( required.glass > glass )
			return false;
		if ( required.loom > loom )
			return false;
		if ( required.ore > ore )
			return false;
		if ( required.papyrus > papyrus )
			return false;
		if ( required.stone > stone )
			return false;
		if ( required.wood > wood )
			return false;

		//otherwise return true;
		return true;
	}
	
	//returns the total amount of resources of every type
	public int getCount()
	{
		return (ore + stone + wood + clay + glass + loom + papyrus);
	}
	
	public int getPrimaryCount()
	{
		return (ore + stone + wood + clay);
	}
	
	public int getManufacturedCount()
	{
		return (glass + loom + papyrus);
	}
	
	public int getCoins()
	{
		return coins;
	}
	
	public int getOre()
	{
		return ore;
	}
	
	public int getStone()
	{
		return stone;
	}
	
	public int getWood()
	{
		return wood;
	}
	
	public int getClay()
	{
		return clay;
	}
	
	public int getGlass()
	{
		return glass;
	}
	
	public int getLoom()
	{
		return loom;
	}
	
	public int getPapyrus()
	{
		return papyrus;
	}
	
	public void addCoins(int coins)
	{
		this.coins += coins;
	}
	
	public void addOre(int ore)
	{
		this.ore += ore;
	}
	
	public void addStone(int stone)
	{
		this.stone += stone;
	}
	
	public void addWood(int wood)
	{
		this.wood += wood;
	}
	
	public void addClay(int clay)
	{
		this.clay += clay;
	}
	
	public void addGlass(int glass)
	{
		this.glass += glass;
	}
	
	public void addLoom(int loom)
	{
		this.loom += loom;
	}
	
	public void addPapyrus(int papyrus)
	{
		this.papyrus += papyrus;
	}
	
	
	public void addResources(Resources resources)
	{
		coins += resources.coins;
		ore += resources.ore;
		stone += resources.stone;
		wood += resources.wood;
		clay += resources.clay;
		glass += resources.glass;
		loom += resources.loom;
		papyrus += resources.papyrus;
	}
	
	public static Resources addResources(Resources a, Resources b)
	{
		Resources r = new Resources();
		r.clay    = a.clay + b.clay;
		r.coins   = a.coins + b.coins;
		r.ore     = a.ore + b.ore;
		r.stone   = a.stone + b.stone;
		r.wood    = a.wood + b.wood;
		r.glass   = a.glass + b.glass;
		r.loom    = a.loom + b.loom;
		r.papyrus = a.papyrus + b.papyrus;
		return r;
	}
	
	public static Resources addResources(Resources a, Resources b, Resources c)
	{
		Resources r = new Resources();
		r.clay    = a.clay + b.clay + c.clay;
		r.coins   = a.coins + b.coins + c.coins;
		r.ore     = a.ore + b.ore + c.coins;
		r.stone   = a.stone + b.stone + c.stone;
		r.wood    = a.wood + b.wood + c.wood;
		r.glass   = a.glass + b.glass + b.glass;
		r.loom    = a.loom + b.loom + c.loom;
		r.papyrus = a.papyrus + b.papyrus + c.papyrus;
		return r;
	}
	
	public Resources findMissingResources(Resources required)
	{
		Resources r = new Resources();
		r.ore = required.ore - ore;
		if ( r.ore < 0 ) r.ore = 0;
		r.stone = required.stone - stone;
		if ( r.stone < 0 ) r.stone = 0;
		r.wood = required.wood - wood;
		if ( r.wood < 0 ) r.wood = 0;
		r.clay = required.clay - clay;
		if ( r.clay < 0 ) r.clay = 0;
		r.glass = required.glass - glass;
		if ( r.glass < 0 ) r.glass = 0;
		r.loom = required.loom - loom;
		if ( r.loom < 0 ) r.loom = 0;
		r.papyrus = required.papyrus - papyrus;
		if ( r.papyrus < 0 ) r.papyrus = 0;
		return r;
	}
	
	public ArrayList<Integer> buyResources(Player leftNeighbor, Player rightNeighbor, Resources required, TradingPerks prices, int preference)
	{
		ArrayList<Integer> neighborRevenue = new ArrayList<Integer>();
		int leftRev = 0;
		int rightRev = 0;
		//handle manufactured resources
		if ( prices.manufac() )//cheaper price
		{
			switch ( preference )
			{
				case 0://left first
					if ( required.getGlass() > 0 )
					{
						if ( leftNeighbor.getResources().getGlass() >= required.getGlass() )
						{//buy all from left
							addGlass(required.getGlass());
							leftRev += required.getGlass();
							required.addGlass(required.getGlass() * -1);
						}
						else
						{
							//buy as much as you can from left
							addGlass(leftNeighbor.getResources().getGlass());
							leftRev += leftNeighbor.getResources().getGlass();
							required.addGlass(leftNeighbor.getResources().getGlass() * -1);
							
							//and the rest from right
							addGlass(required.getGlass());
							rightRev += required.getGlass();
							required.addGlass(required.getGlass() * -1);
						}
					}
					if ( required.getLoom() > 0 )
					{
						if ( leftNeighbor.getResources().getLoom() >= required.getLoom() )
						{//buy all from left
							addLoom(required.getLoom());
							leftRev += required.getLoom();
							required.addLoom(required.getLoom() * -1);
						}
						else
						{
							//buy as much as you can from left
							addLoom(leftNeighbor.getResources().getLoom());
							leftRev += leftNeighbor.getResources().getLoom();
							required.addLoom(leftNeighbor.getResources().getLoom() * -1);
							
							//and the rest from right
							addLoom(required.getLoom());
							rightRev += required.getLoom();
							required.addLoom(required.getLoom() * -1);
						}
					}
					if ( required.getPapyrus() > 0)
					{
						if ( leftNeighbor.getResources().getPapyrus() >= required.getPapyrus() )
						{//buy all from left
							addPapyrus(required.getPapyrus());
							leftRev += required.getPapyrus();
							required.addPapyrus(required.getPapyrus() * -1);
						}
						else
						{
							//buy as much as you can from left
							addPapyrus(leftNeighbor.getResources().getPapyrus());
							leftRev += leftNeighbor.getResources().getPapyrus();
							required.addPapyrus(leftNeighbor.getResources().getPapyrus() * -1);
							
							//and the rest from right
							addPapyrus(required.getPapyrus());
							rightRev += required.getPapyrus();
							required.addPapyrus(required.getPapyrus() * -1);
						}
					}
					break;
				case 1://right first
					if ( required.getGlass() > 0 )
					{
						if ( rightNeighbor.getResources().getGlass() >= required.getGlass() )
						{//buy all from right
							addGlass(required.getGlass());
							rightRev += required.getGlass();
							required.addGlass(required.getGlass() * -1);
						}
						else
						{
							//buy as much as you can from right
							addGlass(rightNeighbor.getResources().getGlass());
							rightRev += rightNeighbor.getResources().getGlass();
							required.addGlass(rightNeighbor.getResources().getGlass() * -1);
							
							//and the rest from left
							addGlass(required.getGlass());
							leftRev += required.getGlass();
							required.addGlass(required.getGlass() * -1);
						}
					}
					if ( required.getLoom() > 0 )
					{
						if ( rightNeighbor.getResources().getLoom() >= required.getLoom() )
						{//buy all from right
							addLoom(required.getLoom());
							rightRev += required.getLoom();
							required.addLoom(required.getLoom() * -1);
						}
						else
						{
							//buy as much as you can from right
							addLoom(rightNeighbor.getResources().getLoom());
							rightRev += rightNeighbor.getResources().getLoom();
							required.addLoom(rightNeighbor.getResources().getLoom() * -1);
							
							//and the rest from left
							addLoom(required.getLoom());
							leftRev += required.getLoom();
							required.addLoom(required.getLoom() * -1);
						}
					}
					if ( required.getPapyrus() > 0)
					{
						if ( rightNeighbor.getResources().getPapyrus() >= required.getPapyrus() )
						{//buy all from right
							addPapyrus(required.getPapyrus());
							rightRev += required.getPapyrus();
							required.addPapyrus(required.getPapyrus() * -1);
						}
						else
						{
							//buy as much as you can from right
							addPapyrus(rightNeighbor.getResources().getPapyrus());
							rightRev += rightNeighbor.getResources().getPapyrus();
							required.addPapyrus(rightNeighbor.getResources().getPapyrus() * -1);
							
							//and the rest from left
							addPapyrus(required.getPapyrus());
							leftRev += required.getPapyrus();
							required.addPapyrus(required.getPapyrus() * -1);
						}
					}
					break;
			}
		}
		else//regular price
		{
			switch ( preference )
			{
				case 0://left first
					if ( required.getGlass() > 0 )
					{
						if ( leftNeighbor.getResources().getGlass() >= required.getGlass() )
						{//buy all from left
							addGlass(required.getGlass());
							leftRev += (required.getGlass() * 2);
							required.addGlass(required.getGlass() * -1);
						}
						else
						{
							//buy as much as you can from left
							addGlass(leftNeighbor.getResources().getGlass());
							leftRev += (leftNeighbor.getResources().getGlass() * 2);
							required.addGlass(leftNeighbor.getResources().getGlass() * -1);
							
							//and the rest from right
							addGlass(required.getGlass());
							rightRev += (required.getGlass() * 2);
							required.addGlass(required.getGlass() * -1);
						}
					}
					if ( required.getLoom() > 0 )
					{
						if ( leftNeighbor.getResources().getLoom() >= required.getLoom() )
						{//buy all from left
							addLoom(required.getLoom());
							leftRev += (required.getLoom() * 2);
							required.addLoom(required.getLoom() * -1);
						}
						else
						{
							//buy as much as you can from left
							addLoom(leftNeighbor.getResources().getLoom());
							leftRev += (leftNeighbor.getResources().getLoom() * 2);
							required.addLoom(leftNeighbor.getResources().getLoom() * -1);
							
							//and the rest from right
							addLoom(required.getLoom());
							rightRev += (required.getLoom() * 2);
							required.addLoom(required.getLoom() * -1);
						}
					}
					if ( required.getPapyrus() > 0)
					{
						if ( leftNeighbor.getResources().getPapyrus() >= required.getPapyrus() )
						{//buy all from left
							addPapyrus(required.getPapyrus());
							leftRev += (required.getPapyrus() * 2);
							required.addPapyrus(required.getPapyrus() * -1);
						}
						else
						{
							//buy as much as you can from left
							addPapyrus(leftNeighbor.getResources().getPapyrus());
							leftRev += (leftNeighbor.getResources().getPapyrus() * 2);
							required.addPapyrus(leftNeighbor.getResources().getPapyrus() * -1);
							
							//and the rest from right
							addPapyrus(required.getPapyrus());
							rightRev += (required.getPapyrus() * 2);
							required.addPapyrus(required.getPapyrus() * -1);
						}
					}
					break;
				case 1://right first
					if ( required.getGlass() > 0 )
					{
						if ( rightNeighbor.getResources().getGlass() >= required.getGlass() )
						{//buy all from right
							addGlass(required.getGlass());
							rightRev += (required.getGlass() * 2);
							required.addGlass(required.getGlass() * -1);
						}
						else
						{
							//buy as much as you can from right
							addGlass(rightNeighbor.getResources().getGlass());
							rightRev += (rightNeighbor.getResources().getGlass() * 2);
							required.addGlass(rightNeighbor.getResources().getGlass() * -1);
							
							//and the rest from left
							addGlass(required.getGlass());
							leftRev += (required.getGlass() * 2);
							required.addGlass(required.getGlass() * -1);
						}
					}
					if ( required.getLoom() > 0 )
					{
						if ( rightNeighbor.getResources().getLoom() >= required.getLoom() )
						{//buy all from right
							addLoom(required.getLoom());
							rightRev += (required.getLoom() * 2);
							required.addLoom(required.getLoom() * -1);
						}
						else
						{
							//buy as much as you can from right
							addLoom(rightNeighbor.getResources().getLoom());
							rightRev += (rightNeighbor.getResources().getLoom() * 2);
							required.addLoom(rightNeighbor.getResources().getLoom() * -1);
							
							//and the rest from left
							addLoom(required.getLoom());
							leftRev += (required.getLoom() * 2);
							required.addLoom(required.getLoom() * -1);
						}
					}
					if ( required.getPapyrus() > 0)
					{
						if ( rightNeighbor.getResources().getPapyrus() >= required.getPapyrus() )
						{//buy all from right
							addPapyrus(required.getPapyrus());
							rightRev += (required.getPapyrus() * 2);
							required.addPapyrus(required.getPapyrus() * -1);
						}
						else
						{
							//buy as much as you can from right
							addPapyrus(rightNeighbor.getResources().getPapyrus());
							rightRev += (rightNeighbor.getResources().getPapyrus() * 2);
							required.addPapyrus(rightNeighbor.getResources().getPapyrus() * -1);
							
							//and the rest from left
							addPapyrus(required.getPapyrus());
							leftRev += (required.getPapyrus() * 2);
							required.addPapyrus(required.getPapyrus() * -1);
						}
					}
					break;
			}
		}
		
		//primary resources
		if ( prices.primleft() && prices.primright() )//cheaper price
		{
			switch ( preference )
			{
				case 0://left first
					if ( required.getOre() > 0 )
					{
						if ( leftNeighbor.getResources().getOre() >= required.getOre() )
						{//buy all from left
							addOre(required.getOre());
							leftRev += required.getOre();
							required.addOre(required.getOre() * -1);
						}
						else
						{
							//buy as much as you can from left
							addOre(leftNeighbor.getResources().getOre());
							leftRev += leftNeighbor.getResources().getOre();
							required.addOre(leftNeighbor.getResources().getOre() * -1);
							
							//and the rest from right
							addOre(required.getOre());
							rightRev += required.getOre();
							required.addOre(required.getOre() * -1);
						}
					}
					if ( required.getStone() > 0 )
					{
						if ( leftNeighbor.getResources().getStone() >= required.getStone() )
						{//buy all from left
							addStone(required.getStone());
							leftRev += required.getStone();
							required.addStone(required.getStone() * -1);
						}
						else
						{
							//buy as much as you can from left
							addStone(leftNeighbor.getResources().getStone());
							leftRev += leftNeighbor.getResources().getStone();
							required.addStone(leftNeighbor.getResources().getStone() * -1);
							
							//and the rest from right
							addStone(required.getStone());
							rightRev += required.getStone();
							required.addStone(required.getStone() * -1);
						}
					}
					if ( required.getWood() > 0 )
					{
						if ( leftNeighbor.getResources().getWood() >= required.getWood() )
						{//buy all from left
							addWood(required.getWood());
							leftRev += required.getWood();
							required.addWood(required.getWood() * -1);
						}
						else
						{
							//buy as much as you can from left
							addWood(leftNeighbor.getResources().getWood());
							leftRev += leftNeighbor.getResources().getWood();
							required.addWood(leftNeighbor.getResources().getWood() * -1);
							
							//and the rest from right
							addWood(required.getWood());
							rightRev += required.getWood();
							required.addWood(required.getWood() * -1);
						}
					}
					if ( required.getClay() > 0 )
					{
						if ( leftNeighbor.getResources().getClay() >= required.getClay() )
						{//buy all from left
							addClay(required.getClay());
							leftRev += required.getClay();
							required.addClay(required.getClay() * -1);
						}
						else
						{
							//buy as much as you can from left
							addClay(leftNeighbor.getResources().getClay());
							leftRev += leftNeighbor.getResources().getClay();
							required.addClay(leftNeighbor.getResources().getClay() * -1);
							
							//and the rest from right
							addClay(required.getClay());
							rightRev += required.getClay();
							required.addClay(required.getClay() * -1);
						}
					}
					break;
				case 1://right first
					if ( required.getOre() > 0 )
					{
						if ( rightNeighbor.getResources().getOre() >= required.getOre() )
						{//buy all from right
							addOre(required.getOre());
							rightRev += required.getOre();
							required.addOre(required.getOre() * -1);
						}
						else
						{
							//buy as much as you can from right
							addOre(rightNeighbor.getResources().getOre());
							rightRev += rightNeighbor.getResources().getOre();
							required.addOre(rightNeighbor.getResources().getOre() * -1);
							
							//and the rest from left
							addOre(required.getOre());
							leftRev += required.getOre();
							required.addOre(required.getOre() * -1);
						}
					}
					if ( required.getStone() > 0 )
					{
						if ( rightNeighbor.getResources().getStone() >= required.getStone() )
						{//buy all from right
							addStone(required.getStone());
							rightRev += required.getStone();
							required.addStone(required.getStone() * -1);
						}
						else
						{
							//buy as much as you can from right
							addStone(rightNeighbor.getResources().getStone());
							rightRev += rightNeighbor.getResources().getStone();
							required.addStone(rightNeighbor.getResources().getStone() * -1);
							
							//and the rest from left
							addStone(required.getStone());
							leftRev += required.getStone();
							required.addStone(required.getStone() * -1);
						}
					}
					if ( required.getWood() > 0 )
					{
						if ( rightNeighbor.getResources().getWood() >= required.getWood() )
						{//buy all from right
							addWood(required.getWood());
							rightRev += (required.getWood());
							required.addWood(required.getWood() * -1);
						}
						else
						{
							//buy as much as you can from right
							addWood(rightNeighbor.getResources().getWood());
							rightRev += rightNeighbor.getResources().getWood();
							required.addWood(rightNeighbor.getResources().getWood() * -1);
							
							//and the rest from left
							addWood(required.getWood());
							leftRev += required.getWood();
							required.addWood(required.getWood() * -1);
						}
					}
					if ( required.getClay() > 0 )
					{
						if ( rightNeighbor.getResources().getClay() >= required.getClay() )
						{//buy all from right
							addClay(required.getClay());
							rightRev += required.getClay();
							required.addClay(required.getClay() * -1);
						}
						else
						{
							//buy as much as you can from right
							addClay(rightNeighbor.getResources().getClay());
							rightRev += rightNeighbor.getResources().getClay();
							required.addClay(rightNeighbor.getResources().getClay() * -1);
							
							//and the rest from left
							addClay(required.getClay());
							leftRev += required.getClay();
							required.addClay(required.getClay() * -1);
						}
					}
					break;
			}
		}
		else if ( prices.primleft() || prices.primright() )
		{
			if ( prices.primleft() )//cheaper price for left
			{
				//buy as much as you can at discount from left
				//buy rest at regular price from right
				if ( required.getOre() > 0 )
				{
					if ( leftNeighbor.getResources().getOre() >= required.getOre() )
					{//buy all from left
						addOre(required.getOre());
						leftRev += required.getOre();
						required.addOre(required.getOre() * -1);
					}
					else
					{
						//buy as much as you can from left
						addOre(leftNeighbor.getResources().getOre());
						leftRev += leftNeighbor.getResources().getOre();
						required.addOre(leftNeighbor.getResources().getOre() * -1);
						
						//and the rest from right
						addOre(required.getOre());
						rightRev += (required.getOre() * 2);
						required.addOre(required.getOre() * -1);
					}
				}
				if ( required.getStone() > 0 )
				{
					if ( leftNeighbor.getResources().getStone() >= required.getStone() )
					{//buy all from left
						addStone(required.getStone());
						leftRev += required.getStone();
						required.addStone(required.getStone() * -1);
					}
					else
					{
						//buy as much as you can from left
						addStone(leftNeighbor.getResources().getStone());
						leftRev += leftNeighbor.getResources().getStone();
						required.addStone(leftNeighbor.getResources().getStone() * -1);
						
						//and the rest from right
						addStone(required.getStone());
						rightRev += (required.getStone() * 2);
						required.addStone(required.getStone() * -1);
					}
				}
				if ( required.getWood() > 0 )
				{
					if ( leftNeighbor.getResources().getWood() >= required.getWood() )
					{//buy all from left
						addWood(required.getWood());
						leftRev += required.getWood();
						required.addWood(required.getWood() * -1);
					}
					else
					{
						//buy as much as you can from left
						addWood(leftNeighbor.getResources().getWood());
						leftRev += leftNeighbor.getResources().getWood();
						required.addWood(leftNeighbor.getResources().getWood() * -1);
						
						//and the rest from right
						addWood(required.getWood());
						rightRev += (required.getWood() * 2);
						required.addWood(required.getWood() * -1);
					}
				}
				if ( required.getClay() > 0 )
				{
					if ( leftNeighbor.getResources().getClay() >= required.getClay() )
					{//buy all from left
						addClay(required.getClay());
						leftRev += required.getClay();
						required.addClay(required.getClay() * -1);
					}
					else
					{
						//buy as much as you can from left
						addClay(leftNeighbor.getResources().getClay());
						leftRev += leftNeighbor.getResources().getClay();
						required.addClay(leftNeighbor.getResources().getClay() * -1);
						
						//and the rest from right
						addClay(required.getClay());
						rightRev += (required.getClay() * 2);
						required.addClay(required.getClay() * -1);
					}
				}
			}
			if ( prices.primright() )//cheaper price for right
			{
				//buy as much as you can at discount from right
				//buy rest at regular price from left
				if ( required.getOre() > 0 )
				{
					if ( rightNeighbor.getResources().getOre() >= required.getOre() )
					{//buy all from right
						addOre(required.getOre());
						rightRev += required.getOre();
						required.addOre(required.getOre() * -1);
					}
					else
					{
						//buy as much as you can from right
						addOre(rightNeighbor.getResources().getOre());
						rightRev += rightNeighbor.getResources().getOre();
						required.addOre(rightNeighbor.getResources().getOre() * -1);
						
						//and the rest from left
						addOre(required.getOre());
						leftRev += (required.getOre() * 2);
						required.addOre(required.getOre() * -1);
					}
				}
				if ( required.getStone() > 0 )
				{
					if ( rightNeighbor.getResources().getStone() >= required.getStone() )
					{//buy all from right
						addStone(required.getStone());
						rightRev += required.getStone();
						required.addStone(required.getStone() * -1);
					}
					else
					{
						//buy as much as you can from right
						addStone(rightNeighbor.getResources().getStone());
						rightRev += rightNeighbor.getResources().getStone();
						required.addStone(rightNeighbor.getResources().getStone() * -1);
						
						//and the rest from left
						addStone(required.getStone());
						leftRev += (required.getStone() * 2);
						required.addStone(required.getStone() * -1);
					}
				}
				if ( required.getWood() > 0 )
				{
					if ( rightNeighbor.getResources().getWood() >= required.getWood() )
					{//buy all from right
						addWood(required.getWood());
						rightRev += required.getWood();
						required.addWood(required.getWood() * -1);
					}
					else
					{
						//buy as much as you can from right
						addWood(rightNeighbor.getResources().getWood());
						rightRev += rightNeighbor.getResources().getWood();
						required.addWood(rightNeighbor.getResources().getWood() * -1);
						
						//and the rest from left
						addWood(required.getWood());
						leftRev += (required.getWood() * 2);
						required.addWood(required.getWood() * -1);
					}
				}
				if ( required.getClay() > 0 )
				{
					if ( rightNeighbor.getResources().getClay() >= required.getClay() )
					{//buy all from right
						addClay(required.getClay());
						rightRev += required.getClay();
						required.addClay(required.getClay() * -1);
					}
					else
					{
						//buy as much as you can from right
						addClay(rightNeighbor.getResources().getClay());
						rightRev += rightNeighbor.getResources().getClay();
						required.addClay(rightNeighbor.getResources().getClay() * -1);
						
						//and the rest from left
						addClay(required.getClay());
						leftRev += (required.getClay() * 2);
						required.addClay(required.getClay() * -1);
					}
				}
			}
		}
		else//regular price
		{
			switch ( preference )
			{
				case 0://left first
					if ( required.getOre() > 0 )
					{
						if ( leftNeighbor.getResources().getOre() >= required.getOre() )
						{//buy all from left
							addOre(required.getOre());
							leftRev += (required.getOre() * 2);
							required.addOre(required.getOre() * -1);
						}
						else
						{
							//buy as much as you can from left
							addOre(leftNeighbor.getResources().getOre());
							leftRev += (leftNeighbor.getResources().getOre() * 2);
							required.addOre(leftNeighbor.getResources().getOre() * -1);
							
							//and the rest from right
							addOre(required.getOre());
							rightRev += (required.getOre() * 2);
							required.addOre(required.getOre() * -1);
						}
					}
					if ( required.getStone() > 0 )
					{
						if ( leftNeighbor.getResources().getStone() >= required.getStone() )
						{//buy all from left
							addStone(required.getStone());
							leftRev += (required.getStone() * 2);
							required.addStone(required.getStone() * -1);
						}
						else
						{
							//buy as much as you can from left
							addStone(leftNeighbor.getResources().getStone());
							leftRev += (leftNeighbor.getResources().getStone() * 2);
							required.addStone(leftNeighbor.getResources().getStone() * -1);
							
							//and the rest from right
							addStone(required.getStone());
							rightRev += (required.getStone() * 2);
							required.addStone(required.getStone() * -1);
						}
					}
					if ( required.getWood() > 0 )
					{
						if ( leftNeighbor.getResources().getWood() >= required.getWood() )
						{//buy all from left
							addWood(required.getWood());
							leftRev += (required.getWood() * 2);
							required.addWood(required.getWood() * -1);
						}
						else
						{
							//buy as much as you can from left
							addWood(leftNeighbor.getResources().getWood());
							leftRev += (leftNeighbor.getResources().getWood() * 2);
							required.addWood(leftNeighbor.getResources().getWood() * -1);
							
							//and the rest from right
							addWood(required.getWood());
							rightRev += (required.getWood() * 2);
							required.addWood(required.getWood() * -1);
						}
					}
					if ( required.getClay() > 0 )
					{
						if ( leftNeighbor.getResources().getClay() >= required.getClay() )
						{//buy all from left
							addClay(required.getClay());
							leftRev += (required.getClay() * 2);
							required.addClay(required.getClay() * -1);
						}
						else
						{
							//buy as much as you can from left
							addClay(leftNeighbor.getResources().getClay());
							leftRev += (leftNeighbor.getResources().getClay() * 2);
							required.addClay(leftNeighbor.getResources().getClay() * -1);
							
							//and the rest from right
							addClay(required.getClay());
							rightRev += (required.getClay() * 2);
							required.addClay(required.getClay() * -1);
						}
					}
					break;
				case 1://right first
					if ( required.getOre() > 0 )
					{
						if ( rightNeighbor.getResources().getOre() >= required.getOre() )
						{//buy all from right
							addOre(required.getOre());
							rightRev += (required.getOre() * 2);
							required.addOre(required.getOre() * -1);
						}
						else
						{
							//buy as much as you can from right
							addOre(rightNeighbor.getResources().getOre());
							rightRev += (rightNeighbor.getResources().getOre() * 2);
							required.addOre(rightNeighbor.getResources().getOre() * -2);
							
							//and the rest from left
							addOre(required.getOre());
							leftRev += (required.getOre() * 2);
							required.addOre(required.getOre() * -1);
						}
					}
					if ( required.getStone() > 0 )
					{
						if ( rightNeighbor.getResources().getStone() >= required.getStone() )
						{//buy all from right
							addStone(required.getStone());
							rightRev += (required.getStone() * 2);
							required.addStone(required.getStone() * -1);
						}
						else
						{
							//buy as much as you can from right
							addStone(rightNeighbor.getResources().getStone());
							rightRev += (rightNeighbor.getResources().getStone() * 2);
							required.addStone(rightNeighbor.getResources().getStone() * -1);
							
							//and the rest from left
							addStone(required.getStone());
							leftRev += (required.getStone() * 2);
							required.addStone(required.getStone() * -1);
						}
					}
					if ( required.getWood() > 0 )
					{
						if ( rightNeighbor.getResources().getWood() >= required.getWood() )
						{//buy all from right
							addWood(required.getWood());
							rightRev += (required.getWood() * 2);
							required.addWood(required.getWood() * -1);
						}
						else
						{
							//buy as much as you can from right
							addWood(rightNeighbor.getResources().getWood());
							rightRev += (rightNeighbor.getResources().getWood() * 2);
							required.addWood(rightNeighbor.getResources().getWood() * -1);
							
							//and the rest from left
							addWood(required.getWood());
							leftRev += (required.getWood() * 2);
							required.addWood(required.getWood() * -1);
						}
					}
					if ( required.getClay() > 0 )
					{
						if ( rightNeighbor.getResources().getClay() >= required.getClay() )
						{//buy all from right
							addClay(required.getClay());
							rightRev += (required.getClay() * 2);
							required.addClay(required.getClay() * -1);
						}
						else
						{
							//buy as much as you can from right
							addClay(rightNeighbor.getResources().getClay());
							rightRev += (rightNeighbor.getResources().getClay() * 2);
							required.addClay(rightNeighbor.getResources().getClay() * -1);
							
							//and the rest from left
							addClay(required.getClay());
							leftRev += (required.getClay() * 2);
							required.addClay(required.getClay() * -1);
						}
					}
					break;
			}
		}
		neighborRevenue.add(leftRev);
		neighborRevenue.add(rightRev);
		return neighborRevenue;
	}
	
	
	//not adjusted for special effect prices
	/*public static int buyResources(Player buyer, Resources seller, Resources required, int primaryPrice, int manufacturedPrice)
	{
		int coins = 0;
		if ( seller.getOre() >= required.getOre() && required.getOre() > 0 )
		{
			buyer.getPurchasedResources().addOre(required.getOre());
			buyer.getPurchasedResources().addCoins(required.getOre() * -primaryPrice);
			coins += (required.getOre() * primaryPrice);
			required.addOre(required.getOre() * -1);
		}
		if ( seller.getStone() >= required.getStone() && required.getStone() > 0 )
		{
			buyer.getPurchasedResources().addStone(required.getStone());
			buyer.getPurchasedResources().addCoins(required.getStone() * -primaryPrice);
			coins += (required.getStone() * primaryPrice);
			required.addStone(required.getStone() * -1);
		}
		if ( seller.getWood() >= required.getWood() && required.getWood() > 0 )
		{
			buyer.getPurchasedResources().addWood(required.getWood());
			buyer.getPurchasedResources().addCoins(required.getWood() * -primaryPrice);
			coins += (required.getWood() * primaryPrice);
			required.addWood(required.getWood() * -1);
		}
		if ( seller.getClay() >= required.getClay() && required.getClay() > 0 )
		{
			buyer.getPurchasedResources().addClay(required.getClay());
			buyer.getPurchasedResources().addCoins(required.getClay() * -primaryPrice);
			coins += (required.getClay() * primaryPrice);
			required.addClay(required.getClay() * -1);
		}
		if ( seller.getGlass() >= required.getGlass() && required.getGlass() > 0 )
		{
			buyer.getPurchasedResources().addGlass(required.getGlass());
			buyer.getPurchasedResources().addCoins(required.getGlass() * -manufacturedPrice);
			coins += (required.getGlass() * manufacturedPrice);
			required.addGlass(required.getGlass() * -1);
		}
		if ( seller.getLoom() >= required.getLoom() && required.getLoom() > 0 )
		{
			buyer.getPurchasedResources().addLoom(required.getLoom());
			buyer.getPurchasedResources().addCoins(required.getLoom() * -manufacturedPrice);
			coins += (required.getLoom() * manufacturedPrice);
			required.addLoom(required.getLoom() * -1);
		}
		if ( seller.getPapyrus() >= required.getPapyrus() && required.getPapyrus() > 0 )
		{
			buyer.getPurchasedResources().addPapyrus(required.getPapyrus());
			buyer.getPurchasedResources().addCoins(required.getPapyrus() * -manufacturedPrice);
			coins += (required.getPapyrus() * manufacturedPrice);
			required.addPapyrus(required.getPapyrus() * -1);
		}
		return coins;
	}*/
	
	public String toString()
	{
		return ("Ore: " + ore + "\nStone: " + stone + "\nWood: " + wood + "\nClay: " + clay + "\nGlass: " + glass + "\nLoom: " + loom + "\nPapyrus: " + papyrus + "\nCoins: " + coins);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Player a = new Player();
		a.getResources().addOre(3);
		Player b = new Player();
		b.getResources().addStone(1);
		Resources r = new Resources(3, 1, 0, 0, 0, 0, 0, 0);
		TradingPerks p = new TradingPerks(0, 0);
		Resources ar = new Resources();
		ArrayList<Integer> vals = ar.buyResources(a, b, r, p, 0);
		System.out.println(ar.toString() + "\n");
		System.out.println(a.getResources().toString() + "\n");
		System.out.println(b.getResources().toString() + "\n");
		for ( Integer i : vals)
		{
			System.out.println(i);
		}
	}

}
