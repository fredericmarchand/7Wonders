package Structures.Effects;

import java.util.ArrayList;

import Tokens.Resources;

public class TradingPerks extends SpecialEffect {

	public static final int TradingPerksID = 0x02;
	
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
			hasManufactured = true;
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
		else if ( hasPrimaryRight && !hasPrimaryLeft )
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
		}
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
		// TODO Auto-generated method stub

	}

}
