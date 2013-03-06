package Player;

import WonderBoards.WonderBoard;
import Structures.Structure;
import java.util.ArrayList;
import Tokens.*;

public class Player {

	private String username;
	private int ID;
	private WonderBoard wonderBoard;
	private ArrayList<Structure> cards;
	private Structure chosenCard;
	@SuppressWarnings("unused")
	private Resources resources, purchased, unavailableResources;
	private int shields;
	private ConflictTokens conflictTokens;
	private int victoryPoints;
	private ScientificSymbols scientificSymbols;
	
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
		conflictTokens = new ConflictTokens();
		shields = 0;
		victoryPoints = 0;
		scientificSymbols = new ScientificSymbols();
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
		conflictTokens = new ConflictTokens();
		shields = 0;
		victoryPoints = 0;
		scientificSymbols = new ScientificSymbols();
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
	
	public ConflictTokens getConflictTokens()
	{
		return conflictTokens;
	}
	
	public ScientificSymbols getScientificSymbols()
	{
		return scientificSymbols;
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
		victoryPoints += points;
	}
	
	public boolean buildStructure()
	{
		if ( chosenCard.getResourceCost().canAfford(resources) && !wonderBoard.containsCard(chosenCard.getID()) )
		{
			wonderBoard.buildStructure(chosenCard);
			chosenCard = null;
			return true;
		}
		return false;
	}
	
	public boolean buildStage()
	{
		return wonderBoard.buildStage(chosenCard, resources);
	}
	
	public void discard(ArrayList<Structure> discardedCards)
	{
		resources.addCoins(3); 
		discardedCards.add(chosenCard);
		chosenCard = null;
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
