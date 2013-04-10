package Tokens;

public class ConflictTokens {

	private int oneTokens, threeTokens, fiveTokens, minusOneTokens;
	
	public ConflictTokens()
	{
		oneTokens = 0;
		threeTokens = 0;
		fiveTokens = 0;
		minusOneTokens = 0;
	}
	
	public int getOneTokens()
	{
		return oneTokens;
	}
	
	public int getThreeTokens()
	{
		return threeTokens;
	}
	
	public int getFiveTokens()
	{
		return fiveTokens;
	}
	
	public int getMinusOneTokens()
	{
		return minusOneTokens;
	}
	
	public int getTotal()
	{
		return (1*oneTokens + 3*threeTokens + 5*fiveTokens + -1*minusOneTokens);
	}
	
	public int getTotalPositive() {
		return (1*oneTokens + 3*threeTokens + 5*fiveTokens);
	}
	
	public void addOneTokens(int amount)
	{
		oneTokens += amount;
	}
	
	public void addThreeTokens(int amount)
	{
		threeTokens += amount;
	}
	
	public void addFiveTokens(int amount)
	{
		fiveTokens += amount;
	}
	
	public void addMinusOneTokens(int amount)
	{
		minusOneTokens += amount;
	}
    
	public int getVictoryPoints(){
		if ( getTotal() < 0 ) return 0;
		return getTotal();
	}
	


}
