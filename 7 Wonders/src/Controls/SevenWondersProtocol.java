package Controls;

import java.util.ArrayList;
import Player.Player;
import Structures.Structure;
import WonderBoards.*;
import Tokens.Resources;
import Tokens.ScientificSymbols;
import Structures.Effects.*;

public class SevenWondersProtocol {

	private static int index;
	
	public SevenWondersProtocol()
	{
		
	}
	
	//////////////////////ENCODE SECTION////////////////////
	
	//returns an arraylist of strings containing the usernames of all players
	public static ArrayList<String> encodePlayerNames(Match2 match)
	{
		ArrayList<String> names = new ArrayList<String>();
		for ( Player p: match.getPlayers() )
		{
			names.add(p.getUsername());
		}
		return names;
	}
	
	//returns arraylist of longs containing the ids of all players
	public static ArrayList<Long> encodePlayerIDs(Match2 match)
	{
		ArrayList<Long> ids = new ArrayList<Long>();
		for ( Player p: match.getPlayers() )
		{
			ids.add(p.getID());
		}
		return ids;
	}
	
	public static ArrayList<Integer> encodeCommandMessage(CommandMessage msg)
	{
		ArrayList<Integer> encoding = new ArrayList<Integer>();
		encoding.add((int)msg.getPlayerID()>>16);
		encoding.add((int)msg.getPlayerID()&0xFFFF);
		encoding.add(msg.getMsgType());
		encoding.add(msg.getCardID());
		encoding.add(msg.getAction());
		encoding.add(msg.getPreference());
		encoding.add(msg.getFree());
		encoding.add(msg.getResourceChoices().size());
		for ( Resources r: msg.getResourceChoices() )
		{
			encoding.addAll(encodeResource(r));
		}
		encoding.add(msg.getScientificSymbols().size());
		for ( ScientificSymbols sc: msg.getScientificSymbols() )
		{
			encoding.add(sc.getCompass());
			encoding.add(sc.getGears());
			encoding.add(sc.getTablets());
		}
		return encoding;
	}
	
	
	//encode a match
	public static ArrayList<Integer> encodeMatch(Match2 match)
	{
		ArrayList<Integer> playerStuff = new ArrayList<Integer>();
		playerStuff.add(match.getAge());
		playerStuff.add(match.getTurn());
		playerStuff.add(match.getPlayers().size());
		playerStuff.add(match.getDiscardedCardIDs().size());
		playerStuff.addAll(match.getDiscardedCardIDs());
		for ( Player p: match.getPlayers() )
		{
			playerStuff.addAll(encodePlayer(p));
		}
		return playerStuff;
	}

	//encode a player
	public static ArrayList<Integer> encodePlayer(Player p)
	{
		ArrayList<Integer> playerStuff = new ArrayList<Integer>();
		playerStuff.add(p.getChosenCardIndex());
		playerStuff.add(p.getShields());
		playerStuff.add(p.getVictoryPoints());
		playerStuff.add(p.getConflictTokens().getFiveTokens());
		playerStuff.add(p.getConflictTokens().getThreeTokens());
		playerStuff.add(p.getConflictTokens().getOneTokens());
		playerStuff.add(p.getConflictTokens().getMinusOneTokens());
		playerStuff.add(p.getScientificSymbols().getCompass());
		playerStuff.add(p.getScientificSymbols().getGears());
		playerStuff.add(p.getScientificSymbols().getTablets());
		playerStuff.add(p.getAge());
		playerStuff.add(p.ai() ? 1 : 0);
		playerStuff.add(p.getFreePermission() ? 1 : 0);
		playerStuff.addAll(encodeResource(p.getOwnedResources()));		
		playerStuff.addAll(encodeResource(p.getExtraResources()));
		playerStuff.addAll(encodeResource(p.getPurchasedResources()));
		playerStuff.addAll(encodeResource(p.getUnvResources()));
		playerStuff.add(p.getCards().size());
		for ( Structure s: p.getCards() )
		{
			playerStuff.addAll(encodeStructure(s));
		}
		playerStuff.addAll(encodeWonderBoard(p.getWonderBoard()));
		
		return playerStuff;
	}
	
	//encode a wonderboard
	public static ArrayList<Integer> encodeWonderBoard(WonderBoard wb)
	{
		ArrayList<Integer> boardStuff = new ArrayList<Integer>();
		boardStuff.add(wb.getBoardID());
		boardStuff.add(wb.getSide());
		boardStuff.add(wb.getAllCards().size());
		for ( Structure s: wb.getAllCards() )
		{
			boardStuff.addAll(encodeStructure(s));
		}
		boardStuff.add(wb.getStages().size());
		for ( WonderBoardStage stg: wb.getStages() )
		{
			boardStuff.addAll(encodeStage(stg));
		}
		return boardStuff;
	}
	
