package Resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import server.MServer;

import Controls.CommandMessage;
import Controls.GameController;
import Controls.Match2;

import Controls.SevenWondersProtocol;
import Player.Player;
import Resources.Packet.Packet10EndMatch;
import Resources.Packet.Packet7MatchFunction;
import Resources.Packet.Packet9StartMatch;
import Structures.Structure;


import com.esotericsoftware.kryonet.Connection;

public class Match {

	ArrayList<Connection> connected;
	ArrayList<CommandMessage> cmdMsgList;
	ArrayList<Long> receivedClientCMD;
	HashMap<Long, String> userMap;
	
	private long match_id;
	private static long counter = 1000;
	private int MAX_PLAYER_COUNT = 7;
	private int connection_count;
	private int human_connection_count;
	private int receivedEvents = 0;
	private int nAI;
	private String hostName;
	private long hostID;
	private boolean inProgress;
	private MServer server;
	private Match2 controller;

	public Match(int h, int ai, MServer m,long id, String uname){
		nAI = ai;
		server = m;
		hostID = id;
		hostName = uname;
		userMap = new HashMap<Long,String>();
		connected = new ArrayList<Connection>();
		receivedClientCMD = new ArrayList<Long>();
		match_id = ++counter;
		cmdMsgList = new ArrayList<CommandMessage>();
		inProgress = false;
		MAX_PLAYER_COUNT = h + ai;
		connection_count += ai;
		controller = new Match2();
	}

	public String getHostUName(){return hostName;}
	public Long getHostID(){return hostID;}
	
	public int getMaxPlayerCount() {
		return MAX_PLAYER_COUNT;
	}

	public boolean get_inProgress() {
		return inProgress;
	}

	public int getConnectionCount() {
		return connection_count;
	}

	public int getHumanConnectionCount() {
		return human_connection_count;
	}
	
	public ArrayList<Connection> getConnections() {
		return connected;
	}

	public void addConnection(Connection c, Object k,Object v) {
		System.out.println("[SERVER - MATCH] User values : \t" + k + "\t" + v  );
		userMap.put((Long)k,(String)v);
		connected.add(c);
		connection_count++;
		human_connection_count++;
	}

	public void removeConnection(Connection c, Object k) {
		userMap.remove(k);
		connected.remove(c);
		connection_count--;
		human_connection_count--;		
	}

	public void removeConnectionOnly(Connection c) {
		connected.remove(c);
		connection_count--;
		human_connection_count--;		
	}

	public long getMatch_ID() {
		return match_id;
	}

	public void update() {
		System.out.println("Server Match update connection count: " + connection_count);
		if (connection_count == MAX_PLAYER_COUNT) {
			System.out.println("Starting Game!");
			startMatch();
		}
	}

	public void generateAI() {
		long idAI;
		System.out.println("Number of AI: " + nAI);
		String usernameAI;
		for (int i = 0; i < nAI; i++) {
			idAI = server.getID();
			server.incID();
			usernameAI = "JP the evil frenchman";
			controller.addAIPlayer(idAI, usernameAI);
		}
	}

	public boolean contains(Connection c) {
		if (connected.contains(c))
			return true;
		return false;
	}

	// getters and setters for controller

	public void sendMatchInfo() {	
		int id = 0;
		Packet7MatchFunction packet = new Packet7MatchFunction();		
		packet.setObject(SevenWondersProtocol.encodeMatch(controller));
		packet.setID(id);
		for(Connection c: connected){
			c.sendTCP(packet);
		}
	
		packet.setObject(SevenWondersProtocol.encodePlayerIDs(controller));
		packet.setID(++id);
		for(Connection c: connected){
			c.sendTCP(packet);
		}
		
		packet.setObject(SevenWondersProtocol.encodePlayerNames(controller));
		packet.setID(++id);
		for(Connection c: connected){
			c.sendTCP(packet);
		}
	}

	public void receiveEvent(CommandMessage m, long clientID) {

		System.out
				.println("[SERVER] Decoded command message received:  \t" + m);
		cmdMsgList.add(m);
		System.out.println("[SERVER] Event received from client \t"
				+ (++receivedEvents));
		System.out.println("[SERVER] Event status: \t" + receivedEvents + "/"
				+ human_connection_count);
		if (!receivedClientCMD.contains(clientID))
			receivedClientCMD.add(clientID);

		if (receivedClientCMD.size() == human_connection_count) {

			for (Object o : cmdMsgList)
				System.out.println("[SERVER] cmdMsgList \t " + o);
			controller.dispatch(cmdMsgList);
			cmdMsgList.clear();
			receivedClientCMD.clear();
			receivedEvents = 0;
			sendMatchInfo();
		}
		for (Player p : controller.getPlayers()) {
			System.out.print("player " + p + "  cards: [");
			for (Structure s : p.getCards()) {
				System.out.print(s.getName() + ", ");
			}
			System.out.println("]");
		}
		
	}

	public void handOff(ArrayList<Integer> receivedPacket,long id) {
		CommandMessage msg = SevenWondersProtocol.decodeCommandMessage(receivedPacket);

		receiveEvent(msg,id);
	}

	public void startMatch() {
		//controller = new Match2();
		generateAI();
		for(Map.Entry<Long, String> e : userMap.entrySet()){
			controller.addPlayer(e.getKey(),e.getValue());
		}
		controller.init();
		inProgress = true;
		sendStartMatchRequest();
	}

	public void endMatch() {
		sendEndMatchRequest();
	}

	public void sendStartMatchRequest() {
		sendMatchInfo();
		Packet9StartMatch start = new Packet9StartMatch();
		for (Connection c : connected) {
			System.out.println("[SERVER] Sending start request");
			c.sendTCP(start);
		}
	}
	
	

	public void sendEndMatchRequest() {
		Packet10EndMatch end = new Packet10EndMatch();
		for (Connection c : connected) {
			c.sendTCP(end);
		}
	}
}
