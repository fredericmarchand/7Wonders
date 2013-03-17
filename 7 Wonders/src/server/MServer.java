package server;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


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
	
	public MServer() throws IOException{
		
		server = new Server();
		list = new ArrayList<Object>();
		matchList = new ArrayList<Match>();
		
		registerPackets();
		
		
		NetworkListener nl = new NetworkListener();
		nl.init(server, this);
		server.addListener(nl);
		System.out.println("Input ");
		server.bind(Integer.parseInt((scanner.next())));
		server.start();
		
	}
	public long createMatch(){		
		Match m = new Match();
		matchList.add(m);
		//m.setServerMatchCommunication(server);
//		for(Match m: matchList)
//			System.out.println(m.getMatch_ID());
		return m.getMatch_ID();
	}
	
	
	/********************************
	 * bridgeClient
	 * given a connect and match id
	 * add client to the match list
	 * @param c
	 * @param m_id
	 */
	
	//To be modified
	public boolean bridgeClient(Connection c, long m_id){
		for(Match m : matchList){
			if(m.getMatch_ID()==m_id){
				if(m.getConnectionCount()<Match.MAX_PLAYER_COUNT){
					m.addConnection(c);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean removeClient(Connection c, long m_id){
		for(Match m : matchList){
			if(m.getMatch_ID()==m_id){
				m.removeConnection(c);
				return true;
			}
		}
		return false;		
	}

	public ArrayList<Object> getConnected(){return list;}
	public ArrayList<Match> getMatchList(){return matchList;}
	public long getID(){return client_ID;}
	public void incID(){++client_ID;}
	
	
	
	public ArrayList<Long> getMatchID_List(){
		ArrayList<Long> idList = new ArrayList<Long>();
		for(Match e: matchList)
			idList.add(e.getMatch_ID());
		return idList;
	}
	//return the match given a match id
	public Match findMatch(long id){
		for(Match e : matchList){
			if(e.getMatch_ID()==id)
				return e;
		}
		return null;
	}
	public boolean containsMatch(long id){
		for(Match e : matchList){
			if(e.getMatch_ID()==id)
				return true;
		}
		return false;
	}
	//any class type sent over the network must be registered to the kryo
	//generic types are implicitly registered
	public void registerPackets() throws IOException{
		Kryo kryo = server.getKryo();
		kryo.register(Packet0LoginRequest.class);
		kryo.register(Packet1LoginAnswer.class);
		kryo.register(Packet2Message.class);
		kryo.register(Packet3Connection.class);
		kryo.register(Packet4Object.class);
		kryo.register(Packet5Disconnect.class);
		kryo.register(Packet6ChatMsg.class);
		kryo.register(Packet7MatchFunction.class);
		kryo.register(Packet8ClientResponse.class);
		kryo.register(Packet9StartMatch.class);
		kryo.register(Packet10EndMatch.class);
		kryo.register(Packet11ImmediateStart.class);
		kryo.register(java.util.ArrayList.class);
		kryo.register(Match.class);	
	}
	
	public static void main(String[] args){
		try {
			
			new MServer();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}

