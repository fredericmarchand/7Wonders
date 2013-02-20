package Resources;

public class Resources {

	private int Ore;
	private int Stone;
	private int Wood;
	private int Clay;
	private int Glass;
	private int Loom;
	private int Papyrus;
	private int Coins;
	
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
