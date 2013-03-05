package Resources;

import java.util.ArrayList;

import com.esotericsoftware.kryonet.Connection;

public class Match {
	
	ArrayList<Connection> connected;
    private long match_id;
    private static long counter = 1000;
    public static final int MAX_PLAYER_COUNT = 7;
    private int connection_count;
    
    public Match(){
    	connected = new ArrayList<Connection>();
		match_id = ++counter;
    }

	public ArrayList<Connection> getConnections(){return connected;}	
	public void addConnection(Connection c){
		connected.add(c);
		update();
	}
	public void removeConnection(Connection c) { 
		connected.remove(c);
		update();
	}
	public long getMatch_ID(){return match_id;}
	public int getConnectionCount(){return connection_count;}
	public void update(){
		connection_count = connected.size();
		System.out.println(connection_count);
		if(connection_count==MAX_PLAYER_COUNT){
			System.out.println("Starting Game!");
			//countDown();
		}
	}
	public boolean countDown(){
		System.out.println("[MATCH] STARTING IN T-MINUS 30 SECONDS");
		for(int i = 30; i > 0;i--){
			if(connection_count!=MAX_PLAYER_COUNT){
				System.out.println("Someone has disconnected");
				return false;
			}
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return true;
	}	
}
