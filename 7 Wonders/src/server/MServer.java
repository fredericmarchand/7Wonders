package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

import Controls.Match1;
import Controls.Match2;
import Player.AIPlayer;
import Player.Intermediate;
import Player.Moderate;
import Player.Simple;
import Player.Strategy;
import Player.User;
import Resources.Match;
import Resources.Packet.*;

import com.esotericsoftware.kryo.Kryo;

import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.minlog.Log;

@SuppressWarnings("unused")
public class MServer {

	private Server server;
	private ArrayList<Object> list;
	private ArrayList<Match> matchList;
	private static long client_ID = 100000;
	Scanner scanner = new Scanner(System.in);

	public MServer() throws IOException {

		server = new Server(16382, 16382);
		
		list = new ArrayList<Object>();
		matchList = new ArrayList<Match>();

		registerPackets();

		NetworkListener nl = new NetworkListener();
		nl.init(server, this);
		server.addListener(nl);
		System.out.println("Input server port (60001 recommended): ");
		server.bind(Integer.parseInt((scanner.next())));
		server.start();

	}
	
	public ArrayList<String> getLobbyList(){
		ArrayList<String> lobbyList = new ArrayList<String>();
		String matchName;
		for(Match e : matchList){
			matchName = (e.getMatch_ID() + " | " + e.getHostUName()  
					+ " 's match  | " + e.getConnectionCount() +
					"/" + e.getMaxPlayerCount() + " players");
			lobbyList.add(matchName);
		}
		return lobbyList;		
	}

	public long createMatch(int h, int ai, long hid, String hname, int aiDiff) {
		Match m = new Match(h, ai,this,hid,hname,aiDiff);
		matchList.add(m);
		return m.getMatch_ID();
	}

	public boolean bridgeClient(Connection c, long m_id, Object k,Object v) {
		for (Match m : matchList) {
			if (m.getMatch_ID() == m_id) {
				if (m.getConnectionCount() < m.getMaxPlayerCount()) {
					m.addConnection(c, k, v);
					return true;
				}
			}
		}
		return false;
	}

	public boolean removeClient(Connection c, long m_id, Object k) {
		for (Match m : matchList) {
			if (m.getMatch_ID() == m_id) {
				m.removeConnection(c, k);
				if (m.getHumanConnectionCount() == 0) {
					ListIterator<Match> it = getMatchList().listIterator();
					while (it.hasNext()) {
						Match match = it.next();
						if (m.getHumanConnectionCount() == 0) {
							it.remove();
							System.out
									.println("[SERVER] No one in match  - DELETED ");
						}
					}
				}
				return true;
			}
		}
		return false;
	}

	public ArrayList<Object> getConnected() {
		return list;
	}

	public ArrayList<Match> getMatchList() {
		return matchList;
	}

	public long getID() {
		return client_ID;
	}

	public void incID() {
		++client_ID;
	}

	public ArrayList<Long> getMatchID_List() {
		ArrayList<Long> idList = new ArrayList<Long>();
		for (Match e : matchList)
			idList.add(e.getMatch_ID());
		return idList;
	}

	// return the match given a match id
	public Match findMatch(long id) {
		for (Match e : matchList) {
			if (e.getMatch_ID() == id)
				return e;
		}
		return null;
	}

	public boolean containsMatch(long id) {
		for (Match e : matchList) {
			if (e.getMatch_ID() == id)
				return true;
		}
		return false;
	}
	
	public void updateMatch(long mid){
		findMatch(mid).update();
	}

