package Tokens;

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
	
	
	/*public void addResources(int ore, int stone, int wood, int clay, int glass, int loom, int papyrus, int coins)
	{
		this.coins += coins;
		this.ore += ore;
		this.stone += stone;
		this.wood += wood;
		this.clay += clay;
		this.glass += glass;
		this.loom += loom;
		this.papyrus += papyrus;
	}*/
	
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
	
	public Resources findMissingResources(Resources required)
	{
		Resources r = new Resources();
		r.ore = required.ore - ore;
		r.stone = required.stone - stone;
		r.wood = required.wood - wood;
		r.clay = required.clay - clay;
		r.glass = required.glass - glass;
		r.loom = required.loom - loom;
		r.papyrus = required.papyrus - papyrus;
		return r;
	}
	
	//not adjusted for special effect prices
	public static void buyResources(Resources buyer, Resources seller, Resources required)
	{
		if ( seller.getOre() >= required.getOre() && required.getOre() > 0 )
		{
			buyer.addOre(required.getOre());
			buyer.addCoins(required.getOre() * -2);
			seller.addCoins(required.getOre() * 2);
			required.addOre(required.getOre() * -1);
		}
		if ( seller.getStone() >= required.getStone() && required.getStone() > 0 )
		{
			buyer.addStone(required.getStone());
			buyer.addCoins(required.getStone() * -2);
			seller.addCoins(required.getStone() * 2);
			required.addStone(required.getStone() * -1);
		}
		if ( seller.getWood() >= required.getWood() && required.getWood() > 0 )
		{
			buyer.addWood(required.getWood());
			buyer.addCoins(required.getWood() * -2);
			seller.addCoins(required.getWood() * 2);
			required.addWood(required.getWood() * -1);
		}
		if ( seller.getClay() >= required.getClay() && required.getClay() > 0 )
		{
			buyer.addClay(required.getClay());
			buyer.addCoins(required.getClay() * -2);
			seller.addCoins(required.getClay() * 2);
			required.addClay(required.getClay() * -1);
		}
		if ( seller.getGlass() >= required.getGlass() && required.getGlass() > 0 )
		{
			buyer.addOre(required.getGlass());
			buyer.addCoins(required.getGlass() * -2);
			seller.addCoins(required.getGlass() * 2);
			required.addOre(required.getGlass() * -1);
		}
		if ( seller.getLoom() >= required.getLoom() && required.getLoom() > 0 )
		{
			buyer.addLoom(required.getLoom());
			buyer.addCoins(required.getLoom() * -2);
			seller.addCoins(required.getLoom() * 2);
			required.addLoom(required.getLoom() * -1);
		}
		if ( seller.getPapyrus() >= required.getPapyrus() && required.getPapyrus() > 0 )
		{
			buyer.addPapyrus(required.getPapyrus());
			buyer.addCoins(required.getPapyrus() * -2);
			seller.addCoins(required.getPapyrus() * 2);
			required.addPapyrus(required.getPapyrus() * -1);
		}
		
	}
	
	public String toString()
	{
		return ("Ore: " + ore + "\nStone: " + stone + "\nWood: " + wood + "\nClay: " + clay + "\nGlass: " + glass + "\nLoom: " + loom + "\nPapyrus: " + papyrus + "\nCoins: " + coins);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
