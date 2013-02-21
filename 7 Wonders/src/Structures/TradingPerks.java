package Structures;

import Resources.Resources;

public class TradingPerks extends SpecialEffects {

	public static final int TradingPerksID = 0x02;
	
	@SuppressWarnings("unused")
	private static final int None = 0;
	@SuppressWarnings("unused")
	private static final int LeftNeighbor = 1;
	@SuppressWarnings("unused")
	private static final int RightNeighbor = 2;
	@SuppressWarnings("unused")
	private static final int Both = 3;
	private static final boolean Primary = false;
	@SuppressWarnings("unused")
	private static final boolean Manufactured = true;
	
	private int partner;
	private Resources possibilities;
	private int price;
	
	
	public TradingPerks(int partner, boolean resourceType, int price)
	{
		super(TradingPerksID, true, RELOAD_EVERY_TURN);
		this.partner = partner;
		if ( resourceType == Primary )
			possibilities = new Resources(1, 1, 1, 1, 0, 0, 0, 0);
		else possibilities = new Resources(0, 0, 0, 0, 1, 1, 1, 0);
		this.price = price;
	}
	
	public int GetPartner()
	{
		return partner;
	}
	
	public Resources GetPossibilities()
	{
		return possibilities;
	}
	
	public int GetPrice()
	{
		return price;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
