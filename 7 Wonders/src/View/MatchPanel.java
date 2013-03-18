package View;

import java.io.File;
import java.util.ArrayList;

import javax.swing.*;

import Tokens.Resources;

import Controls.Match1;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class MatchPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Match1 match;
	private ImageIcon cards[];
	
	private FarPanel f1, f2, f3, f4;
	private NearPanel n1, n2;
	private LocalPanel playerPanel;
	private CardsPanel cardsPanel;
	
	private JLabel lblAge, lblTurn;
	
	private JScrollPane scrollpane;
	private FullscreenCardsPanel fcp;
	
	private ResourceChoicePanel rcp;
	
	private Controller controller;
	
	private ArrayList<Resources> need;
	private ArrayList<Resources> picked;
	
	public MatchPanel(Match1 m, Controller c) {
		setLayout(null);
		setSize(1280, 860);
		setBackground(Color.WHITE);
		
		picked = new ArrayList<Resources>();
		
		match = m;
		controller = c;
		picked = new ArrayList<Resources>();
		
		// Load all the Card images
		int numCards = 75;
		cards = new ImageIcon[numCards+1];
		for(int i = 1; i < numCards+1; i++) {
			cards[i] = new ImageIcon(new ImageIcon(MatchPanel.class.getResource("/Images/Cards/"+i+".jpg")).getImage().getScaledInstance(182, 280, java.awt.Image.SCALE_SMOOTH));
		}
		
		// Make the overview panels
		
		// Players
		playerPanel = new LocalPanel(match.getPlayers().get(0), controller);
		playerPanel.addMouseListener(buildMouseAdapterLocal());
		playerPanel.setLocation(401, 450);
		
		// Neighbours
		n1 = new NearPanel(match.getPlayers().get(6), controller);
		n1.setLocation(0, 150);
		n1.addMouseListener(buildMouseAdapterNear());
		n2 = new NearPanel(match.getPlayers().get(1), controller);
		n2.setLocation(802, 150);
		n2.addMouseListener(buildMouseAdapterNear());
		
		// Foreigners
		f1 = new FarPanel(match.getPlayers().get(5), controller);
		f1.setLocation(0, 0);
		f1.addMouseListener(buildMouseAdapterFar());
		f2 = new FarPanel(match.getPlayers().get(4), controller);
		f2.setLocation(320, 0);
		f2.addMouseListener(buildMouseAdapterFar());
		f3 = new FarPanel(match.getPlayers().get(3), controller);
		f3.setLocation(640, 0);
		f3.addMouseListener(buildMouseAdapterFar());
		f4 = new FarPanel(match.getPlayers().get(2), controller);
		f4.setLocation(960, 0);
		f4.addMouseListener(buildMouseAdapterFar());
		
		
		
		// Cards
		cardsPanel = new CardsPanel(cards, match.getPlayers().get(0).getCards(), controller);
		cardsPanel.setSize(1274, 280);
		cardsPanel.setLocation(3, 558);
		cardsPanel.addMouseListener(buildMouseAdapterCards());
		
		// Other
		lblAge = new JLabel(new ImageIcon(MatchPanel.class.getResource("/Images/Icons/age1.png")), SwingConstants.CENTER);
		lblAge.setBounds(512, 161, 256, 128);
		lblAge.setForeground(Color.BLACK);
		lblAge.setFont(new Font("Tahoma", Font.PLAIN, 28));
		
		lblTurn = new JLabel("Round 1 of 6", SwingConstants.CENTER);
		lblTurn.setBounds(576, 295, 128, 25);
		lblTurn.setForeground(Color.BLACK);
		lblTurn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		fcp = new FullscreenCardsPanel(cards, null, c);
		scrollpane = new JScrollPane(fcp, ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane.setSize(1280, 838);
		scrollpane.getViewport().setOpaque(false);
		scrollpane.setBackground(new Color(50, 50, 50, 200));
		JLabel closeButton = new JLabel(new ImageIcon(MatchPanel.class.getResource("/Images/Icons/X.png")));
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
		
		
		
		
		rcp = new ResourceChoicePanel(c, this);
		rcp.setVisible(false);
		
		add(rcp);
		add(scrollpane);
		add(cardsPanel);
		add(playerPanel);
		add(n1);
		add(n2);
		add(f1);
		add(f2);
		add(f3);
		add(f4);
		add(lblAge);
		add(lblTurn);
		
		scrollpane.setVisible(false);
		
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
	
	public void update() {
		cardsPanel.setCards(match.getPlayers().get(0).getCards());
		if(match.getAge() == 4) {
			lblAge.setIcon(null);
			lblAge.setText("GAME OVER!");
		} else {
			lblAge.setIcon(new ImageIcon(MatchPanel.class.getResource("/Images/Icons/age" + match.getAge() + ".png")));
			lblAge.setText(null);
		}
		lblTurn.setText("Round "+ match.getTurn() +" of 6");
		playerPanel.update();
		f1.update();
		f2.update();
		f3.update();
		f4.update();
		n1.update();
		n2.update();
		cardsPanel.update();
		
		// Choose resources
		need = controller.needToChooseResources();
		Resources next = nextResource();
		rcp.setResource(next);
		if(next != null) rcp.setVisible(true);
	}
	
	public Resources nextResource() {
		if(need.size() > 0)
			return need.remove(0);
		else {
			controller.resourceChosen(picked);
			picked.clear();
			return null;
		}
	}
	
	public void resourceChosen(Resources r) {
		picked.add(r);
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
					//fcp.setCards(panel.player.getCards());
					fcp.update();
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
					fcp.update();
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
					cardsPanel.setLocation(cardsPanel.getX(), 750);
					cardsPanel.removeMouseListeners();
				} else {
					fcp.setCards(playerPanel.player.getWonderBoard().getAllCards());
					fcp.update();
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
				if(cardsPanel.getY() == 750) {
					cardsPanel.setLocation(cardsPanel.getX(), 558);
					cardsPanel.addMouseListeners();
				}
			}
		};
	}
}
