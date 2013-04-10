package view.game;


import image.Images;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JLabel;
import javax.swing.JPanel;

import Controls.Controller;

public class FreeBuildPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private JLabel lblFree, lblFreeOverlay;
	
	private Controller controller;
	
	private boolean can = false;
	private boolean toggle = false;
	
	public FreeBuildPanel (Controller c) {
		setLayout(null);
		setOpaque(false);
		setSize(80, 87);
		
		controller = c;
		
		// Free build structure
		lblFree = new JLabel(Images.get("freeBuild"));
		lblFree.setSize(80, 87);
		lblFree.setLocation(0, 0);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(can) {
					toggle = !toggle;
					controller.buildForFree(toggle);
					lblFreeOverlay.setVisible(toggle);
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				lblFreeOverlay.setVisible(toggle);
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				lblFreeOverlay.setVisible(true);
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
	
	public void update() {
		if(controller.canBuildForFree()) {
			lblFree.setVisible(true);
			can = true;
		} else {
			can = false;
			lblFree.setVisible(false);
		}
		toggle = false;
	}
}
