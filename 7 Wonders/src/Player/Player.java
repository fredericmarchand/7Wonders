package Player;

import WonderBoards.WonderBoard;
import Structures.Structure;
import Resources.Resources;
import java.util.ArrayList;
import Tokens.MilitaryVictoryTokens;

public class Player {

	private String username;
	private int ID;
	private WonderBoard wonderBoard;
	private ArrayList<Structure> cards;
	private Structure chosenCard;
	@SuppressWarnings("unused")
	private Resources resources, purchased, unavailableResources;
	private int shields;
	private MilitaryVictoryTokens militaryVictoryPoints;
	private int victoryPoints;
	
	//constructors
	public Player()
	{
		username = "noob";
		ID = 0;
		wonderBoard = new WonderBoard();
		cards = new ArrayList<Structure>();
		chosenCard = null;
		resources = new Resources(0, 0, 0, 0, 0, 0, 0, 3);
		purchased = new Resources();
		unavailableResources = new Resources();
		militaryVictoryPoints = new MilitaryVictoryTokens();
		shields = 0;
		victoryPoints = 0;
	}
	
	public Player(String uname, int id)
	{
		username = uname;
		ID = id;
		wonderBoard = new WonderBoard();
		cards = new ArrayList<Structure>();
		chosenCard = null;
		resources = new Resources();
		purchased = new Resources();
		unavailableResources = new Resources();
		militaryVictoryPoints = new MilitaryVictoryTokens();
		shields = 0;
		victoryPoints = 0;
	}

	//getters
	public String getUsername()
	{
		return username;
	}
	
	public int getID()
	{
		return ID;
	}
	
	public ArrayList<Structure> getCards()
	{
		return cards;
	}
	
	public WonderBoard getWonderBoard()
	{
		return wonderBoard;
	}
	
	public Structure getChosenCard()
	{
		return chosenCard;
	}
	
	public int getShields()
	{
		return shields;
	}
	
	public Resources getResources()
	{
		return resources;
	}
	
	public int getVictoryPoints()
	{
		return victoryPoints;
	}
	
	public MilitaryVictoryTokens getMilitaryVictoryTokens()
	{
		return militaryVictoryPoints;
	}
	
	
	//actions
	public void assignWonderBoard(WonderBoard wb)
	{
		wonderBoard = wb;
	}
		
	public void assignCard(Structure s)
	{
		if ( cards.size() < 7 ) cards.add(s);
	}
	
	public void assignCards(ArrayList<Structure> newCards)
	{
		cards = newCards;
	}
	
	public void chooseCard(int index)
	{
		chosenCard = cards.remove(index);
	}
	
	public void addShields(int s)
	{
		shields += s;
	}
	
	public void addVictoryPoints(int points)
	{
		victoryPoints = points;
	}
	
	
	
	public boolean actionPhase(int choice)
	{
		switch ( choice )
		{
		case 1://build the structure
			if ( chosenCard.getResourceCost().canAfford(resources) && !wonderBoard.ContainsCard(chosenCard.getID()) )
			{
				wonderBoard.BuildStructure(chosenCard);
				chosenCard = null;
				return true;
			}
			break;
			
		case 2://Build wonderboard stage
			
			break;
			
		case 3://discard the card for coins
			resources.addCoins(3); 
			chosenCard = null;
			return true;
		}
		return false;
	}
	
	public void buyResources(Player neighbor)
	{
		//check for cards with trading perks
		//2 coins per resource purchased
		//added resources are put in the purchased resources variable
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

}
