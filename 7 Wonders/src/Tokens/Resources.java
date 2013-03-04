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
	
	public void addResources(int ore, int stone, int wood, int clay, int glass, int loom, int papyrus, int coins)
	{
		this.coins += coins;
		this.ore += ore;
		this.stone += stone;
		this.wood += wood;
		this.clay += clay;
		this.glass += glass;
		this.loom += loom;
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
