package Resources;
import client.MClient;

public class Chat {
	private ChatFrame chatFrame;
	MClient client ;	
	public Chat(MClient c){
		client = c;
		chatFrame = new ChatFrame(this , c.getUser_username());		
	}
	
	public MClient getClient(){return client;}
	
	public void addChat(String s){
		chatFrame.appendChat(s);
	}
	public void launchChatFrame(){
		chatFrame.createAndShowGUI();
	}
	public void sendMsg(String s){
		client.sendChat(s);
	}
	
	public void countdown(){		
		for(int i = 10; i > 0;i--){
			chatFrame.appendChat("[MATCH] STARTING ");
		}
	}
}
