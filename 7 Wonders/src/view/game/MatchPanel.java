package view.game;


import image.Images;

import java.util.ArrayList;

import javax.swing.*;

import view.resources.JLabel2D;

import Structures.Structure;
import Tokens.Resources;
import Tokens.ScientificSymbols;

import Controls.Controller;
import Controls.Match2;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MatchPanel extends JPanel implements Runnable {
	private static final long serialVersionUID = 1L;
	
	private Match2 match;
	
	private int numplayers;
	
	private FarPanel f1, f2, f3, f4;
	private NearPanel n1, n2;
	private LocalPanel playerPanel;
	private CardsPanel cardsPanel;
	
	private JLabel lblAge, closeButton;
	private JLabel2D lblTurn;
	
	private JScrollPane scrollpane;
	private FullscreenCardsPanel fcp;
	
	private ResourceChoicePanel rcp;
	private ScienceChoicePanel scp;
	private FreeBuildPanel fbp;
	private TradingChoicePanel tcp;
	
	private Controller controller;
	
	private ArrayList<Resources> needResources;
	private ArrayList<Resources> pickedResources;
	private ArrayList<ScientificSymbols> needScience;
	private ArrayList<ScientificSymbols> pickedSciences;
	private ArrayList<Structure> needDiscarded;
	private ArrayList<Structure> needGuild;
	
	public MatchPanel(Match2 m, Controller c) {
		setLayout(null);
		setSize(1280, 860);
		
		match = m;
		controller = c;
		pickedResources = new ArrayList<Resources>();
		needDiscarded = new ArrayList<Structure>();
		needGuild = new ArrayList<Structure>();
		needScience = new ArrayList<ScientificSymbols>();
		pickedSciences = new ArrayList<ScientificSymbols>();
		
		// Start asynchronous part
		run();
		
		// Add solid white BG to fix background repaint issue with scrollpanes
		BufferedImage bi = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, bi.getWidth(), bi.getHeight());
        g.dispose();
		JLabel bg = new JLabel(new ImageIcon(bi));
		bg.setSize(this.getSize());
		bg.setLocation(0, 0);
		add(bg);
		
		update();
	}
	
	public void run(){
		
		numplayers = match.getPlayers().size();
		System.out.println("MatchPanel numplayers: " + numplayers);
		// Local
		Player.Player p1 = match.getLocalPlayer();
		playerPanel = new LocalPanel(p1);
		playerPanel.addMouseListener(buildMouseAdapterLocal());
		playerPanel.setLocation(401, (numplayers > 3) ? 450 : 300);
		
		// Right Neighbor
		Player.Player p2 = match.getRightNeighbor(p1);
		n2 = new NearPanel(p2);
		n2.setLocation(802, (numplayers > 3) ? 150 : 0);
		n2.addMouseListener(buildMouseAdapterNear());
		
		// Left Neighbor
		Player.Player p3 = match.getLeftNeighbor(p1);
		n1 = new NearPanel(p3);
		n1.setLocation(0, (numplayers > 3) ? 150 : 0);
		n1.addMouseListener(buildMouseAdapterNear());
		
		Player.Player p4 = null;
		Player.Player p5 = null;
		Player.Player p6 = null;
		Player.Player p7 = null;
		f1 = null;
		f2 = null;
		f3 = null;
		f4 = null;

		if(numplayers > 3) {
			p4 = match.getRightNeighbor(p2);
			f1 = new FarPanel(p4);
			f1.setLocation(getForeignPos(numplayers, 0), 0);
			f1.addMouseListener(buildMouseAdapterFar());
		}
		if(numplayers > 4) {
			p5 = match.getRightNeighbor(p4);
			f2 = new FarPanel(p5);
			f2.setLocation(getForeignPos(numplayers, 1), 0);
			f2.addMouseListener(buildMouseAdapterFar());
		}
		if(numplayers > 5) {
			p6 = match.getRightNeighbor(p5);
			f3 = new FarPanel(p6);
			f3.setLocation(getForeignPos(numplayers, 2), 0);
			f3.addMouseListener(buildMouseAdapterFar());
		}
		if(numplayers > 6) {
			p7 = match.getRightNeighbor(p6);
			f4 = new FarPanel(p7);
			f4.setLocation(getForeignPos(numplayers, 3), 0);
			f4.addMouseListener(buildMouseAdapterFar());
		}
		
		fbp = new FreeBuildPanel(controller);
		fbp.setLocation(296, 458);
		
		// Cards
		cardsPanel = new CardsPanel(this, controller);
		cardsPanel.setLocation(3, 558);
		cardsPanel.addMouseListener(buildMouseAdapterCards());
		cardsPanel.update(match.getLocalPlayer().getCards());
		
		// Other
		lblAge = new JLabel(Images.get("age0"), SwingConstants.CENTER);
		lblAge.setBounds(512, (numplayers > 3) ? 161 : 141, 256, 128);
		lblAge.setForeground(Color.BLACK);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		lblTurn = new JLabel2D("Round 1 of 6", SwingConstants.CENTER);
		lblTurn.setBounds(576, (numplayers > 3) ? 295 : 275, 128, 25);
		lblTurn.setForeground(Color.WHITE);
		lblTurn.setOutlineColor(Color.BLACK);
		lblTurn.setStroke(new BasicStroke(2f));
		lblTurn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		fcp = new FullscreenCardsPanel(this);
		scrollpane = new JScrollPane(fcp, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane.setSize(1280, 838);
		scrollpane.getViewport().setOpaque(false);
		scrollpane.setBackground(new Color(50, 50, 50, 200));
		closeButton = new JLabel(Images.get("X"));
		closeButton.setSize(56, 56);
		closeButton.setLocation(1224, 0);
		closeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				scrollpane.setVisible(false);
			}
		});
		scrollpane.add(closeButton);
		final MatchPanel matchpanel = this;
		scrollpane.getHorizontalScrollBar().addAdjustmentListener(new AdjustmentListener() {
	        @Override
	        public void adjustmentValueChanged(final AdjustmentEvent e) {
	            matchpanel.repaint();
	        }
	    });
		scrollpane.setVisible(false);
		
		rcp = new ResourceChoicePanel(this);
		rcp.setVisible(false);
		
		scp = new ScienceChoicePanel(this);
		scp.setVisible(false);
		
		tcp = new TradingChoicePanel(this);
		tcp.setVisible(false);
		
		add(tcp);
		add(scp);
		add(rcp);
		add(scrollpane);
		add(cardsPanel);
		add(fbp);
		add(playerPanel);
		add(n1);
		add(n2);
		if(numplayers > 3) add(f1);
		if(numplayers > 4) add(f2);
		if(numplayers > 5) add(f3);
		if(numplayers > 6) add(f4);
		add(lblAge);
		add(lblTurn);
		JLabel bgimg = new JLabel(Images.get("bg"));
		bgimg.setSize(1280, 860);
		add(bgimg);
	}
	
	public void update() {
		updateValues();
		
		// Choose resources
		needResources = controller.needToChooseResources();
		Resources next = nextResource();
		rcp.setResource(next);
		if(next != null) rcp.setVisible(true);
		
		// Choose discarded
		needDiscarded = controller.needToChooseDiscarded();
		if(!needDiscarded.isEmpty()) {
			fcp.setCards(needDiscarded);
			fcp.setMode(FullscreenCardsPanel.DISCARDED);
			closeButton.setVisible(false);
			scrollpane.revalidate();
			scrollpane.setVisible(true);
		} else {
			controller.chosenDiscarded(new Structure());
		}
		
		// Choose guild to copy
		needGuild = controller.needToChooseCopyGuild();
		if(!needGuild.isEmpty()) {
			fcp.setCards(needGuild);
			fcp.setMode(FullscreenCardsPanel.GUILD);
			closeButton.setVisible(false);
			scrollpane.revalidate();
			scrollpane.setVisible(true);
		} else {
			controller.chosenGuild(new Structure());
		}
		
		// Choose Science
		needScience = controller.needToChooseScienceSymbol();
		if(nextScience() != null) scp.setVisible(true);
	}
	
	//update function that wont cause an infinite loop on receive
	public void updateValues() {
		if(match.getAge() > 3) {
			lblAge.setIcon(null);
			lblAge.setText("GAME OVER!");
			lblTurn.setText(" ");
		} else {
			lblAge.setIcon(Images.get("age"+match.getAge()));
			lblAge.setText(" ");
			int t = match.getTurn();
			if ( t > 6 ) t = 6;
			lblTurn.setText("Round "+ t +" of 6");
		}
		playerPanel.update();
		n1.update();
		n2.update();
		if(f1 != null) f1.update();
		if(f2 != null) f2.update();
		if(f3 != null) f3.update();
		if(f4 != null) f4.update();
		fbp.update();
		cardsPanel.update(match.getLocalPlayer().getCards());
	}
	
	public void buildStructure(int type) {
		if(type == 0) return;
		if(type == 2) controller.buildStructure(-1);
		if(type == 1) tcp.showGUI(1);
		if(type >= 10) controller.buildStructure(type - 10);
	}
	
	public void buildWonderStage(int type) {
		if(type == 0) return;
		if(type == 2) controller.buildWonderStage(-1);
		if(type == 1) tcp.showGUI(2);
		if(type >= 10) controller.buildWonderStage(type - 10);
	}
	
	 public Resources nextResource() {
		 if(needResources.size() > 0)
		 return needResources.remove(0);
		 else {
			 if(!pickedResources.isEmpty()) {
				 controller.resourceChosen(pickedResources);
				 pickedResources.clear();
			 }
			 return null;
		 }
	}
	
	public void resourceChosen(Resources r) {
		pickedResources.add(r);
	}
	
	public ScientificSymbols nextScience() {
		if(needScience.size() > 0)
			return needScience.remove(0);
		else {
			controller.scienceChosen(pickedSciences);
			pickedSciences.clear();
			return null;
		}
	}
	
	public void scienceChosen(ScientificSymbols s) {
		pickedSciences.add(s);
	}
	
	public void discardChosen(Structure s) {
		controller.chosenDiscarded(s);
		scrollpane.setVisible(false);
		closeButton.setVisible(true);
	}
	
	public void guildChosen(Structure s) {
		controller.chosenGuild(s);
		scrollpane.setVisible(false);
		closeButton.setVisible(true);
	}
	
	public void pause() {
		cardsPanel.pause();
	}
	
	public void unpause() {
		cardsPanel.unpause();
	}
	
	public MouseAdapter buildMouseAdapterNear() {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				NearPanel panel = null;
				if(e.getComponent() == ((NearPanel) n1)) panel = n1;
				else if (e.getComponent() == ((NearPanel) n2)) panel = n2;
				if(panel != null) {
					fcp.setCards(panel.player.getWonderBoard().getAllCards());
					fcp.setMode(FullscreenCardsPanel.DISPLAY);
					scrollpane.revalidate();
					scrollpane.setVisible(true);
				}
			}
		};
	}
	
	public MouseAdapter buildMouseAdapterFar() {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				FarPanel panel = null;
				if(e.getComponent() == ((FarPanel) f1)) panel = f1;
				else if (e.getComponent() == ((FarPanel) f2)) panel = f2;
				else if (e.getComponent() == ((FarPanel) f3)) panel = f3;
				else if (e.getComponent() == ((FarPanel) f4)) panel = f4;
				if(panel != null) {
					fcp.setCards(panel.player.getWonderBoard().getAllCards());
					fcp.setMode(FullscreenCardsPanel.DISPLAY);
					scrollpane.revalidate();
					scrollpane.setVisible(true);
				}
			}
		};
	}
	
	public MouseAdapter buildMouseAdapterLocal() {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(cardsPanel.getY() == 558) {
					cardsPanel.setLocation(cardsPanel.getX(), (numplayers > 3) ? 750 : 600);
					cardsPanel.removeMouseListeners();
				} else {
					fcp.setCards(playerPanel.player.getWonderBoard().getAllCards());
					fcp.setMode(FullscreenCardsPanel.DISPLAY);
					scrollpane.revalidate();
					scrollpane.setVisible(true);
				}
			}
		};
	}
	
	public MouseAdapter buildMouseAdapterCards() {
		return new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(cardsPanel.getY() == ((numplayers > 3) ? 750 : 600)) {
					cardsPanel.setLocation(cardsPanel.getX(), 558);
					cardsPanel.addMouseListeners();
				}
			}
		};
	}
	
	private int getForeignPos(int numplayers, int index){
		if(numplayers == 4) return 480;
		if(numplayers == 5) return 320 + 320*index;
		if(numplayers == 6) return 160 + 320*index;
		if(numplayers == 7) return 320*index;
		return 0;
	}
	
	public void hideMatchPanel(){
		setVisible(false);
	}
}
