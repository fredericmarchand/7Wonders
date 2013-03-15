package Resources;
import client.MClient;

public class Chat {
	private ChatFrame chatFrame;
	MClient client ;	
	public Chat(MClient c){
		chatFrame = new ChatFrame(this);
		client = c;
	}
	
	public void addChat(String s){
		chatFrame.appendChat(s);
	}
	public void launchChatFrame(){
		chatFrame.createAndShowGUI();
	}
	public void sendMsg(String s){
		client.sendChat(s);
	}
}