	// any class type sent over the network must be registered to the kryo
	// generic types are implicitly registered
	public void registerPackets() throws IOException {
		Kryo kryo = server.getKryo();
		kryo.register(Packet0LoginRequest.class);
		kryo.register(Packet1LoginAnswer.class);
		kryo.register(Packet2MatchListRequest.class);
		kryo.register(Packet3Connection.class);
		kryo.register(Packet4Object.class);
		kryo.register(Packet5Disconnect.class);
		kryo.register(Packet6ChatMsg.class);
		kryo.register(Packet7MatchFunction.class);
		kryo.register(Packet8ClientResponse.class);
		kryo.register(Packet9StartMatch.class);
		kryo.register(Packet10EndMatch.class);
		kryo.register(Packet11ImmediateStart.class);
		kryo.register(Packet12CreateMatch.class);
		kryo.register(Packet13MatchJoinRequest.class);
		kryo.register(Packet14HostCreateMatch.class);
		kryo.register(Packet15MatchDisconnect.class);
	
		kryo.register(java.util.ArrayList.class);
		kryo.register(Match1.class);
		kryo.register(Match2.class);
		kryo.register(User.class);
//
//		kryo.register(Structures.Structure.class);
//		kryo.register(Structures.Cards.Academy.class);
//		kryo.register(Structures.Cards.Altar.class);
//		kryo.register(Structures.Cards.Apothecary.class);
//		kryo.register(Structures.Cards.Aqueduct.class);
//		kryo.register(Structures.Cards.ArcheryRange.class);
//		kryo.register(Structures.Cards.Arena.class);
//		kryo.register(Structures.Cards.Arsenal.class);
//		kryo.register(Structures.Cards.Barracks.class);
//		kryo.register(Structures.Cards.Baths.class);
//		kryo.register(Structures.Cards.Bazar.class);
//		kryo.register(Structures.Cards.Brickyard.class);
//		kryo.register(Structures.Cards.BuildersGuild.class);
//		kryo.register(Structures.Cards.Caravansery.class);
//		kryo.register(Structures.Cards.ChamberOfCommerce.class);
//		kryo.register(Structures.Cards.Circus.class);
//		kryo.register(Structures.Cards.ClayPit.class);
//		kryo.register(Structures.Cards.ClayPool.class);
//		kryo.register(Structures.Cards.Courthouse.class);
//		kryo.register(Structures.Cards.CraftmensGuild.class);
//		kryo.register(Structures.Cards.Dispensary.class);
//		kryo.register(Structures.Cards.EastTradingPost.class);
//		kryo.register(Structures.Cards.Excavation.class);
//		kryo.register(Structures.Cards.ForestCave.class);
//		kryo.register(Structures.Cards.Fortifications.class);
//		kryo.register(Structures.Cards.Forum.class);
//		kryo.register(Structures.Cards.Foundry.class);
//		kryo.register(Structures.Cards.Gardens.class);
//		kryo.register(Structures.Cards.Glassworks.class);
//		kryo.register(Structures.Cards.GuardTower.class);
//		kryo.register(Structures.Cards.Haven.class);
//		kryo.register(Structures.Cards.Laboratory.class);
//		kryo.register(Structures.Cards.Library.class);
//		kryo.register(Structures.Cards.Lighthouse.class);
//		kryo.register(Structures.Cards.Lodge.class);
//		kryo.register(Structures.Cards.Loom.class);
//		kryo.register(Structures.Cards.LumberYard.class);
//		kryo.register(Structures.Cards.MagistratesGuild.class);
//		kryo.register(Structures.Cards.Marketplace.class);
//		kryo.register(Structures.Cards.Mine.class);
//		kryo.register(Structures.Cards.Observatory.class);
//		kryo.register(Structures.Cards.OreVein.class);
//		kryo.register(Structures.Cards.Palace.class);
//		kryo.register(Structures.Cards.Pantheon.class);
//		kryo.register(Structures.Cards.PawnShop.class);
//		kryo.register(Structures.Cards.PhilosophersGuild.class);
//		kryo.register(Structures.Cards.Press.class);
//		kryo.register(Structures.Cards.Quarry.class);
//		kryo.register(Structures.Cards.Sawmill.class);
//		kryo.register(Structures.Cards.School.class);
//		kryo.register(Structures.Cards.ScientistsGuild.class);
//		kryo.register(Structures.Cards.Scriptorium.class);
//		kryo.register(Structures.Cards.Senate.class);
//		kryo.register(Structures.Cards.ShipownersGuild.class);
//		kryo.register(Structures.Cards.SiegeWorkshop.class);
//		kryo.register(Structures.Cards.SpiesGuild.class);
//		kryo.register(Structures.Cards.Stables.class);
//		kryo.register(Structures.Cards.Statue.class);
//		kryo.register(Structures.Cards.Stockade.class);
//		kryo.register(Structures.Cards.StonePit.class);
//		kryo.register(Structures.Cards.StrategistsGuild.class);
//		kryo.register(Structures.Cards.Study.class);
//		kryo.register(Structures.Cards.Tavern.class);
//		kryo.register(Structures.Cards.Temple.class);
//		kryo.register(Structures.Cards.Theater.class);
//		kryo.register(Structures.Cards.TimberYard.class);
//		kryo.register(Structures.Cards.TownHall.class);
//		kryo.register(Structures.Cards.TradersGuild.class);
//		kryo.register(Structures.Cards.TrainingGround.class);
//		kryo.register(Structures.Cards.TreeFarm.class);
//		kryo.register(Structures.Cards.University.class);
//		kryo.register(Structures.Cards.Vineyard.class);
//		kryo.register(Structures.Cards.Walls.class);
//		kryo.register(Structures.Cards.WestTradingPost.class);
//		kryo.register(Structures.Cards.WorkersGuild.class);
//		kryo.register(Structures.Cards.Workshop.class);
//
//		kryo.register(Structures.Effects.BuildDiscardedCard.class);
//		kryo.register(Structures.Effects.CardCoinBonus.class);
//		kryo.register(Structures.Effects.CardVictoryPointBonus.class);
//		kryo.register(Structures.Effects.CoinBonus.class);
//		kryo.register(Structures.Effects.CopyGuild.class);
//		kryo.register(Structures.Effects.FreeConstruction.class);
//		kryo.register(Structures.Effects.MilitaryDefeatBonus.class);
//		kryo.register(Structures.Effects.PlayLastCard.class);
//		kryo.register(Structures.Effects.ResourceChoice.class);
//		kryo.register(Structures.Effects.ResourcesBonus.class);
//		kryo.register(Structures.Effects.ScientificSymbolBonus.class);
//		kryo.register(Structures.Effects.ShieldBonus.class);
//		kryo.register(Structures.Effects.SpecialEffect.class);
//		kryo.register(Structures.Effects.TradingPerks.class);
//		kryo.register(Structures.Effects.VictoryPointBonus.class);
//		kryo.register(Structures.Effects.WonderStageCoinBonus.class);
//		kryo.register(Structures.Effects.WonderStageVictoryPointBonus.class);
//
//		kryo.register(Tokens.ConflictTokens.class);
//		kryo.register(Tokens.Resources.class);
//		kryo.register(Tokens.ScientificSymbols.class);
//
//		kryo.register(Player.Player.class);
//
//		kryo.register(WonderBoards.WonderBoard.class);
//		kryo.register(WonderBoards.WonderBoardStage.class);
//
//		kryo.register(WonderBoards.Boards.TheColossusOfRhodes.class);
//		kryo.register(WonderBoards.Boards.TheHangingGardensOfBabylon.class);
//		kryo.register(WonderBoards.Boards.TheLighthouseOfAlexandria.class);
//		kryo.register(WonderBoards.Boards.TheMausoleumOfHalicarnassus.class);
//		kryo.register(WonderBoards.Boards.ThePyramidsOfGiza.class);
//		kryo.register(WonderBoards.Boards.TheStatueOfZeusInOlympia.class);
//		kryo.register(WonderBoards.Boards.TheTempleOfArtemisInEphesus.class);

//		kryo.register(Controls.CommandMessage.class);

//		kryo.register(AIPlayer.class);
//		kryo.register(Strategy.class);
//		kryo.register(Simple.class);
//		kryo.register(Moderate.class);
//		kryo.register(Intermediate.class);
//
//		kryo.register(java.util.Random.class);
//
//		kryo.register(java.util.concurrent.atomic.AtomicLong.class);


	}

	public static void main(String[] args) {
		try {
			new MServer();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
