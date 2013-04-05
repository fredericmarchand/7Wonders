package View;

import javax.swing.*;

import Controls.Controller;
import Controls.Match2;
import Images.Images;

import java.awt.*;

public class MainFrame extends JFrame implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private MatchPanel panel;
	private LoadingPanel loading;
	
	public MainFrame(Controller c) {
		setTitle("7 Wonders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		controller = c;
		
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
		
		add(loading);
		this.setVisible(true);
		
		run();
	}
	
	public void update() {
		panel.update();
	}
	
	public void startMatch(Match2 m) {
		panel = new MatchPanel(m, controller);
		add(panel);
	}

	@Override
	public void run() {
		Images.setGUI(loading);
		Images.run();
		loading.setVisible(false);
	}

}
