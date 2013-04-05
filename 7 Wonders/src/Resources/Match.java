package Resources;

import java.util.ArrayList;

import server.MServer;

//import server.MServer;



import Controls.CommandMessage;
import Controls.Match2;
import Player.Player;
import Player.User;
import Resources.Packet.Packet10EndMatch;
import Resources.Packet.Packet7MatchFunction;
import Resources.Packet.Packet8ClientResponse;
import Resources.Packet.Packet9StartMatch;


import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Server;

public class Match {
	
	ArrayList<Connection> connected;
	ArrayList<User> userList;
    ArrayList<CommandMessage> cmdMsgList;
    private long match_id;
    private static long counter = 1000;
    private int MAX_PLAYER_COUNT = 7 ;
    private int connection_count;
    private int receivedEvents = 0;
    private int nHum;
    private int nAI;

    private boolean inProgress;
    MServer server;
    private Match2 controller;
    
    public Match(int h,int ai, MServer m){
    	nHum = h;
    	nAI = ai;
    	server = m;
    	userList = new ArrayList<User>();
    	connected = new ArrayList<Connection>();
		match_id = ++counter;
		cmdMsgList = new ArrayList<CommandMessage>();
		inProgress = false;
		MAX_PLAYER_COUNT = h+ai;
		connection_count+=ai;
				
    }
    public int getMaxPlayerCount() {return MAX_PLAYER_COUNT;}
    public boolean get_inProgress(){return inProgress;}
	public ArrayList<Connection> getConnections(){return connected;}	
	public void addConnection(Connection c, Object o){
		userList.add((User)o);		
		connected.add(c);		
		connection_count++;
		update();
	}
	public void removeConnection(Connection c, Object o) {
		userList.remove((User)o);
		connected.remove(c);
		connection_count--;
		update();
	}
	public void removeConnectionOnly(Connection c){
		connected.remove(c);
		update();
	}
	public long getMatch_ID(){return match_id;}
	public int getConnectionCount(){return connection_count;}
	public void update(){
		//connection_count=connected.size();
		System.out.println(connection_count);
		if(connection_count==MAX_PLAYER_COUNT){
			System.out.println("Starting Game!");
			startMatch();
		}
	}

	public void generateAI(){
		long idAI;
		String usernameAI;
		for(int i = 0; i<nAI;i++){
			idAI= server.getID();
			server.incID();
			usernameAI = "JP the evil frenchmen"; 
			controller.addAIPlayer(idAI, usernameAI);
			update();
		}
	}

	public boolean contains(Connection c){
		if(connected.contains(c))
			return true;
		return false;
	}
	
	//getters and setters for controller
	
	public void sendMatchInfo(Object o){
		Packet7MatchFunction gamePacket = new Packet7MatchFunction(); 
		gamePacket.setObject(o);

		for(Connection c: connected){
			c.sendTCP(gamePacket);
		}
	}
	
	public void receiveEvent(CommandMessage m, long cID){
		cmdMsgList.add(m);
		receivedEvents++;
		if(receivedEvents==connection_count){				
			sendMatchInfo(controller.dispatch(cmdMsgList));			
		}
	}
	
	public void handOff(Packet8ClientResponse receivedPacket){		
		receiveEvent((CommandMessage)receivedPacket.getObject(), receivedPacket.getCID());
	}	
	public void startMatch(){
		controller = new Match2(userList);
		//generateAI(nAI);	
		inProgress = true;
		sendStartMatchRequest();
	}
	
	public void endMatch(){
		sendEndMatchRequest();
	}
	public void sendStartMatchRequest(){
		Packet9StartMatch start = new Packet9StartMatch();
		start.setObject(controller);
		
			for(Connection c: connected){
				c.sendTCP(start);
			}
			
			
	}
	
	public void sendEndMatchRequest(){
		Packet10EndMatch end = new Packet10EndMatch();
		for(Connection c: connected){
			c.sendTCP(end);
		}
	}
}
