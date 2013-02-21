package Resources;

public class Resources {

	public static final int ORE_ID = 1;
	public static final int STONE_ID = 2;
	public static final int WOOD_ID = 3;
	public static final int CLAY_ID = 4;
	public static final int GLASS_ID = 5;
	public static final int LOOM_ID = 6;
	public static final int PAPYRUS_ID = 7;
	public static final int COINS_ID = 8;
	
	private int Ore, Stone, Wood, Clay, Glass, Loom, Papyrus, Coins;
	
	public Resources()
	{
		Ore = 0;
		Stone = 0;
		Wood = 0;
		Clay = 0;
		Glass = 0;
		Loom = 0;
		Papyrus = 0;
		Coins = 0;
	}
	
	public Resources(int ore, int stone, int wood, int clay, int glass, int loom, int papyrus, int coins)
	{
		Ore = ore;
		Stone = stone;
		Wood = wood;
		Clay = clay;
		Glass = glass;
		Loom = loom;
		Papyrus = papyrus;
		Coins = coins;
	}
	
	public boolean CanAfford(Resources funds)
	{
		//If any of these cases are not satisfied return false;
		if ( funds.Clay < Clay )
			return false;
		if ( funds.Glass < Glass )
			return false;
		if ( funds.Loom < Loom )
			return false;
		if ( funds.Ore < Ore )
			return false;
		if ( funds.Papyrus < Papyrus )
			return false;
		if ( funds.Stone < Stone )
			return false;
		if ( funds.Wood < Wood )
			return false;
		if ( funds.Coins < Coins )
			return false;
		
		//otherwise return true;
		return true;
	}
	
	public int GetCoins()
	{
		return Coins;
	}
	
	public int GetOre()
	{
		return Ore;
	}
	
	public int GetStone()
	{
		return Stone;
	}
	
	public int GetWood()
	{
		return Wood;
	}
	
	public int GetClay()
	{
		return Clay;
	}
	
	public int GetGlass()
	{
		return Glass;
	}
	
	public int GetLoom()
	{
		return Loom;
	}
	
	public int GetPapyrus()
	{
		return Papyrus;
	}
	
	public void AddCoins(int coins)
	{
		Coins += coins;
	}
	
	public void AddOre(int ore)
	{
		Ore += ore;
	}
	
	public void AddStone(int stone)
	{
		Stone += stone;
	}
	
	public void AddWood(int wood)
	{
		Wood += wood;
	}
	
	public void AddClay(int clay)
	{
		Clay += clay;
	}
	
	public void AddGlass(int glass)
	{
		Glass += glass;
	}
	
	public void AddLoom(int loom)
	{
		Loom += loom;
	}
	
	public void AddPapyrus(int papyrus)
	{
		Papyrus += papyrus;
	}
	
	public void AddResources(int ore, int stone, int wood, int clay, int glass, int loom, int papyrus, int coins)
	{
		Coins += coins;
		Ore += ore;
		Stone += stone;
		Wood += wood;
		Clay += clay;
		Glass += glass;
		Loom += loom;
		Papyrus += papyrus;
	}
	
	public void AddResources(Resources resources)
	{
		Coins += resources.Coins;
		Ore += resources.Ore;
		Stone += resources.Stone;
		Wood += resources.Wood;
		Clay += resources.Clay;
		Glass += resources.Glass;
		Loom += resources.Loom;
		Papyrus += resources.Papyrus;
	}
	
	public static Resources AddResources(Resources a, Resources b)
	{
		Resources r = new Resources();
		r.Clay    = a.Clay + b.Clay;
		r.Coins   = a.Coins + b.Coins;
		r.Ore     = a.Ore + b.Ore;
		r.Stone   = a.Stone + b.Stone;
		r.Wood    = a.Wood + b.Wood;
		r.Glass   = a.Glass + b.Glass;
		r.Loom    = a.Loom + b.Loom;
		r.Papyrus = a.Papyrus + b.Papyrus;
		return r;
	}
	
	
	public String toString()
	{
		return ("Ore: " + Ore + "\nStone: " + Stone + "\nWood: " + Wood + "\nClay: " + Clay + "\nGlass: " + Glass + "\nLoom: " + Loom + "\nPapyrus: " + Papyrus + "\nCoins: " + Coins);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
