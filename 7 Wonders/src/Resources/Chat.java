package Resources;
import client.MClient;

public class Chat implements Runnable {
	private ChatFrame chatFrame;
	MClient client ;	
	public Chat(MClient c){
		client = c;
		//chatFrame = new ChatFrame(this,c.getUser_username());		
	}
	
	
	
	public MClient getClient(){return client;}
	
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
	
	public void countdown(){		
		if(chatFrame == null)
			chatFrame = new ChatFrame(this, client.getUser_username());
		chatFrame.appendChat("[MATCH STARTING]");
		chatFrame.appendChat("[MATCH STARTING]");
		chatFrame.appendChat("[MATCH STARTING]");
		chatFrame.setStart(false);
		for(int i = 10; i > 0;i--){
			    chatFrame.appendChat(String.valueOf(i));
			    try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public void kill(){
		chatFrame.clear();
		chatFrame.setVisible(false);
	}
	public void setStart(boolean b){
		chatFrame.setStart(b);
	}
	public void run(){
		countdown();
	}
}
