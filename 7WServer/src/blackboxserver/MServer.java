package blackboxserver;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;


import blackboxserver.Packet.*;

public class MServer {
	
	private Server server;
	ArrayList<Object> list;
	ArrayList<Match> matchList;
	Scanner scanner = new Scanner(System.in);
	
	public MServer() throws IOException{
		server = new Server();
		list = new ArrayList<Object>();
		matchList = new ArrayList<Match>();
		
		registerPackets();
		
		
		NetworkListener nl = new NetworkListener();
		nl.init(server, this);
		server.addListener(nl);
		server.bind(Integer.parseInt((scanner.next())));
		server.start();
		
	}
	public void createMatch(){
		matchList.add(new Match());
		for(Object m: matchList)
			System.out.println(m);
	}
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
	
	
	public static void main(String[] args){
		try {
			new MServer();
			Log.set(Log.LEVEL_TRACE);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	}

