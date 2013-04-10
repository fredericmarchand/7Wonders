package Player;

import WonderBoards.WonderBoard;
import WonderBoards.WonderBoardStage;
import Structures.Structure;
import Structures.Effects.*;

import java.util.ArrayList;
import Tokens.*;
import java.util.Random;

public class Player extends User {

	protected WonderBoard wonderBoard;
	protected ArrayList<Structure> cards;
	protected Structure chosenCard;
	protected int chosenCardIndex;
	protected Resources resources, extraResources, purchased, unavailableResources;
	protected int shields;
	protected ConflictTokens conflictTokens;
	protected int victoryPoints;
	protected ScientificSymbols scientificSymbols;
	protected boolean isAI;
	protected int currentAge;
	
	//constructors
	public Player()
	{
		super("noob", 0);
		resetPlayerStats();
		isAI = false;
	}
	
	public Player(String uname, long id)
	{
		super(uname, id);
		resetPlayerStats();
		isAI = false;
	}
	
	
	public void resetPlayerStats()
	{
		wonderBoard = new WonderBoard();
		cards = new ArrayList<Structure>();
		chosenCard = new Structure();
		resources = new Resources();
		resetResources();
		conflictTokens = new ConflictTokens();
		shields = 0;
		victoryPoints = 0;
		scientificSymbols = new ScientificSymbols();
		currentAge = 1;
	}

	public int getAge()
	{
		return currentAge;
	}
	
	public void nextAge()
	{
		currentAge++;
	}
	
	public void initAge()
	{
		currentAge = 1;
	}
	public void setAge(int age)
	{
		currentAge = age;
	}
	
	public boolean ai()
	{
		return isAI;
	}
	