	//Encode the stage of a wonderboard
	public static ArrayList<Integer> encodeStage(WonderBoardStage stage)
	{
		ArrayList<Integer> stageStuff = new ArrayList<Integer>();
		stageStuff.add(stage.isBuilt() ? 1 : 0);
		stageStuff.addAll(encodeResource(stage.getResourceCost()));
		stageStuff.add(stage.getEffects().size());
		for ( SpecialEffect sp: stage.getEffects() )
		{
			stageStuff.addAll(encodeSpecialEffect(sp));
		}
		return stageStuff;
	}
	
	//encode a structure
	public static ArrayList<Integer> encodeStructure(Structure s)
	{
		ArrayList<Integer> structStuff = new ArrayList<Integer>();
		structStuff.add(s.getID());
		structStuff.add(s.getEffects().size());
		for ( SpecialEffect sp : s.getEffects() )
		{
			structStuff.addAll(encodeSpecialEffect(sp));
		}
		return structStuff;
	}

	//encode a resource
	public static ArrayList<Integer> encodeResource(Resources r)
	{
		ArrayList<Integer> resourceStuff = new ArrayList<Integer>();
		resourceStuff.add(r.getOre());
		resourceStuff.add(r.getStone());
		resourceStuff.add(r.getWood());
		resourceStuff.add(r.getClay());
		resourceStuff.add(r.getGlass());
		resourceStuff.add(r.getLoom());
		resourceStuff.add(r.getPapyrus());
		resourceStuff.add(r.getCoins());
		return resourceStuff;
	}
	
	//encode a special effect
	public static ArrayList<Integer> encodeSpecialEffect(SpecialEffect sp)
	{
		ArrayList<Integer> spStuff = new ArrayList<Integer>();
		spStuff.add(sp.getID());
		spStuff.add(sp.getType());
		spStuff.add(sp.isUsedUp() ? 1 : 0);
		spStuff.add(sp.activateTime());
		switch ( sp.getID() )
		{
			case 1: 
				spStuff.addAll(encodeResource(((ResourceChoice)sp).getPossibilities()));
				spStuff.add(((ResourceChoice)sp).canBeBought() ? 1 : 0);
				break;
			case 2: 
				spStuff.add(((TradingPerks)sp).getResourceType());
				spStuff.add(((TradingPerks)sp).getPartner());
				spStuff.add(((TradingPerks)sp).manufac() ? 1 : 0);
				spStuff.add(((TradingPerks)sp).primleft() ? 1 : 0);
				spStuff.add(((TradingPerks)sp).primright() ? 1 : 0);
				break;
			case 3: 
				spStuff.add(((CoinBonus)sp).amountOfCoins());
				break;
			case 4: 
				spStuff.add(((CardCoinBonus)sp).getAmountOfCoins());
				spStuff.add(((CardCoinBonus)sp).getCardColor());
				spStuff.add(((CardCoinBonus)sp).includeNeighbors());
				break;
			case 5: 
				spStuff.add(((VictoryPointBonus)sp).amountOfPoints());
				break;
			case 6: 
				spStuff.add(((ShieldBonus)sp).amountOfShields());
				break;
			case 7: 
				spStuff.add(((ScientificSymbolBonus)sp).canChoose()? 1 : 0);
				spStuff.add(((ScientificSymbolBonus)sp).getSymbolType());
				break;
			case 8: 
				spStuff.add(((WonderStageCoinBonus)sp).amountOfCoins());
				break;
			case 9: 
				spStuff.add(((CardVictoryPointBonus)sp).amountOfPoints());
				spStuff.add(((CardVictoryPointBonus)sp).onlyNeighbors());
				spStuff.add(((CardVictoryPointBonus)sp).getCardColor());
				break;
			case 10: //MilitaryDefeat Bonus, nothing to copy
				break;
			case 11: //wonderstage victorypointbonus
				spStuff.add(((WonderStageVictoryPointBonus)sp).amountOfPoints());
				spStuff.add(((WonderStageVictoryPointBonus)sp).includeNeighbors());
				break;
			case 12: 
				spStuff.addAll(encodeResource(((ResourcesBonus)sp).getResources()));
				break;
			case 13: //PlayLastCard nothing to copy
				break;
			case 14: //BuildDiscardedCard nothing to copy
				break;
			case 15: //FreeConstruction nothing to copy
				//spStuff.add(((FreeConstruction)sp).hasBeenUsed() ? 1 : 0);
				break;
			case 16: //copy guild nothing to copy
				break;
		
		}
		return spStuff;
	}
	
	//////////////////DECODE SECTION/////////////////////////
	
