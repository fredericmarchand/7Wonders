package client;

import java.io.IOException;
import java.util.*;



import Resources.Match;
import Resources.Packet.Packet0LoginRequest;
import Resources.Packet.Packet1LoginAnswer;
import Resources.Packet.Packet2Message;
import Resources.Packet.Packet3Connection;
import Resources.Packet.Packet4Object;
import Resources.Packet.Packet5Disconnect;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.minlog.Log;

public class MClient {
    private Client client;
    private static Scanner s = new Scanner(System.in);
    public boolean GAME_ALIVE;
    private boolean host = false;
    
    //if client has yet to join a game 
    //match ID is 0
    private long matchID = 0000;
    private long ID = 0000;
    
    public MClient(){
    	
    	client = new Client();
    	NetworkListener nl = new NetworkListener(this);
    	
    	client.addListener(nl);
    	client.start();
    	register();
    	//System.out.println("Please enter the specified IP!");
		//String x = s.next();
		//System.out.println("Please enter the specified Port!");
		//int p = Integer.parseInt(s.next());
		//given 50 seconds to input connect
		//connection fails if no input
		//send request to server
		try {
			client.connect(5000,"127.0.0.1",25565);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			client.stop();
		}
		client.sendTCP(new Packet0LoginRequest());
    	
    	command();
    	
    }
    
    public void command(){
    	//Test code
    	//user inputs ip address as well as forwarded port
    	//exchanges exception packet 
    	//while the game is still alive the server will continue
    	//to except user input
    	GAME_ALIVE = true;


	        while(GAME_ALIVE){
	        	String m = s.next();
	        	if(m.equals("QUIT")){
		        	System.out.println("MAGIC");
		        	quitMatch();
		        }else if(!m.equals("JOIN")){
		            Packet2Message mpackage = new Packet2Message();
			        mpackage.setObject(m);
			        client.sendTCP(mpackage);
		        }else if(m.equals("JOIN")){
		        	Packet4Object game_id = new Packet4Object();
		        	game_id.setID(2);
		        	game_id.setObject(s.next());
		        	client.sendTCP(game_id);      	
		        	
		        	//figure out fix
		        }
	        }
	        //immitate turn taking

    }
    
    //get/set host values
    public void setHost(boolean h){host = h;}
    public boolean getHost(){return host;}
    
    //get/set ID values
    public void setID(long _ID){ ID = _ID;}
    public long getID(){return ID;}
    
    public void setAlive(boolean a){ GAME_ALIVE = a;}
    public boolean getAlive(){return GAME_ALIVE;}
    
    //set match id to which client has joined
    public void setMID(long id){matchID = id;}
    public long getMID(){return matchID;}
    
    public void ClientWait(){
    	while(true){
    		
    	}
    }
    
    public void turn(){
    	//Queue<Object> queue = new Queue<Object>();
    }
    

    
    public void chat(){

    	while(true){

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
    	kryo.register(Packet5Disconnect.class);
    	kryo.register(java.util.ArrayList.class);
    	kryo.register(Match.class);
   }
    
    public void quitMatch(){
    	System.out.println(matchID);
    	if(matchID>0){
    		System.out.println("QUIT called");
    		Packet5Disconnect quit = new Packet5Disconnect();
    		quit.setMID(matchID);
    		client.sendTCP(quit);
    		matchID = 0000; //no longer in a game
    	}
    }
        

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MClient();
		Log.set(Log.LEVEL_TRACE);

	}

}
