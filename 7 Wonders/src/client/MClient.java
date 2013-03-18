package client;

import java.io.IOException;
import java.util.*;


import Controls.*;



import Player.Player;
import Player.User;
import Resources.Chat;

import Resources.linkNetworkView;
import Resources.Packet.*;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.minlog.Log;

public class MClient {
    private Client client;
    private static Scanner s = new Scanner(System.in);
    public boolean GAME_ALIVE;
    private boolean host = false;
    private ArrayList<Long> matchList;
    //if client has yet to join a game 
    //match ID is 0
    private long matchID = 0000;
    private long ID = 0000;
    private String username;
    private Chat chat;
    private User user;
    private linkNetworkView link;
    private Player player;
    Controls.Match1 m;
        
    //player receive game
    public MClient(){
    	matchList = new ArrayList<Long>();
    	client = new Client();
    	NetworkListener nl = new NetworkListener(this);
    	
    	link = new linkNetworkView(this);
    	
    	client.addListener(nl);
    	client.start();
    	register();
    	
//    	System.out.println("Please enter the specified IP!");
//		String x = s.next();
//		System.out.println("Please enter the specified Port!");
//		int p = Integer.parseInt(s.next());
//    	
///********************Connect*******************************/
//		try {
//			client.connect(5000,"127.0.0.1",25565);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			client.stop();
//		}
//		client.sendTCP(new Packet0LoginRequest());
/********************Connect*******************************/    	
    	//command();
    	
    }
    
    public void command(){
    	//Test code
    	//user inputs ip address as well as forwarded port
    	//exchanges exception packet 
    	//while the game is still alive the server will continue
    	//to except user input
    	GAME_ALIVE = true;

	        while(GAME_ALIVE){
	        	System.out.println(matchID);
	        	String m = s.next();
	        	if(!m.equals("JOIN")&&matchID==0){
		            Packet2Message mpackage = new Packet2Message();
			        mpackage.setObject(m);
			        client.sendTCP(mpackage);
		        }else if(m.equals("JOIN")){
		        	Packet4Object game_id = new Packet4Object();
		        	game_id.setID(2);
		        	game_id.setObject(s.next());
		        	client.sendTCP(game_id);      	
		        	chat.launchChatFrame();
		        	//figure out fix
		        }else if (m.equals("QUIT")&&matchID!=0){
		        	quitMatch();
	        	}else if(matchID!=(long)0){
		        	System.out.println("Sending message");
		        	Packet6ChatMsg msg = new Packet6ChatMsg();
		        	msg.setMsg(m);
		        	msg.setCID(ID);
		        	msg.setuName(username);
		        	msg.setMID(matchID);
		        	client.sendTCP(msg);
		        }
	        }
	        //immitate turn taking

    }
    
    public void sendCreateMatchRequest(int human,int ai){
//    	Packet2Message mpackage = new Packet2Message();
//        mpackage.setObject("CREATE");
//        mpackage.setObject2(x);
    	Packet12CreateMatch packet = new Packet12CreateMatch();
    	packet.setHuman(human);
    	packet.setAI(ai);
        client.sendTCP(packet);
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
    
    public void setMatchList(ArrayList<Long> list){matchList = list;}
    public ArrayList<Long>getMatchList(){return matchList;}
    
    //getter for chat
    public void setUser(User u){user = u;}
    
    public linkNetworkView getLink(){return link;}
    public User getUser(){return user;}
    public void setUser_username(String s){username = s;}
    public void setUser_ID(long id){}
    public void createUser(String uname, long id){
    	user = new User(uname, id);
    }
    
    public void serverConnect(String ip, int port){
    	try {
			client.connect(5000,ip,port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			client.stop();
		}
    	client.sendTCP(new Packet0LoginRequest());
    }
    

  //any class type sent over the network must be registered to the kryo
  	//generic types are implicitly registered

    public void sendStartRequest(){
    	Packet11ImmediateStart packet = new Packet11ImmediateStart();
    	packet.setMID(matchID);
    	client.sendTCP(packet);
    }
    public void quitMatch(){
    	if(matchID>0){
    		Packet5Disconnect quit = new Packet5Disconnect();
    		quit.setMID(matchID);
    		client.sendTCP(quit);
    		matchID = 0000; //no longer in a game
    	}
    }
    
    public void sendChat(String s){
    	Packet6ChatMsg msg = new Packet6ChatMsg();
    	String _msg = ("[" + username + "."+ID+"]" + " " + s);
    	msg.setMsg(_msg);
    	msg.setCID(ID);
    	msg.setuName(username);
    	msg.setMID(matchID);
    	client.sendTCP(msg);
    }
    
    public void sendMatchListRequest(){
    	Packet2Message packet = new Packet2Message();
    	packet.setObject("LIST");
    	client.sendTCP(packet);
    }

    
    //send request packets to server
    
    public void sendMatchRequest(String mname){
    	Packet4Object game_id = new Packet4Object();
    	game_id.setID(2);
    	game_id.setObject(mname);
    	client.sendTCP(game_id);      	
  	
    }
    //public void sendClientMatch
    /*
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */
       
    ///HEAVY EDIT////
    public void turn(Packet7MatchFunction o){
//    	player.passMatch((Controls.Match)(o.getObject()));
//       	response(player.getCommandMsg());
    	//player....
    	
    	// if(player.getMatchComplete?
    	// new Packet5
    	//set info
    	//send tcp
    }
    public void response(CommandMessage msg){
    	Packet8ClientResponse resp = new Packet8ClientResponse();
    	resp.setCID(ID);
    	resp.setMID(matchID);
    	resp.setObject(msg);
    	client.sendTCP(resp);
    }
    
    public void startMatch(){
    	 m = new Controls.Match1();
    	 player = new Player();
    	
    	//link.getChat().countdown();
    	link.launchMainFrame( m,player);
    	//load match
    }
    
    //return to lobby
    public void returnToLobby(){
    	quitMatch();
    	//return list?	
    		//send list request packet
    		//receive list
    		//set as some variable 
    	
    }

    public void register(){
    	Kryo kryo = client.getKryo();
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
		kryo.register(Packet12CreateMatch.class);
    	kryo.register(java.util.ArrayList.class);
    	kryo.register(Match1.class);
   }
	public static void main(String[] args) {
		

		Log.set(Log.LEVEL_TRACE);

	}

	

}
