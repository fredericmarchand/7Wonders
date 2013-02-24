package blackboxserver;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;

public class Match {
	
	ArrayList<Connection> connected;
    private long match_id;
    private static long counter = 1000;
    
    public Match(){
    	connected = new ArrayList<Connection>();
		match_id = ++counter;
    }
//	public Match(long i){
//		connected = new ArrayList<Connection>();
//		match_id = i;
//	}

	public ArrayList<Connection> getConnections(){return connected;}	
	public void addConnection(Connection c){ connected.add(c);}
	public void removeConnection(Connection c) { connected.remove(c);}
	public long getMatch_ID(){return match_id;}
	
}
