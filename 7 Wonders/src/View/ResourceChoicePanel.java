package View;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import Tokens.Resources;

public class ResourceChoicePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private Point overlayLocations[] = {
			// 2 overlay - width 50
			new Point(506, 403),
			new Point(581, 403),
			// 3 overlay - width 38
			new Point(493, 404),
			new Point(543, 404),
			new Point(594, 404),
			// 4 overlay - width 32
			new Point(490, 403),
			new Point(526, 403),
			new Point(561, 403),
			new Point(598, 403)
	};
	
	/*
		0000111
		0011000
		0101000
		0110000
		1001000
		1010000
		1100000
		1111000
	 */
	
	private ImageIcon overlayImage2, overlayImage3, overlayImage4;
	ImageIcon image0011000, image0101000, image0110000, image1001000, image1010000, image1100000, image0000111, image1111000;
	private Resources resource;
	int whichOverlay = 2;
	
	private JLabel lblResource, lblOverlay;
	
	private Controller controller;
	
	private ResourceChoicePanel thisPanel = this;
	private MatchPanel matchPanel;
	
	public ResourceChoicePanel(Controller c, MatchPanel m) {
		setSize(1280, 860);
		setLayout(null);
		setBackground(new Color(50, 50, 50, 200)); 
		
		resource = null;
		controller = c;
		matchPanel = m;
		
		overlayImage2 = new ImageIcon(ResourceChoicePanel.class.getResource("/Images/Icons/circleselect2.png"));
		overlayImage3 = new ImageIcon(ResourceChoicePanel.class.getResource("/Images/Icons/circleselect3.png"));
		overlayImage4 = new ImageIcon(ResourceChoicePanel.class.getResource("/Images/Icons/circleselect4.png"));
		
		image0011000 = new ImageIcon(ResourceChoicePanel.class.getResource("/Images/Icons/resource-0011000.png"));
		image0101000 = new ImageIcon(ResourceChoicePanel.class.getResource("/Images/Icons/resource-0101000.png"));
		image0110000 = new ImageIcon(ResourceChoicePanel.class.getResource("/Images/Icons/resource-0110000.png"));
		image1001000 = new ImageIcon(ResourceChoicePanel.class.getResource("/Images/Icons/resource-1001000.png"));
		image1010000 = new ImageIcon(ResourceChoicePanel.class.getResource("/Images/Icons/resource-1010000.png"));
		image1100000 = new ImageIcon(ResourceChoicePanel.class.getResource("/Images/Icons/resource-1100000.png"));
		image0000111 = new ImageIcon(ResourceChoicePanel.class.getResource("/Images/Icons/resource-0000111.png"));
		image1111000 = new ImageIcon(ResourceChoicePanel.class.getResource("/Images/Icons/resource-1111000.png"));
		
		lblOverlay = new JLabel("", SwingConstants.CENTER);
		lblOverlay.setIcon(overlayImage4);
		lblOverlay.setBounds(598, 403, 50, 50);
		add(lblOverlay);
		
		lblResource = new JLabel("", SwingConstants.CENTER);
		lblResource.setBounds(497, 400, 143, 60);
		lblResource.setIcon(new ImageIcon(ResourceChoicePanel.class.getResource("/Images/Icons/resource-1111000.png")));
		add(lblResource);
		
		addMouseListener(buildMouseAdapter());
		addMouseMotionListener(buildMouseMotionAdapter());
	}
	
	private MouseAdapter buildMouseAdapter() {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(whichOverlay == 2) {
					if(e.getPoint().x > overlayLocations[0].x      && e.getPoint().y > overlayLocations[0].y
					&& e.getPoint().x < overlayLocations[0].x + 50 && e.getPoint().y < overlayLocations[0].y + 50) {
						controller.resourceChosen(convertResource(1));
						Resources next = matchPanel.nextResource();
						if(next == null) thisPanel.setVisible(false);
						else setResource(next);
					} else if(e.getPoint().x > overlayLocations[1].x      && e.getPoint().y > overlayLocations[1].y
						   && e.getPoint().x < overlayLocations[1].x + 50 && e.getPoint().y < overlayLocations[1].y + 50) {
						controller.resourceChosen(convertResource(2));
						Resources next = matchPanel.nextResource();
						if(next == null) thisPanel.setVisible(false);
						else setResource(next);
					} else {
						
					}
				} else if(whichOverlay == 3) {
					if(e.getPoint().x > overlayLocations[2].x      && e.getPoint().y > overlayLocations[2].y
					&& e.getPoint().x < overlayLocations[2].x + 38 && e.getPoint().y < overlayLocations[2].y + 38) {
						controller.resourceChosen(convertResource(1));
						Resources next = matchPanel.nextResource();
						if(next == null) thisPanel.setVisible(false);
						else setResource(next);
					} else if(e.getPoint().x > overlayLocations[3].x      && e.getPoint().y > overlayLocations[3].y
						   && e.getPoint().x < overlayLocations[3].x + 38 && e.getPoint().y < overlayLocations[3].y + 38) {
						controller.resourceChosen(convertResource(2));
						Resources next = matchPanel.nextResource();
						if(next == null) thisPanel.setVisible(false);
						else setResource(next);
					} else if(e.getPoint().x > overlayLocations[4].x      && e.getPoint().y > overlayLocations[4].y
						   && e.getPoint().x < overlayLocations[4].x + 38 && e.getPoint().y < overlayLocations[4].y + 38) {
						controller.resourceChosen(convertResource(3));
						Resources next = matchPanel.nextResource();
						if(next == null) thisPanel.setVisible(false);
						else setResource(next);
					} else {
						
					}
				} else if(whichOverlay == 4) {
					if(e.getPoint().x > overlayLocations[5].x 	   && e.getPoint().y > overlayLocations[5].y
					&& e.getPoint().x < overlayLocations[5].x + 32 && e.getPoint().y < overlayLocations[5].y + 32) {
						controller.resourceChosen(convertResource(1));
						Resources next = matchPanel.nextResource();
						if(next == null) thisPanel.setVisible(false);
						else setResource(next);
					} else if(e.getPoint().x > overlayLocations[6].x 	  && e.getPoint().y > overlayLocations[6].y
						   && e.getPoint().x < overlayLocations[6].x + 32 && e.getPoint().y < overlayLocations[6].y + 32) {
						controller.resourceChosen(convertResource(2));
						Resources next = matchPanel.nextResource();
						if(next == null) thisPanel.setVisible(false);
						else setResource(next);
					} else if(e.getPoint().x > overlayLocations[7].x 	  && e.getPoint().y > overlayLocations[7].y
						   && e.getPoint().x < overlayLocations[7].x + 32 && e.getPoint().y < overlayLocations[7].y + 32) {
						controller.resourceChosen(convertResource(3));
						Resources next = matchPanel.nextResource();
						if(next == null) thisPanel.setVisible(false);
						else setResource(next);
					} else if(e.getPoint().x > overlayLocations[8].x 	  && e.getPoint().y > overlayLocations[8].y
						   && e.getPoint().x < overlayLocations[8].x + 32 && e.getPoint().y < overlayLocations[8].y + 32) {
						controller.resourceChosen(convertResource(4));
						Resources next = matchPanel.nextResource();
						if(next == null) thisPanel.setVisible(false);
						else setResource(next);
					} else {
						
					}
				}
				matchPanel.repaint();
			}
		};
	}
	
	private MouseMotionAdapter buildMouseMotionAdapter() {
		return new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if(whichOverlay == 2) {
					if(e.getPoint().x > overlayLocations[0].x      && e.getPoint().y > overlayLocations[0].y
					&& e.getPoint().x < overlayLocations[0].x + 50 && e.getPoint().y < overlayLocations[0].y + 50) {
						lblOverlay.setLocation(overlayLocations[0]);
						lblOverlay.setVisible(true);
					} else if(e.getPoint().x > overlayLocations[1].x      && e.getPoint().y > overlayLocations[1].y
						   && e.getPoint().x < overlayLocations[1].x + 50 && e.getPoint().y < overlayLocations[1].y + 50) {
						lblOverlay.setLocation(overlayLocations[1]);
						lblOverlay.setVisible(true);
					} else {
						lblOverlay.setVisible(false);
					}
				} else if(whichOverlay == 3) {
					if(e.getPoint().x > overlayLocations[2].x      && e.getPoint().y > overlayLocations[2].y
					&& e.getPoint().x < overlayLocations[2].x + 38 && e.getPoint().y < overlayLocations[2].y + 38) {
						lblOverlay.setLocation(overlayLocations[2]);
						lblOverlay.setVisible(true);
					} else if(e.getPoint().x > overlayLocations[3].x      && e.getPoint().y > overlayLocations[3].y
						   && e.getPoint().x < overlayLocations[3].x + 38 && e.getPoint().y < overlayLocations[3].y + 38) {
						lblOverlay.setLocation(overlayLocations[3]);
						lblOverlay.setVisible(true);
					} else if(e.getPoint().x > overlayLocations[4].x      && e.getPoint().y > overlayLocations[4].y
						   && e.getPoint().x < overlayLocations[4].x + 38 && e.getPoint().y < overlayLocations[4].y + 38) {
						lblOverlay.setLocation(overlayLocations[4]);
						lblOverlay.setVisible(true);
					} else {
						lblOverlay.setVisible(false);
					}
				} else if(whichOverlay == 4) {
					if(e.getPoint().x > overlayLocations[5].x 	   && e.getPoint().y > overlayLocations[5].y
					&& e.getPoint().x < overlayLocations[5].x + 32 && e.getPoint().y < overlayLocations[5].y + 32) {
						lblOverlay.setLocation(overlayLocations[5]);
						lblOverlay.setVisible(true);
					} else if(e.getPoint().x > overlayLocations[6].x 	  && e.getPoint().y > overlayLocations[6].y
						   && e.getPoint().x < overlayLocations[6].x + 32 && e.getPoint().y < overlayLocations[6].y + 32) {
						lblOverlay.setLocation(overlayLocations[6]);
						lblOverlay.setVisible(true);
					} else if(e.getPoint().x > overlayLocations[7].x 	  && e.getPoint().y > overlayLocations[7].y
						   && e.getPoint().x < overlayLocations[7].x + 32 && e.getPoint().y < overlayLocations[7].y + 32) {
						lblOverlay.setLocation(overlayLocations[7]);
						lblOverlay.setVisible(true);
					} else if(e.getPoint().x > overlayLocations[8].x 	  && e.getPoint().y > overlayLocations[8].y
						   && e.getPoint().x < overlayLocations[8].x + 32 && e.getPoint().y < overlayLocations[8].y + 32) {
						lblOverlay.setLocation(overlayLocations[8]);
						lblOverlay.setVisible(true);
					} else {
						lblOverlay.setVisible(false);
					}
				}
				matchPanel.repaint();
			}
		};
	}
	
	public void setResource(Resources r) {
		resource = r;
		lblOverlay.setVisible(false);
		if(r != null) {
			if(new Resources(0, 0, 1, 1, 0, 0, 0, 0).compare(r)) {
				lblResource.setIcon(image0011000);
				lblOverlay.setIcon(overlayImage2);
				whichOverlay = 2;
			}
			else if(new Resources(0, 1, 0, 1, 0, 0, 0, 0).compare(r)) {
				lblResource.setIcon(image0101000);
				lblOverlay.setIcon(overlayImage2);
				whichOverlay = 2;
			}
			else if(new Resources(0, 1, 1, 0, 0, 0, 0, 0).compare(r)) {
				lblResource.setIcon(image0110000);
				lblOverlay.setIcon(overlayImage2);
				whichOverlay = 2;
			}
			else if(new Resources(1, 0, 0, 1, 0, 0, 0, 0).compare(r)) {
				lblResource.setIcon(image1001000);
				lblOverlay.setIcon(overlayImage2);
				whichOverlay = 2;
			}
			else if(new Resources(1, 0, 1, 0, 0, 0, 0, 0).compare(r)) {
				lblResource.setIcon(image1010000);
				lblOverlay.setIcon(overlayImage2);
				whichOverlay = 2;
			}
			else if(new Resources(1, 1, 0, 0, 0, 0, 0, 0).compare(r)) {
				lblResource.setIcon(image1100000);
				lblOverlay.setIcon(overlayImage2);
				whichOverlay = 2;
			}
			else if(new Resources(0, 0, 0, 0, 1, 1, 1, 0).compare(r)) {
				lblResource.setIcon(image0000111);
				lblOverlay.setIcon(overlayImage3);
				whichOverlay = 3;
			}
			else if(new Resources(1, 1, 1, 1, 0, 0, 0, 0).compare(r)) {
				lblResource.setIcon(image1111000);
				lblOverlay.setIcon(overlayImage4);
				whichOverlay = 4;
			}
		}
	}
	
	private Resources convertResource(int which) {
		if(new Resources(0, 0, 1, 1, 0, 0, 0, 0).compare(resource)) {
			if(which == 1) return new Resources(0, 0, 1, 0, 0, 0, 0, 0);
			if(which == 2) return new Resources(0, 0, 0, 1, 0, 0, 0, 0);
		}
		else if(new Resources(0, 1, 0, 1, 0, 0, 0, 0).compare(resource)) {
			if(which == 1) return new Resources(0, 1, 0, 0, 0, 0, 0, 0);
			if(which == 2) return new Resources(0, 0, 0, 1, 0, 0, 0, 0);
		}
		else if(new Resources(0, 1, 1, 0, 0, 0, 0, 0).compare(resource)) {
			if(which == 1) return new Resources(0, 1, 0, 0, 0, 0, 0, 0);
			if(which == 2) return new Resources(0, 0, 1, 0, 0, 0, 0, 0);
		}
		else if(new Resources(1, 0, 0, 1, 0, 0, 0, 0).compare(resource)) {
			if(which == 1) return new Resources(0, 0, 0, 1, 0, 0, 0, 0);
			if(which == 2) return new Resources(1, 0, 0, 0, 0, 0, 0, 0);
		}
		else if(new Resources(1, 0, 1, 0, 0, 0, 0, 0).compare(resource)) {
			if(which == 1) return new Resources(0, 0, 1, 0, 0, 0, 0, 0);
			if(which == 2) return new Resources(1, 0, 0, 0, 0, 0, 0, 0);
		}
		else if(new Resources(1, 1, 0, 0, 0, 0, 0, 0).compare(resource)) {
			if(which == 1) return new Resources(0, 1, 0, 0, 0, 0, 0, 0);
			if(which == 2) return new Resources(1, 0, 0, 0, 0, 0, 0, 0);
		}
		else if(new Resources(0, 0, 0, 0, 1, 1, 1, 0).compare(resource)) {
			if(which == 1) return new Resources(0, 0, 0, 0, 1, 0, 0, 0);
			if(which == 2) return new Resources(0, 0, 0, 0, 0, 1, 0, 0);
			if(which == 3) return new Resources(0, 0, 0, 0, 0, 0, 1, 0);
		}
		else if(new Resources(1, 1, 1, 1, 0, 0, 0, 0).compare(resource)) {
			if(which == 1) return new Resources(0, 0, 1, 0, 0, 0, 0, 0);
			if(which == 2) return new Resources(0, 1, 0, 0, 0, 0, 0, 0);
			if(which == 3) return new Resources(1, 0, 0, 0, 0, 0, 0, 0);
			if(which == 4) return new Resources(0, 0, 0, 1, 0, 0, 0, 0);
		}
		return null;
	}
	
}
