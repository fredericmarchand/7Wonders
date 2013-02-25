package client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import resources.Match;
import resources.Packet.Packet0LoginRequest;
import resources.Packet.Packet1LoginAnswer;
import resources.Packet.Packet2Message;
import resources.Packet.Packet3Connection;
import resources.Packet.Packet4Object;


import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

public class MClient {
    private Client client;
    public static Scanner scanner;
    private ArrayList<Object> connectedList;
    public boolean GAME_ALIVE = true;
    
    public MClient(){
    	scanner = new Scanner(System.in);
    	client = new Client();
    	NetworkListener nl = new NetworkListener(this);
    	
    	client.addListener(nl);
    	client.start();
    	register();
    	

    	
    	//Test code
    	//user inputs ip address as well as forwarded port
    	//exchanges exception packet 
    	//while the game is still alive the server will continue
    	//to except user input
    	try {
			Log.info("Please enter the specified IP!");
			String x = scanner.next();
			Log.info("Please enter the specified Port!");
			int p = Integer.parseInt(scanner.next());
			//given 50 seconds to input connect
			//connection fails if no input
			//send request to server
			client.connect(5000,x,p);
			client.sendTCP(new Packet0LoginRequest());
			
			Scanner s = new Scanner(System.in);
	        while(GAME_ALIVE){
	        	String m = s.next();
	        	if(!m.equals("JOIN")){
		            Packet2Message mpackage = new Packet2Message();
			        mpackage.setObject(m);
			        client.sendTCP(mpackage);
		        }else if(m.equals("JOIN")){
		        	Packet4Object game_id = new Packet4Object();
		        	game_id.setID(2);
		        	game_id.setObject(s.next());
		        	client.sendTCP(game_id);
		        	
		        }
	        }
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			client.stop();
		}
    }
    
  //any class type sent over the network must be registered to the kryo
  	//generic types are implicitly registered
    public void register(){
    	Kryo kryo = client.getKryo();
    	kryo.register(Packet0LoginRequest.class);
    	kryo.register(Packet1LoginAnswer.class);
    	kryo.register(Packet2Message.class);
    	kryo.register(Packet3Connection.class);
    	kryo.register(Packet4Object.class);
    	kryo.register(java.util.ArrayList.class);
    	kryo.register(Match.class);
    	
    	
    }
        
    public void joinMatch(ArrayList<Match> l){
    	Log.info("[CLIENT] Listing matches");
    	if(!l.isEmpty()){
	    	ArrayList<Long> id_list = new ArrayList<Long>();
	    	for(Match e : l){
	    		System.out.println(e.getMatch_ID());
	    		id_list.add(e.getMatch_ID());
	    	}
    	
   
	    	Scanner s = new Scanner(System.in);
	    	long input = (long)(Integer.parseInt(s.next()));
	    	while(!id_list.contains(input)){
	    		Log.info("Match not found");
	    		input = (long)(Integer.parseInt(s.next()));
	    	}
	    	//sendTCP of game ID
	    	Packet4Object client_match_id = new Packet4Object();
	    	client_match_id.setID(2);
	    	client_match_id.setObject(input);
	    	client.sendTCP(client_match_id);
    	}
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MClient();
		Log.set(Log.LEVEL_TRACE);

	}

}
