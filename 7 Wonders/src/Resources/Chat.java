package Resources;
import client.MClient;

public class Chat {
	
	private ChatFrame chatFrame;
	MClient client;	
	
	public Chat(MClient c){
		client = c;
				
	}
	
	public MClient getClient(){
		return client;
	}
	
	public void addChat(String s){
		chatFrame.appendChat(s);
	}
	
	public void launchChatFrame(boolean b){
		chatFrame = new ChatFrame(this, client.getUser_username());
		chatFrame.setStart(b);
		chatFrame.showGUI();
	}
	
	public void sendMsg(String s){
		client.sendChat(s);
	}
	

	public void kill(){
		chatFrame.clear();
		chatFrame.setVisible(false);
	}
	
	public void setStart(boolean b){
		chatFrame.setStart(b);
	}

}
