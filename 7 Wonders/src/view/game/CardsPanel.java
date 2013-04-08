package view.game;


import image.Images;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;

import view.resources.VerticalLabelUI;

import Controls.Controller;
import Structures.Structure;

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
	
	private Controller controller;
	private FreeBuildPanel fbp;
	
	public CardsPanel(ArrayList<Structure> ca, Controller c, FreeBuildPanel f) {
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
		
		controller = c;
		cards = ca;
		fbp = f;
		fbp.setCardsPanel(this);
		
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
		update();
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
	
	public void setCards(ArrayList<Structure> c) {
		cards = c;
	}

	public void update() {
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
									if(j == 0 && canDoAction[i][0] > 0) controller.buildStructure();
									else if (j == 1 && canDoAction[i][1] > 0) controller.buildWonderStage();
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
							fbp.chosenCard(cards.get(i));
							canDoAction[i][0] = controller.canBuildStructure(cards.get(i));
							canDoAction[i][1] = controller.canBuildWonderStage(cards.get(i));
						}
						break;
					}
				}
				update();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				for (int i = 0; i < cards.size(); i++) {
					if (((JLabel)e.getComponent()) == cardArr[i]) {
						for (boolean[] b : showArrow) Arrays.fill(b, Boolean.FALSE);
						break;
					}
				}
				update();
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
				update();
			}
		};
	}
	
	public void hideAllOptions() {
		Arrays.fill(showOptions, Boolean.FALSE);
		update();
	}
}
