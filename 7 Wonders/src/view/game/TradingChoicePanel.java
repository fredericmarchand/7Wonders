package view.game;

import image.Images;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import view.resources.JLabel2D;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class TradingChoicePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	JLabel lblLeft, lblRight, lblRandom;
	
	MatchPanel matchPanel;
	
	public TradingChoicePanel(MatchPanel m) {
		setLayout(null);
		setSize(1280, 860);
		setBackground(new Color(50, 50, 50, 200));
		
		matchPanel = m;
		
		JLabel2D lblDesc = new JLabel2D("Choose who you would prefer to trade with: ", SwingConstants.CENTER);
		lblDesc.setForeground(Color.WHITE);
		lblDesc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblDesc.setBounds(390, 413, 500, 33);
		add(lblDesc);
		
		lblLeft = new JLabel(Images.get("tradingpref0"));
		lblLeft.setBounds(516, 456, 48, 48);
		add(lblLeft);
		
		lblRight = new JLabel(Images.get("tradingpref1"));
		lblRight.setBounds(716, 456, 48, 48);
		add(lblRight);
		
		lblRandom = new JLabel(Images.get("tradingpref2"));
		lblRandom.setBounds(616, 506, 48, 48);
		add(lblRandom);
		
		addMouseListener(buildMouseAdapter());
		addMouseMotionListener(buildMouseMotionAdapter());
		
	}
	
	private MouseAdapter buildMouseAdapter() {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getPoint().x > 556 && e.getPoint().y > 403
				&& e.getPoint().x < 606 && e.getPoint().y < 453) {

				} else if(e.getPoint().x > 619 && e.getPoint().y > 402
					   && e.getPoint().x < 663 && e.getPoint().y < 452) {

				} else if(e.getPoint().x > 664 && e.getPoint().y > 402
					   && e.getPoint().x < 714 && e.getPoint().y < 453) {

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

				} else if(e.getPoint().x > 619 && e.getPoint().y > 402
					   && e.getPoint().x < 663 && e.getPoint().y < 452) {

				} else if(e.getPoint().x > 664 && e.getPoint().y > 402
					   && e.getPoint().x < 714 && e.getPoint().y < 453) {

				} else {

				}
				matchPanel.repaint();
			}
		};
	}
}
