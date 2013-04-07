package client;

import java.io.IOException;
import java.util.*;

import Controls.*;

import Player.*;

import Resources.linkNetworkView;
import Resources.Packet.*;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.minlog.Log;


//kill mclient once frames have been closed.
public class MClient {
	private Client client;
	private boolean host = false;
	private ArrayList<Long> matchList;
	// if client has yet to join a game
	// match ID is 0
	private long matchID = 0000;
	private long ID = 0000;
	private String username;
	
	private linkNetworkView link;
	
	private User user;

	public MClient() {
		matchList = new ArrayList<Long>();
		client = new Client(16382,16382);
		NetworkListener nl = new NetworkListener(this);
		link = new linkNetworkView(this);

		client.addListener(nl);
		client.start();
		register();

	}

	// get/set host values
	public void setHost(boolean h) {
		host = h;
	}

	public boolean getHost() {
		return host;
	}

	// get/set ID values
	public void setID(long _ID) {
		ID = _ID;
	}

	public long getID() {
		return ID;
	}

	// set match id to which client has joined
	public void setMID(long id) {
		matchID = id;
	}

	public long getMID() {
		return matchID;
	}

	public void setMatchList(ArrayList<Long> list) {
		matchList = list;
	}

	public ArrayList<Long> getMatchList() {
		return matchList;
	}
	public linkNetworkView getLink() {
		return link;
	}

	// getter for chat
	public void setUser(User u) {
		user = u;
	}

	public User getUser() {
		return user;
	}

	public void setUser_username(String s) {
		username = s;
	}

	public String getUser_username() {
		return username;
	}

	public void setUser_ID(long id) {
	}

	public void createUser() {
		System.out.println("[CLIENT] Creating user");
		user = new Player(username, ID);
		user.setClient(this);
	}
	
	public void sendCommandMessage(CommandMessage m){
		System.out.println("[CLIENT] Sending command message \n\t" + m);
		Packet8ClientResponse packet = new Packet8ClientResponse();
		packet.setCID(ID);
		packet.setMID(matchID);
		packet.setObject(m);
		client.sendTCP(packet);
	}

