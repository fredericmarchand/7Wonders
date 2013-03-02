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
		}
		if(o instanceof Packet3Connection){
			System.out.println("Packet3Connection Received");
		}
		if(o instanceof Packet4Object){
			System.out.println("[CLIENT] PACKET 4");
			if(((Packet4Object) o).getID()==1){
				//list = (ArrayList<Long>)((Packet4Object)o).getObject();
				for(Long e : (ArrayList<Long>)((Packet4Object)o).getObject())
					System.out.println(Long.toString(e));
//				mclient.joinMatch(list);
			}
		}
		
		// TODO Auto-generated method stub
		//super.received(arg0, arg1);
	}

}
