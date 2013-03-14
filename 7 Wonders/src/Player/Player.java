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
		resources = new Resources();
		conflictTokens = new ConflictTokens();
		shields = 0;
		victoryPoints = 0;
		scientificSymbols = new ScientificSymbols();
		resetResources();
	}
	
	public Player(String uname, int id)
	{
		username = uname;
		ID = id;
		wonderBoard = new WonderBoard();
		cards = new ArrayList<Structure>();
		chosenCard = null;
		resources = new Resources();
		resetResources();
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
	
	public Resources getExtraResources()
	{
		return extraResources;
	}
	
	public Resources getPurchasedResources()
	{
		return purchased;
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
	
	public void addExtraResources(Resources r)
	{
		extraResources.addResources(r);
	}
	
	public void addUnvResources(Resources r)
	{
		unavailableResources.addResources(r);
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
	
	//resets resetable resources
	public void resetResources()
	{
		purchased = new Resources();
		unavailableResources = new Resources();
		extraResources = new Resources();
	}
	//returns 0 if already has card
	//returns 1 if can't afford
	//returns 2 if structure got built
	public int buildStructure()
	{
		if ( !wonderBoard.containsCard(chosenCard.getID()) )
		{
			if ( ((chosenCard.getResourceCost().canAfford(Resources.addResources(extraResources, resources, unavailableResources)) 
					|| chosenCard.canBuildForFree(wonderBoard)))  )
			{
				wonderBoard.buildStructure(chosenCard);
				for ( SpecialEffect se : chosenCard.getEffects() )
				{				
					if ( se.getID() == CoinBonus.CoinBonusID )
						((CoinBonus)se).acquireCoins(resources); 
					else if ( se.getID() == VictoryPointBonus.VictoryPointBonusID )
						((VictoryPointBonus)se).acquireVictoryPoints(this);
					else if ( se.getID() == ScientificSymbolBonus.ScientificSymbolBonusID )
					{
						if ( !((ScientificSymbolBonus)se).canChoose() ) 
							((ScientificSymbolBonus)se).acquireSymbol(this);
					}
					else if ( se.getID() == ResourcesBonus.ResourcesBonusID )
						((ResourcesBonus)se).acquireResources(this);
					else if ( se.getID() == ShieldBonus.ShieldBonusID )
						((ShieldBonus)se).acquireShields(this);
					else if ( se.getID() == WonderStageCoinBonus.WonderStageCoinBonusID )
						((WonderStageCoinBonus)se).acquireCoins(this);
				}
				chosenCard = null;
				return 2;
			}
			else return 1;
		}
		else return 0;
	}
	
	//returns true or false
	public boolean buildStructure(Player leftNeighbor, Player rightNeighbor, int preference)
	{
		buyResources(leftNeighbor, rightNeighbor, chosenCard.getResourceCost(), preference);	
		wonderBoard.buildStructure(chosenCard);
		for ( SpecialEffect se : chosenCard.getEffects() )
		{				
			if ( se.getID() == CoinBonus.CoinBonusID )
				((CoinBonus)se).acquireCoins(resources); 
			else if ( se.getID() == VictoryPointBonus.VictoryPointBonusID )
				((VictoryPointBonus)se).acquireVictoryPoints(this);
			else if ( se.getID() == ScientificSymbolBonus.ScientificSymbolBonusID )
			{
				if ( !((ScientificSymbolBonus)se).canChoose() ) 
					((ScientificSymbolBonus)se).acquireSymbol(this);
			}
			else if ( se.getID() == ResourcesBonus.ResourcesBonusID )
				((ResourcesBonus)se).acquireResources(this);
			else if ( se.getID() == ShieldBonus.ShieldBonusID )
				((ShieldBonus)se).acquireShields(this);
			else if ( se.getID() == WonderStageCoinBonus.WonderStageCoinBonusID )
				((WonderStageCoinBonus)se).acquireCoins(this);
		}
		chosenCard = null;
		return true;
	}
	
	public boolean buildStage()
	{
		return wonderBoard.buildStage(chosenCard, Resources.addResources(unavailableResources, Resources.addResources(resources, extraResources)));
	}
	
	public boolean buildStage(Player leftNeighbor, Player rightNeighbor, int preference)
	{
		boolean ans = wonderBoard.buildStage(chosenCard, Resources.addResources(unavailableResources, Resources.addResources(resources, extraResources)));
		
		if ( ans ) return true;
		else 
		{
			buyResources(leftNeighbor, rightNeighbor, wonderBoard.getNextStageCost(), preference);
			return wonderBoard.buildStage(chosenCard, Resources.addResources(purchased, Resources.addResources(unavailableResources, Resources.addResources(resources, extraResources))));
		}
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
		Random r = new Random();
		int val;
		
		TradingPerks sumup;
		ArrayList<TradingPerks> perks = new ArrayList<TradingPerks>();
		for ( Structure s: wonderBoard.getYellowCards() )
		{
			for ( SpecialEffect se: s.getEffects() ) 
			{
				if ( se.getID() == TradingPerks.TradingPerksID )
				{
					perks.add(((TradingPerks)se));
				}
			}
		}
		sumup = TradingPerks.addAll(perks);
		int manprice = 2, primpriceleft = 2, primpriceright = 2;
		if ( sumup.manufac() ) manprice = 1;
		if ( sumup.primleft() ) primpriceleft = 1;
		if ( sumup.primright() ) primpriceright = 1;
		
		//finds what resources need to be purchased from neighbors
		Resources missing = (Resources.addResources(extraResources, Resources.addResources(resources, unavailableResources))).findMissingResources(required);
		
		if ( preference == 2 )
			val = r.nextInt(2);
		else val = preference;
			
		purchased.buyResources(leftNeighbor, rightNeighbor, missing, sumup, preference);
		/*switch ( val )
		{
			case 0: //left neighbor preferred
				leftNeighbor.getResources().addCoins(Resources.buyResources(this, 
						Resources.addResources(leftNeighbor.getResources(), leftNeighbor.getExtraResources()), 
						missing, primpriceleft, manprice));
				rightNeighbor.getResources().addCoins(Resources.buyResources(this, 
						Resources.addResources(rightNeighbor.getResources(), rightNeighbor.getExtraResources()), 
						missing, primpriceright, manprice));
				break;
					
			case 1: //right neighbor preferred
				rightNeighbor.getResources().addCoins(Resources.buyResources(this, 
						Resources.addResources(rightNeighbor.getResources(), rightNeighbor.getExtraResources()), 
						missing, primpriceright, manprice));
				leftNeighbor.getResources().addCoins(Resources.buyResources(this, 
						Resources.addResources(leftNeighbor.getResources(), leftNeighbor.getExtraResources()), 
						missing, primpriceleft, manprice));
				break;
		}*/
		return true;
	}
	
	//checks if his neighbors have enough resources to fulfill the missing ones and if the player can afford to pay for them
	public boolean neighborsHaveResources(Player leftNeighbor, Player rightNeighbor, Resources required)
	{
		TradingPerks sumup;
		ArrayList<TradingPerks> perks = new ArrayList<TradingPerks>();
		for ( Structure s: wonderBoard.getYellowCards() )
		{
			for ( SpecialEffect se: s.getEffects() ) 
			{
				if ( se.getID() == TradingPerks.TradingPerksID )
				{
					perks.add(((TradingPerks)se));
				}
			}
		}
		sumup = TradingPerks.addAll(perks);
		
		Resources missing = (Resources.addResources(extraResources, Resources.addResources(resources, unavailableResources))).findMissingResources(required);
		int price = sumup.bulkPrice(missing, 
				Resources.addResources(leftNeighbor.getResources(), leftNeighbor.getExtraResources()), 
				Resources.addResources(rightNeighbor.getResources(), rightNeighbor.getExtraResources()));
		
		if ( Resources.addResources(
				Resources.addResources(leftNeighbor.getResources(), leftNeighbor.getExtraResources()), 
				Resources.addResources(rightNeighbor.getResources(), rightNeighbor.getExtraResources())).hasRequiredResources(missing) 
				&& price <= resources.getCoins() )
			return true;
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
