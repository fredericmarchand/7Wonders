package client;

import java.io.IOException;
import java.util.*;

import view.menu.MainFrame;

import Controls.*;

import Player.*;

import Resources.linkNetworkView;
import Resources.Packet.*;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;
//import com.esotericsoftware.minlog.Log;


//kill mclient once frames have been closed.
public class MClient {
	private Client client;
	private boolean host = false;
	private ArrayList<String> matchList;
	// if client has yet to join a game
	// match ID is 0
	private long matchID = 0000;
	private long ID = 0000;
	private String username;
	private boolean inMatch = false;
	private linkNetworkView link;
	
	private User user;

	private MainFrame mainframe;
	
	public MClient(MainFrame m) {
		
		mainframe = m;
		
		matchList = new ArrayList<String>();
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

	public void setMatchList(ArrayList<String> list) {
		matchList = list;
	}

	public ArrayList<String> getMatchList() {
		return matchList;
	}
	public linkNetworkView getLink() {
		return link;
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

	public void createUser() {
		System.out.println("[CLIENT] Creating user");
		System.out.println("[CLIENT] MClient : \t" + this);
		user = new Player(username, ID);
		user.setClient(this);
		System.out.println("[CLIENT] User MClient : \t"+ user.getClient());
	}
	
	public MainFrame getMainFrame() {
		return mainframe;
	}

	
	//transmission methods///
	public boolean serverConnect(String ip, int port) {
		boolean connect = false;
		try {
			connect= true;
			client.connect(5000, ip, port);
			
		} catch (IOException e) {

			return false;
		}
		client.sendTCP(new Packet0LoginRequest());
		return connect;
	}


	public void sendCommandMessage(ArrayList<Integer> m, boolean last){
		System.out.println("[CLIENT] Sending command message \n\t" + m);
		//for(Integer i : m)
		//	System.out.println(i);
		Packet8ClientResponse packet = new Packet8ClientResponse();
		packet.setCID(ID);
		packet.setMID(matchID);
		packet.setObject(m);
		packet.setBoolean(last);
		client.sendTCP(packet);
	}


	public void sendCreateMatchRequest(int human, int ai, int dif) {
		System.out.println("[CLIENT]Sending Create request" );
		Packet12CreateMatch packet = new Packet12CreateMatch();
		packet.setHuman(human);
		packet.setAI(ai);
		packet.setUName(username);
		packet.setAIDiff(dif);
		packet.setCID(ID);
		client.sendTCP(packet);
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
			quit.setCID(ID);
			quit.setUName(username);
			client.sendTCP(quit);
			matchID = 0000; // no longer in a game
			sendMatchListRequest();
			if(inMatch)
				mainframe.hideMatchPanel();
			inMatch = !inMatch;
			mainframe.launchLobby(link.launchLobby());
			
		}
	}

	public void sendChat(String s) {
		Packet6ChatMsg msg = new Packet6ChatMsg();
		//String _msg = ("[" + username + "." + ID + "]" + " " + s);
		String _msg = ("[" + username + "]: " + s);
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

		rPacket.setCID(ID);
		rPacket.setMID(Long.parseLong((mname.split(" | " ))[0]));
		rPacket.setUName(username);

		client.sendTCP(rPacket);

	}
	
	//signal user

	public void startMatch() {
		link.getChat().setStart(false);
		inMatch = true;
		user.startMatch();
		mainframe.updateMatchPanel();
		
	}
	
	public void pushToUser(ArrayList<Integer> devonsShittyList1,
			   ArrayList<Long> devonsShittyList2,
			   ArrayList<String	> devonsShittyList3){
		user.receive(devonsShittyList1,devonsShittyList2,devonsShittyList3);
	}


	// return to lobby
	public void returnToLobby(){};
	public void pushUserQuit(){
		
		for(String s : matchList)
			System.out.println("[CLIENT] MATCH LIST:\t" + s);
		link.killChatFrame();
		//mainframe.hideMatchPanel();
		
		//if user has not joined a game
		//error going from match lobby to game lobby
		//mainframe.hideMatchPanel();
		//mainframe.launchLobby(link.launchLobby());
		link.updateLobby(matchList);
	}
	


	// any class type sent over the network must be registered to the kryo
	// generic types are implicitly registered
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

		
		kryo.register(java.util.ArrayList.class);
		kryo.register(Match1.class);	
		kryo.register(Match2.class);
		kryo.register(User.class);

	}


}
