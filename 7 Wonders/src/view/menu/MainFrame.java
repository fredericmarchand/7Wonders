package view.menu;

import image.Images;

import javax.swing.*;

import view.game.LoadingPanel;
import view.game.MatchPanel;

import Controls.Match2;
import Controls.NetworkGameController;

import java.awt.*;

public class MainFrame extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private NetworkGameController controller;
	private MatchPanel panel;
	private LoadingPanel loading;
	private StartPanel startMenu;
	private CreateMatchPanel cmp;
	private LobbyPanel lp;
	private WaitingPanel wp;
	
	public MainFrame() {
		setTitle("7 Wonders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JFrame temp = new JFrame();
		temp.pack();
		Insets insets = temp.getInsets();
		temp = null;
		setSize(1280+insets.left-2, 860+insets.bottom-2);
		setLocationRelativeTo(null);
		setResizable(false);
		setBackground(Color.white);
		
		loading = new LoadingPanel();
		loading.setVisible(true);
		
		startMenu = new StartPanel(this);
		startMenu.setVisible(false);
		
		add(startMenu);
		add(loading);
		this.setVisible(true);
		
		run();
	}
	
	public void update() {
		panel.update();
	}
	
	public void updateValues() {
		panel.updateValues();
	}
	
	public void startMatch(Match2 m) {
		if(wp != null) remove(wp);
		panel = new MatchPanel(m, controller);
		add(panel);
		update();
	}

	@Override
	public void run() {
		Images.setGUI(loading);
		Images.run();
		loading.setVisible(false);
		startMenu.setVisible(true);
		startMenu.run();
	}	

	public void startController(NetworkGameController c) {
		controller = c;
		controller.setMainFrame(this);
		controller.run();
	}
	
	public void launchWaiting(WaitingPanel p) {
		if(wp != null) remove(wp);
		wp = p;
		wp.setVisible(true);
		add(wp);
		wp.repaint();
	}
	
	public void hideWaiting() {
		if(wp != null) remove(wp);
	}
	
	public void launchCreateMenu(CreateMatchPanel p) {
		if(cmp != null) remove(cmp);
		cmp = p;
		cmp.setVisible(true);
		add(cmp);
	}
	
	public void launchLobby(LobbyPanel p) {
		if(lp != null) remove(lp);
		lp = p;
		lp.setVisible(true);
		startMenu.setVisible(false);
		add(lp);
	}
	
	public void hideMatchPanel(){
		if(panel != null) remove(panel);
	}
	
	public void updateMatchPanel(){
		if(panel != null) panel.repaint();
	}
	public void updateLobbyPanel(){
		if(lp!=null) lp.repaint();
	}
	
	public static void main(String args[]) {
		new MainFrame();
	}
}
