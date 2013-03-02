package server;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


import Resources.Match;
import Resources.Packet.*;

import com.esotericsoftware.kryo.Kryo;

import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.minlog.Log;



public class MServer {
	
	private Server server;
	private ArrayList<Object> list;
	private ArrayList<Match> matchList;
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
	public void createMatch(){		
		matchList.add(new Match());
//		for(Match m: matchList)
//			System.out.println(m.getMatch_ID());
	}
	
	
	/********************************
	 * bridgeClient
	 * given a connect and match id
	 * add client to the match list
	 * @param c
	 * @param m_id
	 */
	
	//To be modified
	public void bridgeClient(Connection c, long m_id){
		for(Match m : matchList){
			if(m.getMatch_ID()==m_id){
				m.addConnection(c);
			}
		}
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
		kryo.register(java.util.ArrayList.class);
		kryo.register(Match.class);	
	}
	
	public ArrayList<Object> getConnected(){
		return list;
	}
	public ArrayList<Match> getMatchList(){
		return matchList;
	}
	
	public ArrayList<Long> getMatchID_List(){
		ArrayList<Long> idList = new ArrayList<Long>();
		for(Match e: matchList)
			idList.add(e.getMatch_ID());
		return idList;
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

