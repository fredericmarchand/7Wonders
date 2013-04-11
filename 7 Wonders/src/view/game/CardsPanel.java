package view.game;


import image.Images;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import view.resources.VerticalLabelUI;

import Controls.Controller;
import Structures.Structure;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

public class CardsPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Structure> cards;	
	private JLabel cardArr[], lblArr[], lblBgArr[], lblOptions[], lblArrow[][];
	private MouseListener ml[];
	private MouseMotionListener mml[];
	private boolean showOptions[], showArrow[][];
	private int canDoAction[][];
	
	private JPanel pausedPanel;
	
	private MatchPanel matchpanel;
	private Controller controller;
	
	private boolean ispaused;
	
	public CardsPanel(MatchPanel m, Controller c) {
		setLayout(null);
		setOpaque(false);
		setSize(1274, 280);
		
		cardArr = new JLabel[7];
		lblArr = new JLabel[7];
		lblBgArr = new JLabel[7];
		lblOptions = new JLabel[7];
		lblArrow = new JLabel[7][3];
		ml = new MouseListener[7];
		mml = new MouseMotionListener[7];
		showOptions = new boolean[7];
		showArrow = new boolean[7][3];
		canDoAction = new int[7][2];
		for (int[] b : canDoAction) Arrays.fill(b, -1);
		for (boolean[] b : showArrow) Arrays.fill(b, Boolean.FALSE);
		Arrays.fill(showOptions, Boolean.FALSE);
		
		matchpanel = m; 
		controller = c;
		
		pausedPanel = new JPanel();
		pausedPanel.setLayout(new BorderLayout());
		pausedPanel.setOpaque(true);
		pausedPanel.setSize(1274, 280);
		pausedPanel.setLocation(0, 0);
		pausedPanel.setBackground(new Color(50, 50, 50, 200));
		pausedPanel.setVisible(false);
		pausedPanel.addMouseListener(new MouseAdapter() { @Override public void mousePressed(MouseEvent e) {}});
		pausedPanel.addMouseMotionListener(new MouseMotionAdapter() { @Override public void mouseMoved(MouseEvent e) {}});
		JLabel panellabel = new JLabel("Waiting for other users to make their move");
		panellabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		panellabel.setForeground(Color.WHITE);
		panellabel.setHorizontalAlignment(SwingConstants.CENTER);
		pausedPanel.add(panellabel, BorderLayout.CENTER);
		add(pausedPanel);
		
		for (int i = 0; i < 7; i++) {
			cardArr[i] = new JLabel();
			cardArr[i].setSize(182, 280);
			
			lblArr[i] = new JLabel();
			lblArr[i].setForeground(Color.BLACK);
			lblArr[i].setFont(new Font("Arial", Font.PLAIN, 18));
			lblArr[i].setUI(new VerticalLabelUI(false));
			lblArr[i].setSize(25, 188);
			lblBgArr[i] = new JLabel();
			lblBgArr[i].setSize(30, 188);
			
			lblOptions[i] = new JLabel();
			lblOptions[i].setSize(150, 192);
			
			for(int j = 0; j < 3; j++) {
				lblArrow[i][j] = new JLabel();
				lblArrow[i][j].setSize(91, 57);
				lblArrow[i][j].setIcon(null);
				add(lblArrow[i][j]);
			}
			
			ml[i] = buildMouseAdapter();
			mml[i] = buildMouseMotionAdapter();
			
			add(lblOptions[i]);
			add(lblArr[i]);
			add(lblBgArr[i]);
			add(cardArr[i]);
		}
		
		addMouseListeners();
	}
	
	public void addMouseListeners() {
		for (int i = 0; i < 7; i++) {
			cardArr[i].addMouseListener(ml[i]);
			cardArr[i].addMouseMotionListener(mml[i]);
		}
	}
	
	public void removeMouseListeners() {
		for (int i = 0; i < 7; i++) {
			cardArr[i].removeMouseListener(ml[i]);
			cardArr[i].removeMouseMotionListener(mml[i]);
		}
	}

	public void update(ArrayList<Structure> c) {
		cards = c;
		for (int i = 0; i < 7; i++) {
			if(cards.size() > i) {
				
				// Card image and name
				cardArr[i].setIcon(Images.get("card"+cards.get(i).getID()));
				lblArr[i].setText(cards.get(i).getName());
				
				// Update card labels
				int w = lblArr[i].getFontMetrics(lblArr[i].getFont()).stringWidth(lblArr[i].getText()) + 23;
				lblBgArr[i].setIcon(new ImageIcon((Images.get("cardLabelbg").getImage()).getScaledInstance(30, w, java.awt.Image.SCALE_SMOOTH)));
				lblBgArr[i].setSize(30, w);
				
				// Update locations
				cardArr[i].setLocation(i*182 + ((7-cards.size()) * 91), 0);
				lblArr[i].setLocation(10 + i*182 + ((7-cards.size()) * 91), 74);
				lblBgArr[i].setLocation(8 + i*182 + ((7-cards.size()) * 91), 275 - lblBgArr[i].getHeight());
				lblOptions[i].setLocation(26 + i*182 + ((7-cards.size()) * 91), 75);
				for(int j = 0; j < 3; j++) lblArrow[i][j].setLocation(26 + i*182 + ((7-cards.size()) * 91), 80 + 63*j);
				
				// Update options
				if(showOptions[i]) {
					lblOptions[i].setIcon(Images.get("options"));
					for (int j = 0; j < 3; j++) {
						if(showArrow[i][j]){
							if(j == 2) lblArrow[i][j].setIcon(Images.get("arrow"+2));
							else {
								if(canDoAction[i][j] == 0) lblArrow[i][j].setIcon(Images.get("arrow"+0));
								else if(canDoAction[i][j] == 1) lblArrow[i][j].setIcon(Images.get("arrow"+1));
								else if(canDoAction[i][j] == 2) lblArrow[i][j].setIcon(Images.get("arrow"+2));
								else lblArrow[i][j].setIcon(null);
							}
						} else lblArrow[i][j].setIcon(null);
					}
				}
				else {
					lblOptions[i].setIcon(null);
					for (int j = 0; j < 3; j++) lblArrow[i][j].setIcon(null);
				}
			} else {
				cardArr[i].setIcon(null);
				lblArr[i].setText("");
				lblBgArr[i].setIcon(null);
				lblOptions[i].setIcon(null);
				for (int j = 0; j < 3; j++) lblArrow[i][j].setIcon(null);
			}
		}
		pausedPanel.setSize(1274 - (2*((7-cards.size()) * 91)), 280);
		pausedPanel.setLocation(((7-cards.size()) * 91), 0);
		this.repaint();
	}
	
	private MouseAdapter buildMouseAdapter() {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				for (int i = 0; i < cards.size(); i++) {
					if (((JLabel)e.getComponent()) == cardArr[i] && controller != null) {
						if(showOptions[i]) {
							for(int j = 0; j < 3; j++) {
								if(e.getPoint().x > (26) && e.getPoint().y > (80 + 63*j)
								&& e.getPoint().x < (26 + 150) && e.getPoint().y < (80 + 63*j + 57)){
									if(j == 0) matchpanel.buildStructure(canDoAction[i][0]);
									else if (j == 1) matchpanel.buildWonderStage(canDoAction[i][1]);
									else if (j == 2) controller.discardChosen();
									showOptions[i] = false;
									break;
								}
							}
						} else {
							Arrays.fill(showOptions, Boolean.FALSE);
							for (int[] b : canDoAction) Arrays.fill(b, -1);
							showOptions[i] = true;
							controller.chooseCard(cards.get(i));
							canDoAction[i][0] = controller.canBuildStructure(cards.get(i));
							canDoAction[i][1] = controller.canBuildWonderStage(cards.get(i));
						}
						break;
					}
				}
				update(cards);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				for (int i = 0; i < cards.size(); i++) {
					if (((JLabel)e.getComponent()) == cardArr[i]) {
						for (boolean[] b : showArrow) Arrays.fill(b, Boolean.FALSE);
						break;
					}
				}
				update(cards);
			}
		};
	}
	
	private MouseMotionAdapter buildMouseMotionAdapter() {
		return new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				for (int i = 0; i < cards.size(); i++) {
					if (((JLabel)e.getComponent()) == cardArr[i]) {
						for(int j = 0; j < 3; j++) {
							if(e.getPoint().x > (26) && e.getPoint().y > (80 + 63*j)
							   && e.getPoint().x < (26 + 150) && e.getPoint().y < (80 + 63*j + 57) && showOptions[i]){
								showArrow[i][j] = true;
							} else showArrow[i][j] = false;
						}
						break;
					}
				}
				update(cards);
			}
		};
	}
	
	public boolean isPaused() {
		return ispaused;
	}
	
	public void pause() {
		pausedPanel.setSize(1274 - (2*((7-cards.size()) * 91)), 280);
		pausedPanel.setLocation(((7-cards.size()) * 91), 0);
		pausedPanel.setVisible(true);
		ispaused = true;
	}
	
	public void unpause() {
		pausedPanel.setVisible(false);
		ispaused = false;
	}
	
	public void hideAllOptions() {
		Arrays.fill(showOptions, Boolean.FALSE);
		update(cards);
	}
}
