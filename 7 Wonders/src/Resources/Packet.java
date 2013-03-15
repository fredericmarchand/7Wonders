package Resources;

//create packets to communicate from the server to anyone connected to the server
//EACH packet represents a different type of communication
//Packet4Object is the most complex and should be used for sending playing moves to the 
//server

public class Packet {
	public static class Packet0LoginRequest{}
	public static class Packet1LoginAnswer{
		private boolean accepted = false;
		private long idvalue;
		public boolean getAccepted(){return accepted;}
		public void setAccepted(boolean b){accepted = b;}
		public void setIDValue(long id){idvalue = id;}
		public long getIDValue(){return idvalue;}		
	}
	public static class Packet2Message{
		private Object o;
		public Object getObject(){ return o;}
		public void setObject(Object ob){ o = ob;}
	}
	public static class Packet3Connection{
		private boolean accepted = false;
		private long idvalue;
		public boolean getAccepted(){return accepted;}
		public void setAccepted(boolean b){accepted = b;}
		public void setIDValue(long id){idvalue = id;}
		public long getIDValue(){return idvalue;}				
	}
	public static class Packet4Object{
		//id ::
		// 0 = null
		// 1 = matchlist
		// 2 = client match id
		// 3 = client move
		// 4 = client card
		// 5 = host responsibilies
		private Object o;
		private int id = 0;
		public Object getObject(){ return o;}
		public void setObject(Object ob){ o = ob;}
		public int getID(){return id;}
		public void setID(int i){id = i;}
	}
	public static class Packet5Disconnect{
		//if client sends one of these
		//eliminate from any current game
		private long matchID;
		public long getMID(){return matchID;}
		public void setMID(long id){matchID = id;}		
	}
	public static class Packet6ChatMsg{
		private String msg;
		private long matchID;
		private long clientID;
		private String uName;
		public String getMsg(){ return msg;}
		public void setMsg(String s){ msg = s;}
		public long getMID(){return matchID;}
		public void setMID(long id){matchID = id;}
		public long getCID(){return clientID;}
		public void setCID(long id){clientID=id;}
		public String getuName(){return uName;}
		public void setuName(String n){uName = n;}
	}
	public static class Packet7MatchFunction{
		private long matchID;
		private long clientID;
		private Object o;
		public long getMID(){return matchID;}
		public void setMID(long id){matchID = id;}
		public long getCID(){return clientID;}
		public void setCID(long id){clientID=id;}
		public Object getObject(){ return o;}
		public void setObject(Object ob){ o = ob;}
	}
	public static class Packet8ClientResponse{
		private long matchID;
		private long clientID;
		private Object o;
		public long getMID(){return matchID;}
		public void setMID(long id){matchID = id;}
		public long getCID(){return clientID;}
		public void setCID(long id){clientID=id;}
		public Object getObject(){ return o;}
		public void setObject(Object ob){ o = ob;}
	}
	public static class Packet9StartMatch{}
	public static class Packet10EndMatch{}

}
