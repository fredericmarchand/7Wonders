package View;

import javax.swing.*;

import Controls.Controller;
import Controls.Match1;

import java.awt.*;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Controller controller;
	private MatchPanel panel;
	
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
	}
	
	public void update() {
		panel.update();
	}
	
	public void startMatch(Match1 m) {
		panel = new MatchPanel(m, controller);
		add(panel);
	}
	
	public static void main(String[] args) {
		MainFrame m = new MainFrame(null);
		m.setVisible(true);
	}

}
