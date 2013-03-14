package Resources;

import java.util.ArrayList;

import client.MClient;

public class Chat {
	private ArrayList<String> chatLog;
	private ChatFrame chatFrame;
	MClient client ;
	
	public Chat(MClient c){
		chatLog = new ArrayList<String>();
		chatFrame = new ChatFrame(this);
		client = c;
		
	}
	
	public void addChat(String s){
		chatFrame.appendChat(s);
	}
	
	public ArrayList<String> getChatLog(){return chatLog;}
	public void launchChatFrame(){
		chatFrame.createAndShowGUI();
	}
	public void sendMsg(String msg){
		
	}
//	public void update(){
//		for(String s: chatLog)
//			System.out.println(s);
//	}
}
