package View;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainFrame() {
		setTitle("7 Wonders");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JFrame temp = new JFrame();
		temp.pack();
		Insets insets = temp.getInsets();
		temp = null;
		setSize(1280+insets.left-2, 860+insets.bottom-2);
		//this.setSize(new Dimension(insets.left + insets.right + 1280, insets.top + insets.bottom + 720));
		//System.out.println(new Dimension(insets.left + insets.right + 1280, insets.top + insets.bottom + 720));
		setLocationRelativeTo( null );
		setResizable(false);
		setBackground(Color.white);
		MatchPanel panel = new MatchPanel(7);
		add(panel);
	}
	
	public static void main(String[] args) {
		MainFrame m = new MainFrame();
		m.setVisible(true);
	}

}
