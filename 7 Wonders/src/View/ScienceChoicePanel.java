package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import Images.Images;
import Tokens.ScientificSymbols;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ScienceChoicePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private JLabel lblOver1, lblOver2, lblOver3;
	private ScienceChoicePanel thisPanel;
	private MatchPanel matchPanel;
	
	public ScienceChoicePanel(MatchPanel m) {
		setLayout(null);
		setSize(1280, 860);
		setBackground(new Color(50, 50, 50, 200));
		
		thisPanel = this;
		matchPanel = m;
		
		lblOver3 = new JLabel("");
		lblOver3.setIcon(Images.sciOverlay3);
		lblOver3.setBounds(664, 386, 65, 88);
		add(lblOver3);
		
		lblOver2 = new JLabel("");
		lblOver2.setIcon(Images.sciOverlay2);
		lblOver2.setBounds(618, 386, 46, 88);
		add(lblOver2);
		
		lblOver1 = new JLabel("");
		lblOver1.setIcon(Images.sciOverlay1);
		lblOver1.setBounds(551, 386, 67, 88);
		add(lblOver1);
		
		JLabel lblPurpose = new JLabel("Choose a symbol:", SwingConstants.CENTER);
		lblPurpose.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPurpose.setBounds(560, 363, 159, 22);
		add(lblPurpose);
		
		JLabel lblScience = new JLabel("", SwingConstants.CENTER);
		lblScience.setIcon(Images.sciPicker);
		lblScience.setBounds(551, 386, 178, 88);
		add(lblScience);
		
		addMouseListener(buildMouseAdapter());
		addMouseMotionListener(buildMouseMotionAdapter());
		
		update();
	}
	
	public void update() {
		
	}

	private MouseAdapter buildMouseAdapter() {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getPoint().x > 556 && e.getPoint().y > 403
				&& e.getPoint().x < 606 && e.getPoint().y < 453) {
					matchPanel.scienceChosen(new ScientificSymbols(1, 0, 0));
					thisPanel.setVisible(false);
				} else if(e.getPoint().x > 619 && e.getPoint().y > 402
					   && e.getPoint().x < 663 && e.getPoint().y < 452) {
					matchPanel.scienceChosen(new ScientificSymbols(0, 1, 0));
					thisPanel.setVisible(false);
				} else if(e.getPoint().x > 664 && e.getPoint().y > 402
					   && e.getPoint().x < 714 && e.getPoint().y < 453) {
					matchPanel.scienceChosen(new ScientificSymbols(0, 0, 1));
					thisPanel.setVisible(false);
				} else {
					
				}
				matchPanel.repaint();
			}
		};
	}
	
	private MouseMotionAdapter buildMouseMotionAdapter() {
		return new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(e.getPoint().x > 556 && e.getPoint().y > 403
				&& e.getPoint().x < 606 && e.getPoint().y < 453) {
					lblOver1.setVisible(true);
					lblOver2.setVisible(false);
					lblOver3.setVisible(false);
				} else if(e.getPoint().x > 619 && e.getPoint().y > 402
					   && e.getPoint().x < 663 && e.getPoint().y < 452) {
					lblOver1.setVisible(false);
					lblOver2.setVisible(true);
					lblOver3.setVisible(false);
				} else if(e.getPoint().x > 664 && e.getPoint().y > 402
					   && e.getPoint().x < 714 && e.getPoint().y < 453) {
					lblOver1.setVisible(false);
					lblOver2.setVisible(false);
					lblOver3.setVisible(true);
				} else {
					lblOver1.setVisible(false);
					lblOver2.setVisible(false);
					lblOver3.setVisible(false);
				}
				matchPanel.repaint();
			}
		};
	}
	
	
//	public static void main(String args[]) {
//		javax.swing.JFrame frame = new javax.swing.JFrame("ScienceTest");
//		frame.setSize(1280, 860);
//		frame.add(new ScienceChoicePanel());
//		frame.setVisible(true);
//	}
}
