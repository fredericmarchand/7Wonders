package Tokens;

public class MilitaryVictoryTokens {

	private int OneTokens, ThreeTokens, FiveTokens, MinusOneTokens;
	
	public MilitaryVictoryTokens()
	{
		OneTokens = 0;
		ThreeTokens = 0;
		FiveTokens = 0;
		MinusOneTokens = 0;
	}
	
	public int GetOneTokens()
	{
		return OneTokens;
	}
	
	public int GetThreeTokens()
	{
		return ThreeTokens;
	}
	
	public int GetFiveTokens()
	{
		return FiveTokens;
	}
	
	public int GetMinusOneTokens()
	{
		return MinusOneTokens;
	}
	
	public int GetTotal()
	{
		return (1*OneTokens + 3*ThreeTokens + 5*FiveTokens + -1*MinusOneTokens);
	}
	
	public void AddOneTokens(int amount)
	{
		OneTokens += amount;
	}
	
	public void AddThreeTokens(int amount)
	{
		ThreeTokens += amount;
	}
	
	public void AddFiveTokens(int amount)
	{
		FiveTokens += amount;
	}
	
	public void AddMinusOneTokens(int amount)
	{
		MinusOneTokens += amount;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