	public static void assignUsernamesAndIDs(Match2 match, ArrayList<String> names, ArrayList<Long> ids)
	{
		int i = 0;
		for ( Player p : match.getPlayers() )
		{
			p.setUsername(names.get(i));
			p.setID(ids.get(i));
			++i;
		}
	}
	
	public static CommandMessage decodeCommandMessage(ArrayList<Integer> encoding)
	{
		CommandMessage msg = new CommandMessage();
		index = 0;
		long id = ((encoding.get(index)<<16) | encoding.get(index+1));
		index += 2;
		msg.setPlayerID(id);
		msg.setMsgType(encoding.get(index++));
		msg.setCardID(encoding.get(index++));
		msg.setAction(encoding.get(index++));
		msg.setPreference(encoding.get(index++));
		msg.setFree(encoding.get(index++));
		int s = encoding.get(index++);
		for ( int i = 0; i < s; ++ i )
		{
			msg.getResourceChoices().add(decodeResources(encoding));
		}
		s = encoding.get(index++);
		for( int i = 0; i < s; ++i )
		{
			int comp = encoding.get(index++);
			int gear = encoding.get(index++);
			int tab = encoding.get(index++);
			msg.getScientificSymbols().add(new ScientificSymbols(gear, tab, comp));
		}
		return msg;
	}
	
	//decode match function
	public static Match2 decodeMatch(ArrayList<Integer> encoding)
	{
		Match2 match = new Match2();
		index = 0;
		match.setAge(encoding.get(index++));
		match.setTurn(encoding.get(index++));
		match.setNumPlayers(encoding.get(index++));
		int discardedAmount = encoding.get(index++);
		for ( int i = 0; i < discardedAmount; ++i )
		{
			match.getDiscardedCards().add(CardHandler.getCardByID(encoding.get(index++)));
		}
		for ( int i = 0; i < match.getNumPlayers() ; ++i )
		{
			match.addPlayer(decodePlayer(encoding));
		}
		return match;
	}
	
	
	//decode player from encoding and return it
	public static Player decodePlayer(ArrayList<Integer> encoding)
	{
		Player p = new Player();
		p.setChosenCardIndex(encoding.get(index++));
		p.addShields(encoding.get(index++));
		p.addVictoryPoints(encoding.get(index++));
		p.getConflictTokens().addFiveTokens(encoding.get(index++));
		p.getConflictTokens().addThreeTokens(encoding.get(index++));
		p.getConflictTokens().addOneTokens(encoding.get(index++));
		p.getConflictTokens().addMinusOneTokens(encoding.get(index++));
		p.getScientificSymbols().addCompass(encoding.get(index++));
		p.getScientificSymbols().addGears(encoding.get(index++));
		p.getScientificSymbols().addTablets(encoding.get(index++));
		p.setAge(encoding.get(index++));
		p.setAI(encoding.get(index++));
		p.setFreePermission(encoding.get(index++) == 1 ? true : false);
		p.setOwnedResources(decodeResources(encoding));
		p.setExtraResources(decodeResources(encoding));
		p.setPurchasedResources(decodeResources(encoding));
		p.setUnvResources(decodeResources(encoding));
		
		int cardSize = encoding.get(index++);
		for ( int i = 0; i < cardSize; ++i )
		{
			p.assignCard(decodeStructure(encoding));
		}
		
		p.assignWonderBoard(decodeWonderBoard(encoding));
		return p;
	}
	
	//decode structure from encoding and return it
	public static Structure decodeStructure(ArrayList<Integer> encoding)
	{
		Structure s = CardHandler.getCardByID(encoding.get(index++));
		s.getEffects().clear();
		int effectSize = encoding.get(index++);
		for ( int i = 0; i < effectSize; ++i )
		{
			s.getEffects().add(decodeEffect(encoding));
		}
		return s;
	}
	
	
	//decode resources from encoding and return it
	public static Resources decodeResources(ArrayList<Integer> encoding)
	{
		Resources r =  new Resources();
		r.addOre(encoding.get(index++));
		r.addStone(encoding.get(index++));
		r.addWood(encoding.get(index++));
		r.addClay(encoding.get(index++));
		r.addGlass(encoding.get(index++));
		r.addLoom(encoding.get(index++));
		r.addPapyrus(encoding.get(index++));
		r.addCoins(encoding.get(index++));
		return r;
	}
	
