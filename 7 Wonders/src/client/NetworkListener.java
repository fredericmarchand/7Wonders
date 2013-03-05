package client;

import java.util.ArrayList;


import Resources.Match;
import Resources.Packet.*;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

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
			if(!((Packet1LoginAnswer)o).getAccepted())
				c.close();
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
				System.out.println("[CLIENT] JOINED GAME SUCCESSFULLY");
			}
			else{
				System.out.println("GAME IS FULL");
				//mclient.command();
			}
		}
		if(o instanceof Packet4Object){			
			switch(((Packet4Object) o).getID()){
				case 0: ;
				case 1: {
							for(Long e : (ArrayList<Long>)((Packet4Object)o).getObject())
								System.out.println(Long.toString(e));
							break;
						}
				case 2: ;					
				case 3: ;
				case 4: ;
				case 5: 
							mclient.setHost((boolean)((Packet4Object)o).getObject());
							if(mclient.getHost())System.out.println("[CLIENT] SET TO HOST");
							//mclient.setAlive(false);
							mclient.chat();
							
						 break;
			default: break;
			}
			//add client to match list
		}

		
		// TODO Auto-generated method stub
		//super.received(arg0, arg1);
	}

}
