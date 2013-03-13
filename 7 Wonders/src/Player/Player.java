package Player;

import WonderBoards.WonderBoard;
import Structures.Structure;
import Structures.Effects.*;

import java.util.ArrayList;
import Tokens.*;
import java.util.Random;

public class Player {

	private String username;
	private int ID;
	private WonderBoard wonderBoard;
	@SuppressWarnings("unused")
	private ArrayList<Structure> cards, discardedCards;
	private Structure chosenCard;
	@SuppressWarnings("unused")
	private Resources resources, extraResources, purchased, unavailableResources;
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
	
	public void setChosenCard( Structure s )
	{
		chosenCard = s;
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
			for ( SpecialEffect se : chosenCard.getEffects() )
			{				
				if ( se.getType() == CoinBonus.CoinBonusID )
					((CoinBonus)se).acquireCoins(resources); 
				else if ( se.getType() == VictoryPointBonus.VictoryPointBonusID )
					((VictoryPointBonus)se).acquireVictoryPoints(this);
				else if ( se.getType() == ScientificSymbolBonus.ScientificSymbolBonusID )
				{
					if ( !((ScientificSymbolBonus)se).canChoose() ) 
						((ScientificSymbolBonus)se).acquireSymbol(this);
				}
				else if ( se.getType() == ResourcesBonus.ResourcesBonusID )
					((ResourcesBonus)se).acquireResources(this);
				else if ( se.getType() == ShieldBonus.ShieldBonusID )
					((ShieldBonus)se).acquireShields(this);
				else if ( se.getType() == WonderStageCoinBonus.WonderStageCoinBonusID )
					((WonderStageCoinBonus)se).acquireCoins(this);
			}
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
	
	//this function must be called after a player has selected their card on the last turn of an age
	public void discardHand(ArrayList<Structure> discardedCards)
	{
		discardedCards.addAll(cards);
		cards.clear();
	}
	
	//the preference parameter is used to ask the player if he cares which neighbor he buys resources from
	//preference == 0 -> left neighbor preferred (i.e., resources will be purchased from the left neighbor unless he does not have them in which case they will be purchased from the right neighbor)
	//preference == 1 -> right neighbor preferred (i.e., resources will be purchased from the right neighbor unless he does not have them in which case they will be purchased from the left neighbor)
	//preference == 2 -> random
	public boolean buyResources(Player leftNeighbor, Player rightNeighbor, Resources required, int preference)
	{
		//check for cards with trading perks
		
		Random r = new Random();
		int val;
		//checking if player has enough money, value of 2 will change when special effects are integrated
		if ( resources.getCoins() < 2 ) return false;
		
		//finds what resources need to be purchased from neighbors
		Resources missing = resources.findMissingResources(required);
		
		//checks if the combined neighbors resources are enough to fill the missing resources and if the player can afford them
		if ( Resources.addResources(leftNeighbor.getResources(), rightNeighbor.getResources()).hasRequiredResources(missing) && (missing.getCount()*2) <= resources.getCoins() )
		{
			if ( preference == 2 )
				val = r.nextInt(2);
			else val = preference;
			
			switch ( val )
			{
				case 0: //any neighbor
					Resources.buyResources(purchased, leftNeighbor.getResources(), missing);
					Resources.buyResources(purchased, rightNeighbor.getResources(), missing);
					break;
					
				case 1: //right neighbor preferred
					Resources.buyResources(purchased, rightNeighbor.getResources(), missing);
					Resources.buyResources(purchased, leftNeighbor.getResources(), missing);
					break;
			}
			return true;
		}
		return false;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Random r = new Random();
		System.out.println(r.nextInt(2));
	}

}