	//decode special effect from encoding and return it
	@SuppressWarnings("unused")
	public static SpecialEffect decodeEffect(ArrayList<Integer> encoding)
	{
		SpecialEffect sp = new SpecialEffect();
		
		int spid = encoding.get(index++);
		int sptype = encoding.get(index++);
		int spused = encoding.get(index++);
		int spact = encoding.get(index++);
		
		switch ( spid )
		{
			case 1: 
				Resources r = decodeResources(encoding);
				int bought = encoding.get(index++);
				sp = new ResourceChoice(r.getOre(), r.getStone(), r.getWood(), r.getClay(), r.getGlass(), r.getLoom(), r.getPapyrus(), bought == 1 ? true : false);
				sp.setters(spused, sptype, spact);
				break;
			case 2: 
				int rtype = encoding.get(index++);
				int partner = encoding.get(index++);
				int manu = encoding.get(index++);//not used but leave there
				int left = encoding.get(index++);//not used but leave there
				int right = encoding.get(index++);//not used but leave there
				sp = new TradingPerks(partner, rtype);
				sp.setters(spused, sptype, spact);
				break;
			case 3: 
				int amount = encoding.get(index++);
				sp = new CoinBonus(amount);
				sp.setters(spused, sptype, spact);
				break;
			case 4: 
				int coins = encoding.get(index++);
				int color = encoding.get(index++);
				int neib = encoding.get(index++);
				sp = new CardCoinBonus(coins, color, neib == 1 ? true : false);
				sp.setters(spused, sptype, spact);
				break;
			case 5: 
				int points = encoding.get(index++);
				sp = new VictoryPointBonus(points);
				sp.setters(spused, sptype, spact);
				break;
			case 6: 
				int shields = encoding.get(index++);
				sp = new ShieldBonus(shields);
				sp.setters(spused, sptype, spact);
				break;
			case 7: 
				int choose = encoding.get(index++);
				int sym = encoding.get(index++);
				sp = new ScientificSymbolBonus(sym, 0, choose == 1? true : false);
				sp.setters(spused, sptype, spact);
				break;
			case 8: 
				int money = encoding.get(index++);
				sp = new WonderStageCoinBonus(money);
				sp.setters(spused, sptype, spact);
				break;
			case 9: 
				int pts = encoding.get(index++);
				int nei = encoding.get(index++);
				int col = encoding.get(index++);
				sp = new CardVictoryPointBonus(pts, nei == 1? true : false, col);
				sp.setters(spused, sptype, spact);
				break;
			case 10: //MilitaryDefeat Bonus, nothing to copy
				sp = new MilitaryDefeatBonus();
				sp.setters(spused, sptype, spact);
				break;
			case 11: 
				int ampts = encoding.get(index++);
				int neibs = encoding.get(index++);
				sp = new WonderStageVictoryPointBonus(ampts, neibs == 1? true : false);
				break;
			case 12: 
				Resources rr = decodeResources(encoding);
				sp = new ResourcesBonus(rr.getOre(), rr.getStone(), rr.getWood(), rr.getClay(), rr.getGlass(), rr.getLoom(), rr.getPapyrus());
				sp.setters(spused, sptype, spact);
				break;
			case 13: //PlayLastCard nothing to copy
				sp = new PlayLastCard();
				sp.setters(spused, sptype, spact);
				break;
			case 14: //BuildDiscardedCard nothing to copy
				sp = new BuildDiscardedCard();
				sp.setters(spused, sptype, spact);
				break;
			case 15: //FreeConstruction nothing to copy
				//int chacha = encoding.get(index++);
				sp = new FreeConstruction();
				//((FreeConstruction)sp).setUsedThisTurn(chacha == 1 ? true : false);
				sp.setters(spused, sptype, spact);
				break;
			case 16: //copy guild nothing to copy
				sp = new CopyGuild();
				sp.setters(spused, sptype, spact);
				break;
		
		}
		
		return sp;
	}
	
	public static WonderBoard decodeWonderBoard(ArrayList<Integer> encoding)
	{
		int wbid = encoding.get(index++);
		int wbside = encoding.get(index++);
		WonderBoard wb = CardHandler.getWonderBoardByID(wbid, wbside);
		
		int cardSize = encoding.get(index++);
		for ( int i = 0; i < cardSize; ++i )
		{
			wb.buildStructure(decodeStructure(encoding));
		}
		
		wb.getStages().clear();
		int stageSize = encoding.get(index++);
		for ( int i = 0; i < stageSize; ++i )
		{
			wb.getStages().add(decodeWonderBoardStage(encoding));
		}
		return wb;
	}
	
	public static WonderBoardStage decodeWonderBoardStage(ArrayList<Integer> encoding)
	{
		WonderBoardStage stg = new WonderBoardStage();
		stg.forceBuild(encoding.get(index++));
		stg.setResourceCost(decodeResources(encoding));
		int spSize = encoding.get(index++);
		for ( int i = 0; i < spSize; ++i )
		{
			stg.getEffects().add(decodeEffect(encoding));
		}
		return stg;
	}
	
	
}



