package Resources;

import java.util.ArrayList;

public class Chat {
	private ArrayList<String> chatLog;
	
	public Chat(){
		chatLog = new ArrayList<String>();
	}
	
	public void addChat(String s, String uname, long ID){
		chatLog.add("[" + uname + "."+ID+"]" + " " + s);
		System.out.println("[" + uname + "."+ID+"]" + " " + s);
	}
	
	public ArrayList<String> getChatLog(){return chatLog;}
	
//	public void update(){
//		for(String s: chatLog)
//			System.out.println(s);
//	}
}
