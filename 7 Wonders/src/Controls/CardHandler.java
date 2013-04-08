package Controls;

import Structures.*;
import Structures.Cards.*;
import WonderBoards.*;
import WonderBoards.Boards.*;
import Player.Player;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class CardHandler {

	public CardHandler()
	{
	}
	
	public static WonderBoard getWonderBoardByID(int id, int side)
	{
		switch ( id )
		{
			case 1: return new TheColossusOfRhodes(side);
			case 2: return new TheLighthouseOfAlexandria(side);
			case 3: return new TheTempleOfArtemisInEphesus(side);
			case 4: return new TheHangingGardensOfBabylon(side);
			case 5: return new TheStatueOfZeusInOlympia(side);
			case 6: return new TheMausoleumOfHalicarnassus(side);
			case 7: return new ThePyramidsOfGiza(side);
		}
		return null;
	}
	
	public static Structure getCardByID(int id)
	{
		switch ( id )
		{
			case 1: return new LumberYard();
			case 2: return new StonePit();
			case 3: return new ClayPool();
			case 4: return new OreVein();
			case 5: return new TreeFarm();
			case 6: return new Excavation();
			case 7: return new ClayPit();
			case 8: return new TimberYard();
			case 9: return new ForestCave();	
			case 10: return new Mine();
			case 11: return new Sawmill();
			case 12: return new Quarry();
			case 13: return new Brickyard();
			case 14: return new Foundry();
			case 15: return new Loom();	
			case 16: return new Glassworks();
			case 17: return new Press();
			case 18: return new WorkersGuild();	
			case 19: return new CraftmensGuild();
			case 20: return new TradersGuild();
			case 21: return new PhilosophersGuild();
			case 22: return new SpiesGuild();
			case 23: return new StrategistsGuild();
			case 24: return new ShipownersGuild();	
			case 25: return new ScientistsGuild();
			case 26: return new MagistratesGuild();	
			case 27: return new BuildersGuild();
			case 28: return new PawnShop();
			case 29: return new Baths();
			case 30: return new Altar();
			case 31: return new Theater();
			case 32: return new Aqueduct();
			case 33: return new Temple();
			case 34: return new Statue();
			case 35: return new Pantheon();
			case 36: return new Gardens();
			case 37: return new TownHall();
			case 38: return new Palace();	
			case 39: return new Courthouse();
			case 40: return new Senate();
			case 41: return new Stockade();
			case 42: return new Barracks();
			case 43: return new GuardTower();
			case 44: return new Walls();	
			case 45: return new TrainingGround();
			case 46: return new Fortifications();
			case 47: return new Circus();	
			case 48: return new Arsenal();
			case 49: return new Stables();
			case 50: return new ArcheryRange();	
			case 51: return new SiegeWorkshop();
			case 52: return new Tavern();
			case 53: return new EastTradingPost();
			case 54: return new WestTradingPost();
			case 55: return new Marketplace();
			case 56: return new Forum();
			case 57: return new Caravansery();
			case 58: return new Vineyard();
			case 59: return new Bazar();	
			case 60: return new Haven();
			case 61: return new Lighthouse();
			case 62: return new ChamberOfCommerce();	
			case 63: return new Arena();
			case 64: return new Apothecary();
			case 65: return new Workshop();
			case 66: return new Scriptorium();
			case 67: return new Dispensary();
			case 68: return new Laboratory();
			case 69: return new Library();
			case 70: return new School();
			case 71: return new Lodge();
			case 72: return new Observatory();
			case 73: return new University();
			case 74: return new Academy();
			case 75: return new Study();
		}
		return null;
	}
	
	//builds a deck according to the amount of players participating in the game
	public static ArrayList<Structure> BuildAge1Deck(int numPlayers)
	{
		ArrayList<Structure> deck = new ArrayList<Structure>();
		deck.addAll(FilterCards(numPlayers, 1));
		return deck;
	}
	
	public static ArrayList<Structure> BuildAge2Deck(int numPlayers)
	{
		ArrayList<Structure> deck = new ArrayList<Structure>();
		deck.addAll(FilterCards(numPlayers, 2));
		return deck;
	}
	
	public static ArrayList<Structure> BuildAge3Deck(int numPlayers)
	{
		ArrayList<Structure> deck = new ArrayList<Structure>();
		deck.addAll(FilterCards(numPlayers, 3));
		return deck;
	}
	
	//This creates a deck with all the proper cards for the current age and the number of players in the match
	public static ArrayList<Structure> FilterCards(int numPlayers, int age)
	{
		ArrayList<Structure> deck = new ArrayList<Structure>();
		
		if ( age == 1 )
		{
			deck.add(new Stockade());
			deck.add(new Barracks());
			deck.add(new GuardTower());
			deck.add(new Altar());
			deck.add(new Theater());
			deck.add(new Baths());
			deck.add(new ClayPit());
			deck.add(new LumberYard());
			deck.add(new StonePit());
			deck.add(new TimberYard());
			deck.add(new OreVein());
			deck.add(new ClayPool());
			deck.add(new Press());
			deck.add(new Glassworks());
			deck.add(new Loom());
			deck.add(new WestTradingPost());
			deck.add(new Marketplace());
			deck.add(new EastTradingPost());
			deck.add(new Apothecary());
			deck.add(new Workshop());
			deck.add(new Scriptorium());
			if ( numPlayers >= 4 )
			{
				deck.add(new GuardTower());
				deck.add(new PawnShop());
				deck.add(new OreVein());
				deck.add(new Excavation());
				deck.add(new LumberYard());
				deck.add(new Tavern());
				deck.add(new Scriptorium());
			}
			if ( numPlayers >= 5 )
			{
				deck.add(new Barracks());
				deck.add(new Altar());
				deck.add(new ClayPit());
				deck.add(new StonePit());
				deck.add(new ForestCave());
				deck.add(new Tavern());
				deck.add(new Apothecary());
			}
			if ( numPlayers >= 6 )
			{
				deck.add(new Theater());
				deck.add(new TreeFarm());
				deck.add(new Mine());
				deck.add(new Loom());
				deck.add(new Press());
				deck.add(new Glassworks());
				deck.add(new Marketplace());
			}
			if ( numPlayers == 7 )
			{
				deck.add(new Stockade());
				deck.add(new Baths());
				deck.add(new PawnShop());
				deck.add(new WestTradingPost());
				deck.add(new EastTradingPost());
				deck.add(new Tavern());
				deck.add(new Workshop());
			}
		}
		else if ( age == 2 )
		{
			deck.add(new Walls());
			deck.add(new ArcheryRange());
			deck.add(new Stables());
			deck.add(new Statue());
			deck.add(new Courthouse());
			deck.add(new Aqueduct());
			deck.add(new Temple());
			deck.add(new Foundry());
			deck.add(new Brickyard());
			deck.add(new Sawmill());
			deck.add(new Quarry());
			deck.add(new Press());
			deck.add(new Glassworks());
			deck.add(new Loom());
			deck.add(new Caravansery());
			deck.add(new Forum());
			deck.add(new Vineyard());
			deck.add(new Dispensary());
			deck.add(new Laboratory());
			deck.add(new Library());
			deck.add(new School());
			if ( numPlayers >= 4 )
			{
				deck.add(new TrainingGround());
				deck.add(new Quarry());
				deck.add(new Foundry());
				deck.add(new Sawmill());
				deck.add(new Brickyard());
				deck.add(new Bazar());
				deck.add(new Dispensary());
			}
			if ( numPlayers >= 5 )
			{
				deck.add(new Stables());
				deck.add(new Courthouse());
				deck.add(new Press());
				deck.add(new Glassworks());
				deck.add(new Loom());
				deck.add(new Caravansery());
				deck.add(new Laboratory());
			}
			if ( numPlayers >= 6 )
			{
				deck.add(new TrainingGround());
				deck.add(new ArcheryRange());
				deck.add(new Temple());
				deck.add(new Forum());
				deck.add(new Vineyard());
				deck.add(new Caravansery());
				deck.add(new Library());
			}
			if ( numPlayers == 7 )
			{
				deck.add(new TrainingGround());
				deck.add(new Walls());
				deck.add(new Aqueduct());
				deck.add(new Statue());
				deck.add(new Bazar());
				deck.add(new Forum());
				deck.add(new School());
			}
		}
		else if ( age == 3 )
		{
			deck.add(new Fortifications());
			deck.add(new SiegeWorkshop());
			deck.add(new Arsenal());
			deck.add(new TownHall());
			deck.add(new Pantheon());
			deck.add(new Senate());
			deck.add(new Gardens());
			deck.add(new Palace());
			deck.add(new Haven());
			deck.add(new Arena());
			deck.add(new Lighthouse());
			deck.add(new Academy());
			deck.add(new University());
			deck.add(new Lodge());
			deck.add(new Observatory());
			deck.add(new Study());
			
			//Special handling for guild cards
			ArrayList<Structure> guilds = new ArrayList<Structure>();
			guilds.add(new CraftmensGuild());
			guilds.add(new BuildersGuild());
			guilds.add(new MagistratesGuild());
			guilds.add(new PhilosophersGuild());
			guilds.add(new WorkersGuild());
			guilds.add(new ShipownersGuild());
			guilds.add(new TradersGuild());
			guilds.add(new StrategistsGuild());
			guilds.add(new SpiesGuild());
			guilds.add(new ScientistsGuild());
			Collections.shuffle(guilds);
			for ( int i = 0; i < numPlayers + 2; ++i )
			{
				deck.add(guilds.get(i));
			}
			
			if ( numPlayers >= 4 )
			{
				deck.add(new Circus());
				deck.add(new Arsenal());
				deck.add(new Gardens());
				deck.add(new Haven());
				deck.add(new ChamberOfCommerce());
				deck.add(new University());
			}
			if ( numPlayers >= 5 )
			{
				deck.add(new SiegeWorkshop());
				deck.add(new Circus());
				deck.add(new TownHall());
				deck.add(new Senate());
				deck.add(new Arena());
				deck.add(new Study());
			}
			if ( numPlayers >= 6 )
			{
				deck.add(new Circus());
				deck.add(new TownHall());
				deck.add(new Pantheon());
				deck.add(new Lighthouse());
				deck.add(new ChamberOfCommerce());
				deck.add(new Lodge());
			}
			if ( numPlayers == 7 )
			{
				deck.add(new Fortifications());
				deck.add(new Arsenal());
				deck.add(new Palace());
				deck.add(new Arena());
				deck.add(new Academy());
				deck.add(new Observatory());
			}
		}
		
		Collections.shuffle(deck);
		
		return deck;
	}
	
	//This assigns a hand of cards to each player from the proper deck corresponding to the age
	public static void DistributeCards(ArrayList<Player> players, ArrayList<Structure> deck)
	{
		int index = 0;
		for ( Structure s: deck )
		{
			players.get(index).assignCard(s);
			++index;
			if ( index == players.size() )
				index = 0;
		}
	}
	
	//This handles the assignment of random wonderboards to every player
	public static void DistributeRandomWonderBoards(ArrayList<Player> players, int side)
	{
		ArrayList<WonderBoard> boards = new ArrayList<WonderBoard>();
		boards.add(new TheColossusOfRhodes(side));
		boards.add(new TheHangingGardensOfBabylon(side));
		boards.add(new TheLighthouseOfAlexandria(side));
		boards.add(new TheMausoleumOfHalicarnassus(side));
		boards.add(new TheStatueOfZeusInOlympia(side));
		boards.add(new TheTempleOfArtemisInEphesus(side));
		boards.add(new ThePyramidsOfGiza(side));
		
		Collections.shuffle(boards);
		int index = 0;
		
		for ( Player p: players )
		{
			p.assignWonderBoard(boards.get(index));
			++index;
		}
	}
	
	//This handles the assignment of random wonderboards to every player
	public static void DistributeRandomWonderBoards(ArrayList<Player> players)
	{
		Random r =  new Random();
		ArrayList<WonderBoard> boards = new ArrayList<WonderBoard>();
		boards.add(new TheColossusOfRhodes(r.nextInt(2)));
		boards.add(new TheLighthouseOfAlexandria(r.nextInt(2)));
		boards.add(new TheHangingGardensOfBabylon(0));
		boards.add(new TheMausoleumOfHalicarnassus(r.nextInt(2)));
		boards.add(new TheStatueOfZeusInOlympia(r.nextInt(2)));
		boards.add(new TheTempleOfArtemisInEphesus(r.nextInt(2)));
		boards.add(new ThePyramidsOfGiza(r.nextInt(2)));
		
		//Collections.shuffle(boards);
		int index = 0;
		
		for ( Player p: players )
		{
			p.assignWonderBoard(boards.get(index));
			++index;
		}
	}
	
	//the set of cards of each player is assigned to the neighbor on the left or on the right depending on the age
	public static void PassCardsToNeighbors(ArrayList<Player> players, int age)
	{
		ArrayList<Structure> hold = new ArrayList<Structure>();
		//cards are passed to the right in age 2
		if ( age == 2 )
		{
			hold.addAll(players.get(players.size()-1).getCards());
			for ( int i = players.size()-1; i > 0; --i )
			{
				players.get(i).assignCards(players.get(i-1).getCards());
			}
			players.get(0).assignCards(hold);
		}
		else //cards are passed to the left in ages 1 and 3
		{
			hold.addAll(players.get(0).getCards());
			for ( int i = 0; i < players.size()-1; ++i )
			{
				players.get(i).assignCards(players.get(i+1).getCards());
			}
			players.get(players.size()-1).assignCards(hold);
		}	
	}
	
	public static void main(String[] args) {
//		ArrayList<Player> players = new ArrayList<Player>();
//		players.add(new Player());
//		players.add(new Player());
//		players.add(new Player());
//		players.add(new Player());
//		players.add(new Player());
//		players.add(new Player());
//		players.add(new Player());
//		ArrayList<Structure> deck = BuildAge1Deck(7);
//		DistributeRandomWonderBoards(players, 1);
//		DistributeCards(players, deck);
//		for ( Player p : players )
//		{
//			System.out.println("\n" + p.getWonderBoard().getBoardName());
//			for ( Structure s: p.getCards() )
//			{
//				System.out.println(s.getName());
//			}
//		}
//		
//		PassCardsToNeighbors(players, 2);
//		
//		for ( Player p : players )
//		{
//			System.out.println("\n" + p.getWonderBoard().getBoardName());
//			for ( Structure s: p.getCards() )
//			{
//				System.out.println(s.getName());
//			}
//		}
//		Random r = new Random();
//		for ( int i = 0; i<10; ++i )
//		{
//			System.out.println(r.nextInt(2));
//		}
//		
		//long n = 235395830;
		//System.out.println(n&0xFFFF);
		CommandMessage msg = new CommandMessage();
		msg.setPlayerID(654419);
		CommandMessage g = SevenWondersProtocol.decodeCommandMessage(SevenWondersProtocol.encodeCommandMessage(msg));
		System.out.println(g.getPlayerID());
		
	}

}
