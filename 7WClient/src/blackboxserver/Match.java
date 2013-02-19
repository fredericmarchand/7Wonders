package blackboxserver;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;

public class Match {
	
	ArrayList<Connection> connected;

	public Match(){
		connected = new ArrayList<Connection>();
	}

	public ArrayList<Connection> getConnections(){return connected;}	
	public void addConnection(Connection c){ connected.add(c);}
	public void removeConnection(Connection c) { connected.remove(c);}
	
}
