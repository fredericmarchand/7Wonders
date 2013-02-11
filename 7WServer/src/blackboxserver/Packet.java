package blackboxserver;

//create packets to communicate from the server to anyone connected to the server
//

public class Packet {
	public static class Packet0LoginRequest{}
	public static class Packet1LoginAnswer{
		private boolean accepted = false;
		public boolean getAccepted(){return accepted;}
		public void setAccepted(boolean b){accepted = b;}
	}
	public static class Packet2Message{
		private Object o;
		public Object getObject(){ return o;}
		public void setObject(Object ob){ o = ob;}
	}
	public static class Packet3Connection{}
}
