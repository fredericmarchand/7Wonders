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

	private JLabel lblLeft, lblRight, lblRandom, lblLeftOver, lblRightOver, lblRandomOver;
	
	private MatchPanel matchPanel;
	
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
		
		lblLeftOver = new JLabel(Images.get("tradingpref0Over"));
		lblLeftOver.setBounds(516, 456, 48, 48);
		add(lblLeftOver);
		
		lblLeft = new JLabel(Images.get("tradingpref0"));
		lblLeft.setBounds(516, 456, 48, 48);
		add(lblLeft);
		
		lblRightOver = new JLabel(Images.get("tradingpref1Over"));
		lblRightOver.setBounds(716, 456, 48, 48);
		add(lblRightOver);
		
		lblRight = new JLabel(Images.get("tradingpref1"));
		lblRight.setBounds(716, 456, 48, 48);
		add(lblRight);
		
		lblRandomOver = new JLabel(Images.get("tradingpref2Over"));
		lblRandomOver.setBounds(616, 506, 48, 48);
		add(lblRandomOver);
		
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
				if(e.getPoint().x > 516 && e.getPoint().y > 456
				&& e.getPoint().x < 564 && e.getPoint().y < 504) {
					
				} else if(e.getPoint().x > 716 && e.getPoint().y > 456
					   && e.getPoint().x < 764 && e.getPoint().y < 504) {
					
				} else if(e.getPoint().x > 616 && e.getPoint().y > 506
					   && e.getPoint().x < 664 && e.getPoint().y < 554) {
					
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
				if(e.getPoint().x > 516 && e.getPoint().y > 456
				&& e.getPoint().x < 564 && e.getPoint().y < 504) {
					lblLeftOver.setVisible(true);
					lblRightOver.setVisible(false);
					lblRandomOver.setVisible(false);
				} else if(e.getPoint().x > 716 && e.getPoint().y > 456
					   && e.getPoint().x < 764 && e.getPoint().y < 504) {
					lblLeftOver.setVisible(false);
					lblRightOver.setVisible(true);
					lblRandomOver.setVisible(false);
				} else if(e.getPoint().x > 616 && e.getPoint().y > 506
					   && e.getPoint().x < 664 && e.getPoint().y < 554) {
					lblLeftOver.setVisible(false);
					lblRightOver.setVisible(false);
					lblRandomOver.setVisible(true);
				} else {
					lblLeftOver.setVisible(false);
					lblRightOver.setVisible(false);
					lblRandomOver.setVisible(false);
				}
				matchPanel.repaint();
			}
		};
	}
}
