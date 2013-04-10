package view.game;


import image.Images;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Controls.Controller;
import Structures.Structure;

public class FreeBuildPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblFree, lblFreeOverlay;
	
	private Controller controller;
	private CardsPanel cp;
	
	private boolean enabled = false;
	private Structure chosen;
	
	public FreeBuildPanel (Controller c) {
		setLayout(null);
		setOpaque(false);
		setSize(80, 87);
		
		controller = c;
		chosen = null;
		
		// Free build structure
		lblFree = new JLabel(Images.get("freeBuild"));
		lblFree.setSize(80, 87);
		lblFree.setLocation(0, 0);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(enabled) {
					controller.buildForFree(chosen);
					cp.hideAllOptions();
					lblFreeOverlay.setVisible(false);
					update();
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblFreeOverlay.setVisible(false);
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(enabled) lblFreeOverlay.setVisible(true);
			}
		});

		lblFreeOverlay = new JLabel(Images.get("freeBuildOverlay"));
		lblFreeOverlay.setSize(80, 87);
		lblFreeOverlay.setLocation(0, 0);
		lblFreeOverlay.setVisible(false);
		
		add(lblFreeOverlay);
		add(lblFree);
		
		update();
	}
	
	public void setCardsPanel(CardsPanel c) {
		cp = c;
	}
	
	public void chosenCard(Structure s) {
		chosen = s;
	}
	
	public void update() {
		if(controller.canBuildForFree()) {
			lblFree.setVisible(true);
			enabled = true;
		} else {
			enabled = false;
			lblFree.setVisible(false);
		}
	}
}