	public void serverConnect(String ip, int port) {
		try {
			client.connect(5000, ip, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			client.stop();
			link.failConnect();
		}
		client.sendTCP(new Packet0LoginRequest());
	}

	public void sendCreateMatchRequest(int human, int ai) {
		System.out.println("[CLIENT]Sending Create request" );
		Packet12CreateMatch packet = new Packet12CreateMatch();
		user = new User(username, ID);
		packet.setHuman(human);
		packet.setAI(ai);
		packet.setUser(user);
		packet.setCID(ID);
		client.sendTCP(packet);
	}
	
	// any class type sent over the network must be registered to the kryo
	// generic types are implicitly registered

	public void pushToUser(ArrayList<Object> devonsShittyList1,
						   ArrayList<Object> devonsShittyList2,
						   ArrayList<Object> devonsShittyList3){
		//user.receive(devonsShittyList1,devonsShittyList2,devonsShittyList3);
	}
	
	public void sendStartRequest() {
		Packet11ImmediateStart packet = new Packet11ImmediateStart();
		packet.setMID(matchID);
		client.sendTCP(packet);
	}

	public void quitMatch() {
		if (matchID > 0) {
			Packet5Disconnect quit = new Packet5Disconnect();
			quit.setMID(matchID);
			quit.setUser(user);
			client.sendTCP(quit);
			matchID = 0000; // no longer in a game
			link.launchLobby();
		}
	}

	public void sendChat(String s) {
		Packet6ChatMsg msg = new Packet6ChatMsg();
		String _msg = ("[" + username + "." + ID + "]" + " " + s);
		msg.setMsg(_msg);
		msg.setCID(ID);
		msg.setuName(username);
		msg.setMID(matchID);
		client.sendTCP(msg);
	}

	public void sendMatchListRequest() {
		Packet2MatchListRequest packet = new Packet2MatchListRequest();
		packet.setObject("LIST");
		client.sendTCP(packet);
	}

	// send request packets to server

	
	//request to join match
	public void sendMatchRequest(String mname) {
		Packet13MatchJoinRequest rPacket = new Packet13MatchJoinRequest();
		//rPacket.setObject(user);
		user = new User(username,ID);
		rPacket.setCID(ID);
		rPacket.setMID(Long.parseLong(mname));
		rPacket.setUser(user);
		client.sendTCP(rPacket);

	}

	// /HEAVY EDIT////
	public void turn(Object o) {
				
		user.updateMatch((Match2)o);
				
	}


	public void startMatch() {
		//link.getChat().countdown();
		user.startMatch();

	}

	public void sendUserInfo(){
		Packet16UserObject packet = new Packet16UserObject();
		user = new User(username,ID);
		packet.setUser(user);
		client.sendTCP(packet);
	}
	// return to lobby
	
	
	public void returnToLobby() {
		quitMatch();
		// return list?
		// send list request packet
		// receive list
		// set as some variable

	}


	public void register() {
		Kryo kryo = client.getKryo();
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
		kryo.register(Packet16UserObject.class);
		kryo.register(Packet17PlayerObject.class);
		
		kryo.register(java.util.ArrayList.class);
		kryo.register(Match1.class);	
		kryo.register(Match2.class);
		kryo.register(User.class);
		
		kryo.register(Structures.Structure.class);
		kryo.register(Structures.Cards.Academy.class);
		kryo.register(Structures.Cards.Altar.class);
		kryo.register(Structures.Cards.Apothecary.class);
		kryo.register(Structures.Cards.Aqueduct.class);
		kryo.register(Structures.Cards.ArcheryRange.class);
		kryo.register(Structures.Cards.Arena.class);
		kryo.register(Structures.Cards.Arsenal.class);
		kryo.register(Structures.Cards.Barracks.class);
		kryo.register(Structures.Cards.Baths.class);
		kryo.register(Structures.Cards.Bazar.class);
		kryo.register(Structures.Cards.Brickyard.class);
		kryo.register(Structures.Cards.BuildersGuild.class);
		kryo.register(Structures.Cards.Caravansery.class);
		kryo.register(Structures.Cards.ChamberOfCommerce.class);
		kryo.register(Structures.Cards.Circus.class);
		kryo.register(Structures.Cards.ClayPit.class);
		kryo.register(Structures.Cards.ClayPool.class);
		kryo.register(Structures.Cards.Courthouse.class);
		kryo.register(Structures.Cards.CraftmensGuild.class);
		kryo.register(Structures.Cards.Dispensary.class);
		kryo.register(Structures.Cards.EastTradingPost.class);
		kryo.register(Structures.Cards.Excavation.class);
		kryo.register(Structures.Cards.ForestCave.class);
		kryo.register(Structures.Cards.Fortifications.class);
		kryo.register(Structures.Cards.Forum.class);
		kryo.register(Structures.Cards.Foundry.class);
		kryo.register(Structures.Cards.Gardens.class);
		kryo.register(Structures.Cards.Glassworks.class);
		kryo.register(Structures.Cards.GuardTower.class);
		kryo.register(Structures.Cards.Haven.class);
		kryo.register(Structures.Cards.Laboratory.class);
		kryo.register(Structures.Cards.Library.class);
		kryo.register(Structures.Cards.Lighthouse.class);
		kryo.register(Structures.Cards.Lodge.class);
		kryo.register(Structures.Cards.Loom.class);
		kryo.register(Structures.Cards.LumberYard.class);
		kryo.register(Structures.Cards.MagistratesGuild.class);
		kryo.register(Structures.Cards.Marketplace.class);
		kryo.register(Structures.Cards.Mine.class);
		kryo.register(Structures.Cards.Observatory.class);
		kryo.register(Structures.Cards.OreVein.class);
		kryo.register(Structures.Cards.Palace.class);
		kryo.register(Structures.Cards.Pantheon.class);
		kryo.register(Structures.Cards.PawnShop.class);
		kryo.register(Structures.Cards.PhilosophersGuild.class);
		kryo.register(Structures.Cards.Press.class);
		kryo.register(Structures.Cards.Quarry.class);
		kryo.register(Structures.Cards.Sawmill.class);
		kryo.register(Structures.Cards.School.class);
		kryo.register(Structures.Cards.ScientistsGuild.class);
		kryo.register(Structures.Cards.Scriptorium.class);
		kryo.register(Structures.Cards.Senate.class);
		kryo.register(Structures.Cards.ShipownersGuild.class);
		kryo.register(Structures.Cards.SiegeWorkshop.class);
		kryo.register(Structures.Cards.SpiesGuild.class);
		kryo.register(Structures.Cards.Stables.class);
		kryo.register(Structures.Cards.Statue.class);
		kryo.register(Structures.Cards.Stockade.class);
		kryo.register(Structures.Cards.StonePit.class);
		kryo.register(Structures.Cards.StrategistsGuild.class);
		kryo.register(Structures.Cards.Study.class);
		kryo.register(Structures.Cards.Tavern.class);
		kryo.register(Structures.Cards.Temple.class);
		kryo.register(Structures.Cards.Theater.class);
		kryo.register(Structures.Cards.TimberYard.class);
		kryo.register(Structures.Cards.TownHall.class);
		kryo.register(Structures.Cards.TradersGuild.class);
		kryo.register(Structures.Cards.TrainingGround.class);
		kryo.register(Structures.Cards.TreeFarm.class);
		kryo.register(Structures.Cards.University.class);
		kryo.register(Structures.Cards.Vineyard.class);
		kryo.register(Structures.Cards.Walls.class);
		kryo.register(Structures.Cards.WestTradingPost.class);
		kryo.register(Structures.Cards.WorkersGuild.class);
		kryo.register(Structures.Cards.Workshop.class);

		kryo.register(Structures.Effects.BuildDiscardedCard.class);
		kryo.register(Structures.Effects.CardCoinBonus.class);
		kryo.register(Structures.Effects.CardVictoryPointBonus.class);
		kryo.register(Structures.Effects.CoinBonus.class);
		kryo.register(Structures.Effects.CopyGuild.class);
		kryo.register(Structures.Effects.FreeConstruction.class);
		kryo.register(Structures.Effects.MilitaryDefeatBonus.class);
		kryo.register(Structures.Effects.PlayLastCard.class);		
		kryo.register(Structures.Effects.ResourceChoice.class);
		kryo.register(Structures.Effects.ResourcesBonus.class);
		kryo.register(Structures.Effects.ScientificSymbolBonus.class);
		kryo.register(Structures.Effects.ShieldBonus.class);
		kryo.register(Structures.Effects.SpecialEffect.class);
		kryo.register(Structures.Effects.TradingPerks.class);
		kryo.register(Structures.Effects.VictoryPointBonus.class);
		kryo.register(Structures.Effects.WonderStageCoinBonus.class);
		kryo.register(Structures.Effects.WonderStageVictoryPointBonus.class);
		
		kryo.register(Tokens.ConflictTokens.class);
		kryo.register(Tokens.Resources.class);
		kryo.register(Tokens.ScientificSymbols.class);
		
		kryo.register(Player.class);
		
		kryo.register(WonderBoards.WonderBoard.class);
		kryo.register(WonderBoards.WonderBoardStage.class);
		
		kryo.register(WonderBoards.Boards.TheColossusOfRhodes.class);
		kryo.register(WonderBoards.Boards.TheHangingGardensOfBabylon.class);
		kryo.register(WonderBoards.Boards.TheLighthouseOfAlexandria.class);
		kryo.register(WonderBoards.Boards.TheMausoleumOfHalicarnassus.class);
		kryo.register(WonderBoards.Boards.ThePyramidsOfGiza.class);
		kryo.register(WonderBoards.Boards.TheStatueOfZeusInOlympia.class);
		kryo.register(WonderBoards.Boards.TheTempleOfArtemisInEphesus.class);
		
		kryo.register(Controls.CommandMessage.class);
		
		kryo.register(AIPlayer.class);
		kryo.register(Strategy.class);
		kryo.register(Simple.class);
		kryo.register(Moderate.class);
		kryo.register(Intermediate.class);
		
		kryo.register(java.util.Random.class);
		
		kryo.register(java.util.concurrent.atomic.AtomicLong.class);
	}

	public static void main(String[] args) {

		Log.set(Log.LEVEL_TRACE);
		
	}

}