	public void setAI(int c)
	{
		if ( c == 1 )
			isAI = true;
		else isAI = false;
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
	
	public int getChosenCardIndex()
	{
		return chosenCardIndex;
	}
	
	public void setChosenCardIndex(int i)
	{
		chosenCardIndex = i;
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
		return Resources.addResources(resources, extraResources);
	}
	
	public Resources getTotalResources()
	{
		return Resources.addResources(resources, extraResources, unavailableResources);
	}
	
	public Resources getOwnedResources()
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
	
	public Resources getUnvResources()
	{
		return unavailableResources;
	}
	
	public void setOwnedResources(Resources r)
	{
		resources = r;
	}
	
	public void setExtraResources(Resources r)
	{
		extraResources = r;
	}
	
	public void setPurchasedResources(Resources r)
	{
		purchased = r;
	}
	
	public void setUnvResources(Resources r)
	{
		unavailableResources = r;
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
		cards.clear();
		cards.addAll(newCards);
	}
	
	public void chooseCard(int index)
	{
		chosenCard = cards.get(index);
		chosenCardIndex = index;
	}
	
	public void chooseCardByID(int id)
	{
		for ( Structure s: cards )
		{
			if ( id == s.getID() )
			{
				chooseCard(s);
				break;
			}
		}
	}
	
	public void chooseCard(Structure s)
	{
		chosenCard = s;
		for ( int i = 0; i < cards.size(); ++i )
		{
			if ( s.getID() == cards.get(i).getID() )
			{
				chosenCardIndex = i;
				break;
			}
		}
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
	
	
	public int canBuild(Player left, Player right)
	{
		if ( chosenCard != null && !wonderBoard.containsCard(chosenCard.getID()) )
		{
			if ( ((chosenCard.getResourceCost().canAfford(getTotalResources())) 
					|| chosenCard.canBuildForFree(wonderBoard))	)
			{
				return 2;
			}
			else if ( neighborsHaveResources(left, right, getChosenCard().getResourceCost()) )
			{
				return 1;
			}
		}
		return 0;
	}
	
	
	public int canBuildStage(Player left, Player right)
	{
		
		if ( wonderBoard.getNextStageCost() != null )
		{
			if ( wonderBoard.getNextStageCost().canAfford(getTotalResources()) )
			{
				return 2;
			}
			else if ( neighborsHaveResources(left, right, wonderBoard.getNextStageCost()) )
			{
				return 1;
			}
		}
		return 0;
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
				cards.remove(chosenCardIndex);
				resources.deductCoins(chosenCard.getResourceCost().getCoins());
				for ( SpecialEffect se : chosenCard.getEffects() )
				{				
					activateBuildEffect(se);
				}
				unavailableResources = new Resources();
				extraResources = new Resources();
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
		cards.remove(chosenCardIndex);
		purchased = new Resources();
		unavailableResources = new Resources();
		extraResources = new Resources();
		for ( SpecialEffect se : chosenCard.getEffects() )
		{				
			activateBuildEffect(se);
		}
		return true;
	}
	
	public boolean buildStage()
	{
		WonderBoardStage stg = wonderBoard.getNextStage();
		wonderBoard.buildStage(chosenCard, getTotalResources());
		cards.remove(chosenCardIndex);
		unavailableResources = new Resources();
		extraResources = new Resources();
		for ( SpecialEffect se : stg.getEffects() )
		{				
			if ( se.getID() == CoinBonus.CoinBonusID )
				((CoinBonus)se).acquireCoins(resources); 
			else if ( se.getID() == VictoryPointBonus.VictoryPointBonusID )
				((VictoryPointBonus)se).acquireVictoryPoints(this);
			else if ( se.getID() == ShieldBonus.ShieldBonusID )
				((ShieldBonus)se).acquireShields(this);
		}
		return true;
	}
	
	public boolean buildStage(Player leftNeighbor, Player rightNeighbor, int preference)
	{
		WonderBoardStage stg = wonderBoard.getNextStage();
		buyResources(leftNeighbor, rightNeighbor, wonderBoard.getNextStageCost(), preference);
			
		wonderBoard.buildStage(chosenCard, Resources.addResources(purchased, Resources.addResources(unavailableResources, getResources())));
		cards.remove(chosenCardIndex);
		purchased = new Resources();
		unavailableResources = new Resources();
		extraResources = new Resources();
		for ( SpecialEffect se : stg.getEffects() )
		{				
			if ( se.getID() == CoinBonus.CoinBonusID )
				((CoinBonus)se).acquireCoins(resources); 
			else if ( se.getID() == VictoryPointBonus.VictoryPointBonusID )
				((VictoryPointBonus)se).acquireVictoryPoints(this);
			else if ( se.getID() == ShieldBonus.ShieldBonusID )
				((ShieldBonus)se).acquireShields(this);
		}
		return true;
	}
	
	public void discard(ArrayList<Structure> discardedCards)
	{
		resources.addCoins(3); 
		cards.remove(chosenCardIndex);
		discardedCards.add(chosenCard);
		unavailableResources = new Resources();
		extraResources = new Resources();
	}
	
	//this function must be called after a player has selected their card on the last turn of an age
	public void discardHand(ArrayList<Structure> discardedCards)
	{
		discardedCards.addAll(cards);
		cards.clear();
	}
	
	
	public boolean freeBuild()
	{
		for ( WonderBoardStage stg: wonderBoard.getStages() )
		{
			System.out.println("====stage i====");
			if ( stg.isBuilt() )
			{
				System.out.println("====stage is built====");
				for ( SpecialEffect se: stg.getEffects() )
				{
					System.out.println("====an effect====");
					if ( se.getID() == FreeConstruction.FreeConstructionID )
					{
						return ((FreeConstruction)se).canBuildForFree();
					}
				}
			}
		}
		return false;
	}
	
	
	public void activateBuildEffect(SpecialEffect se)
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
		for ( WonderBoardStage stg: wonderBoard.getStages() )
		{
			for ( SpecialEffect se: stg.getEffects() )
			{
				if ( se.getID() == TradingPerks.TradingPerksID )
				{
					perks.add(((TradingPerks)se));
				}
			}
		}
		sumup = TradingPerks.addAll(perks);
		
		//finds what resources need to be purchased from neighbors
		Resources missing = (Resources.addResources(extraResources, Resources.addResources(resources, unavailableResources))).findMissingResources(required);
		
		if ( preference != 1 || preference != 0 )
			val = r.nextInt(2);
		else val = preference;
			
		ArrayList<Integer> revenue = purchased.buyResources(leftNeighbor, rightNeighbor, missing, sumup, val);
		leftNeighbor.getOwnedResources().addCoins((int)revenue.get(0));
		rightNeighbor.getOwnedResources().addCoins((int)revenue.get(1));
		resources.deductCoins((int)revenue.get(0) + (int)revenue.get(1));
		//purchased.deductCoins(purchased.getCoins());
		
		return true;
	}
	
	//checks if his neighbors have enough resources to fulfill the missing ones and if the player can afford to pay for them
	public boolean neighborsHaveResources(Player leftNeighbor, Player rightNeighbor, Resources required)
	{
		if ( required.getCoins() == required.getCount() ) return false;
		
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
		for ( WonderBoardStage stg: wonderBoard.getStages() )
		{
			for ( SpecialEffect se: stg.getEffects() )
			{
				if ( se.getID() == TradingPerks.TradingPerksID )
				{
					perks.add(((TradingPerks)se));
				}
			}
		}
		sumup = TradingPerks.addAll(perks);
		
		Resources missing = (getTotalResources()).findMissingResources(required);
		
		int price = sumup.bulkPrice(missing, leftNeighbor.getResources(), rightNeighbor.getResources());
		
		if ( Resources.addResources(leftNeighbor.getResources(), rightNeighbor.getResources()).clearCoins().hasRequiredResources(missing)
				 && price <= resources.getCoins() )
			return true;
		return false;
	}
	
	public int calculateVictoryPoints()
	{
		int vpoints = 0;
		
		vpoints += victoryPoints;
		vpoints += getConflictTokens().getVictoryPoints();
		vpoints += (int)Math.floor(getResources().getCoins() / 3);
		vpoints += getScientificSymbols().victoryPointsValue();
		
		return vpoints;
	}
	
	public void updatePlayer(Player p)
	{
		currentAge = p.currentAge;
		isAI = p.isAI;
		scientificSymbols = p.scientificSymbols;
		victoryPoints = p.victoryPoints;
		conflictTokens = p.conflictTokens;
		shields = p.shields;
		resources = p.resources;
		extraResources = p.extraResources;
		purchased = p.purchased;
		unavailableResources = p.unavailableResources;
		chosenCard = p.chosenCard;
		chosenCardIndex = p.chosenCardIndex;
		cards.clear();
		cards.addAll(p.getCards());
		wonderBoard.updateWonderBoard(p.getWonderBoard());
	}
}
