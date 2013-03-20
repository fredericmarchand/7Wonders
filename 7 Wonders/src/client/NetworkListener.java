package client;

import java.util.ArrayList;


import Resources.Match;
import Resources.Packet.*;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

@SuppressWarnings("unused")
public class NetworkListener extends Listener{
	Client c;
	MClient mclient;
	ArrayList<Match> list;
	
	public NetworkListener(MClient m){
		mclient = m;
	}
	
	public void init(Client c){
		this.c = c;
	}
	
	public void connected(Connection arg0) {
		System.out.println("[CLIENT] Connected");
		// TODO Auto-generated method stub
		//c.sendTCP(new Packet0LoginRequest());
		//super.connected(arg0);
	}
	
	@Override
	public void disconnected(Connection arg0) {
		// TODO Auto-generated method stub
		System.out.println("[CLIENT] Disconnected");
		//super.disconnected(arg0);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void received(Connection c, Object o) {
		System.out.println("[CLIENT] RECEIVED PACKET");
		if(o instanceof Packet1LoginAnswer){
			if(!((Packet1LoginAnswer)o).getAccepted()){
				c.close();
				
			}
			mclient.setMatchList((ArrayList<Long>)((Packet1LoginAnswer)o).getObject());
			mclient.setID(((Packet1LoginAnswer)o).getIDValue());
		}
		//check if client has been able to join game
		//if yes, join lobby
		//if no stay at match list screen
		//
		if(o instanceof Packet3Connection){
			System.out.println("Packet3Connection Received");
			if(((Packet3Connection)o).getAccepted()){
				//join match lobby
				mclient.setMID(((Packet3Connection)o).getIDValue());
				
				mclient.getLink().launchChatFrame();
				System.out.println("[CLIENT] JOINED GAME SUCCESSFULLY");
			}
			else{
				mclient.getLink().failedMatchLobby();
				System.out.println("GAME IS FULL");
		
			}
		}
		if(o instanceof Packet4Object){			
			mclient.getLink().updateLobby((ArrayList<Long>)((Packet4Object)o).getObject());
		}
		if(o instanceof Packet6ChatMsg){
			System.out.println("Received msg packet");
			mclient.getLink().getChat().addChat(((Packet6ChatMsg)o).getMsg());
			//mclient.updateChat(((Packet6ChatMsg)o).getMsg());
		}

		if(o instanceof Packet7MatchFunction){
			mclient.turn((Packet7MatchFunction)o);
		}
		if(o instanceof Packet8ClientResponse){
			
		}
		if(o instanceof Packet9StartMatch){
			mclient.startMatch();
		}
		if(o instanceof Packet14HostCreateMatch){
			mclient.setMID(((Packet14HostCreateMatch)o).getMID());
			mclient.setHost(true);
			if(((Packet14HostCreateMatch)o).getnPlayer()>1){
				mclient.getLink().launchMatchLobby();
				mclient.getLink().launchChatFrame();
			}
		}
	}

}
