package Structures.Effects;

import java.util.ArrayList;

import Tokens.Resources;

public class TradingPerks extends SpecialEffect {

	public static final int TradingPerksID = 0x02;
	
	@SuppressWarnings("unused")
	private static final int None = 0;
	private static final int LeftNeighbor = 1;
	private static final int RightNeighbor = 2;
	private static final int Both = 3;
	private static final int Primary = 0;
	private static final int Manufactured = 1;
	
	private int partner;
	private int rType;
	
	//has manufactured 
	private boolean hasManufactured;
	//has primary right
	private boolean hasPrimaryLeft;
	//has primary left
	private boolean hasPrimaryRight;
	
	public TradingPerks(int partner, int resourceType)
	{
		super(TradingPerksID, true, IN_TURN, IN_TURN);
		this.partner = partner;
		rType = resourceType;
		hasManufactured = false;
		hasPrimaryLeft = false;
		hasPrimaryRight = false;
		if ( partner == LeftNeighbor )
		{
			hasPrimaryLeft = true;
		}
		else if ( partner == RightNeighbor )
		{
			hasPrimaryRight = true;
		}
		else if ( partner == Both )
		{
			if ( resourceType == Manufactured )
				hasManufactured = true;
			else if ( resourceType == Primary )
			{
				hasPrimaryRight = true;
				hasPrimaryLeft = true;
			}
		}
	}
	
	public int getResourceType()
	{
		return rType;
	}
	
	public int getPartner()
	{
		return partner;
	}
	
	public boolean manufac()
	{
		return hasManufactured;
	}
	
	public boolean primleft()
	{
		return hasPrimaryLeft;
	}
	
	public boolean primright()
	{
		return hasPrimaryRight;
	}
	
	//finds the minimum price possible
	public int bulkPrice(Resources missing, Resources right, Resources left)
	{
		int price = 0;
		if ( hasManufactured )
		{
			price += missing.getManufacturedCount();
		}
		else price += 2*missing.getManufacturedCount();
		
		if ( hasPrimaryRight && hasPrimaryLeft )
		{
			price += missing.getPrimaryCount();
		}
		else if ( hasPrimaryLeft )//cheaper price for left
		{
			//buy as much as you can at discount from left
			//buy rest at regular price from right
			if ( missing.getOre() > 0 )
			{
				if ( left.getOre() >= missing.getOre() )
				{//buy all from left
					price += missing.getOre();
				}
				else
				{
					price += left.getOre();
					price += (2*(missing.getOre() - left.getOre()));
				}
			}
			if ( missing.getStone() > 0 )
			{
				if ( left.getStone() >= missing.getStone() )
				{//buy all from left
					price += missing.getStone();
				}
				else
				{
					price += left.getStone();
					price += (2*(missing.getStone() - left.getStone()));
				}
			}
			if ( missing.getWood() > 0 )
			{
				if ( left.getWood() >= missing.getWood() )
				{//buy all from left
					price += missing.getWood();
				}
				else
				{
					price += left.getWood();
					price += (2*(missing.getWood() - left.getWood()));
				}
			}
			if ( missing.getClay() > 0 )
			{
				if ( left.getClay() >= missing.getClay() )
				{//buy all from left
					price += missing.getClay();
				}
				else
				{
					price += left.getClay();
					price += (2*(missing.getClay() - left.getClay()));
				}
			}
		}
		else if ( hasPrimaryRight )//cheaper price for right
		{
			//buy as much as you can at discount from right
			//buy rest at regular price from left
			if ( missing.getOre() > 0 )
			{
				if ( right.getOre() >= missing.getOre() )
				{//buy all from right
					price += missing.getOre();
				}
				else
				{
					price += right.getOre();
					price += (2*(missing.getOre() - right.getOre()));
				}
			}
			if ( missing.getStone() > 0 )
			{
				if ( right.getStone() >= missing.getStone() )
				{//buy all from right
					price += missing.getStone();
				}
				else
				{
					price += right.getStone();
					price += (2*(missing.getStone() - right.getStone()));
				}
			}
			if ( missing.getWood() > 0 )
			{
				if ( right.getWood() >= missing.getWood() )
				{//buy all from right
					price += missing.getWood();
				}
				else
				{
					price += right.getWood();
					price += (2*(missing.getWood() - right.getWood()));
				}
			}
			if ( missing.getClay() > 0 )
			{
				if ( right.getClay() >= missing.getClay() )
				{//buy all from right
					price += missing.getClay();
				}
				else
				{
					price += right.getClay();
					price += (2*(missing.getClay() - right.getClay()));
				}
			}
		}
		/*else if ( hasPrimaryRight && !hasPrimaryLeft )
		{
			if ( missing.getPrimaryCount() <= right.getPrimaryCount() )
				price += missing.getPrimaryCount();
			else
			{
				price += right.getPrimaryCount();
				price += 2*(missing.getPrimaryCount() - right.getPrimaryCount());
			}
		}
		else if ( hasPrimaryLeft && !hasPrimaryRight )
		{
			if ( missing.getPrimaryCount() <= left.getPrimaryCount() )
				price += missing.getPrimaryCount();
			else
			{
				price += left.getPrimaryCount();
				price += 2*(missing.getPrimaryCount() - left.getPrimaryCount());
			}
		}*/
		else
		{
			price += 2*missing.getPrimaryCount();
		}
		
		return price;
	}
	
	public static TradingPerks addAll(ArrayList<TradingPerks> args)
	{
		TradingPerks t = new TradingPerks(0, 2);
		for ( TradingPerks tp: args )
		{
			if ( tp.hasManufactured )
				t.hasManufactured = true;
			if ( tp.hasPrimaryLeft )
				t.hasPrimaryLeft = true;
			if ( tp.hasPrimaryRight )
				t.hasManufactured = true;
		}
		return t;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TradingPerks tp = new TradingPerks(3,0);
		System.out.println(tp.bulkPrice(new Resources(1, 0, 1, 0, 1, 0, 0, 0), new Resources(1, 0, 0, 0, 0, 0, 0, 0), new Resources(0, 0, 1, 0, 1, 0, 0, 0)));
	}

}
