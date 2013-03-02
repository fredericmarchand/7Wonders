package View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		setTitle("7 Wonders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);
		setResizable(true);
		setBackground(Color.white);
		add(new ClientPanel());
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame m = new MainFrame();
		m.setVisible(true);
	}

}
