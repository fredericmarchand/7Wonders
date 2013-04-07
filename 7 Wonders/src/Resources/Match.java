package Resources;

import java.util.ArrayList;

import server.MServer;

import Controls.CommandMessage;
import Controls.Match2;

import Controls.SevenWondersProtocol;
import Player.Player;

import Player.User;
import Resources.Packet.Packet10EndMatch;
import Resources.Packet.Packet17PlayerObject;
import Resources.Packet.Packet7MatchFunction;
import Resources.Packet.Packet8ClientResponse;
import Resources.Packet.Packet9StartMatch;


import com.esotericsoftware.kryonet.Connection;

public class Match {

	ArrayList<Connection> connected;
	ArrayList<User> userList;
	ArrayList<CommandMessage> cmdMsgList;
	private long match_id;
	private static long counter = 1000;
	private int MAX_PLAYER_COUNT = 7;
	private int connection_count;
	private int human_connection_count;
	private int receivedEvents = 0;
	private int nAI;

	private boolean inProgress;
	MServer server;
	private Match2 controller;

	public Match(int h, int ai, MServer m) {
		nAI = ai;
		server = m;
		userList = new ArrayList<User>();
		connected = new ArrayList<Connection>();
		match_id = ++counter;
		cmdMsgList = new ArrayList<CommandMessage>();
		inProgress = false;
		MAX_PLAYER_COUNT = h + ai;
		connection_count += ai;
	}

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

	public void addConnection(Connection c, Object o) {
		User u = (User)o;
		System.out.println("[SERVER - MATCH] User: \t" + u);
		userList.add(u);
		connected.add(c);
		connection_count++;
		human_connection_count++;
		update();
	}

	public void removeConnection(Connection c, Object o) {
		userList.remove((User) o);
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
		Packet7MatchFunction packet = new Packet7MatchFunction();		
		packet.setObject(SevenWondersProtocol.encodeMatch(controller));
		for(Connection c: connected){
			c.sendTCP(packet);
		}
	
		packet.setObject(SevenWondersProtocol.encodePlayerIDs(controller));
		for(Connection c: connected){
			c.sendTCP(packet);
		}
		
		packet.setObject(SevenWondersProtocol.encodePlayerNames(controller));
		for(Connection c: connected){
			c.sendTCP(packet);
		}
	}
	public void receiveEvent(CommandMessage m, long cID) {
		cmdMsgList.add(m);
		receivedEvents++;
		if (receivedEvents == human_connection_count) {
			controller.dispatch(cmdMsgList);
			cmdMsgList.clear();
			sendMatchInfo();
		}
	}

	public void handOff(Packet8ClientResponse receivedPacket) {
		receiveEvent((CommandMessage) receivedPacket.getObject(),
				receivedPacket.getCID());
	}

	public void startMatch() {
		controller = new Match2();
		generateAI();
		controller.addPlayers(userList);
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
