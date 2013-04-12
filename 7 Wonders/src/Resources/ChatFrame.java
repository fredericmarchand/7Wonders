package Resources;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import view.resources.JTextFieldLimit;

public class ChatFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextField txtField;
	private JTextPane textArea;
	private StyledDocument doc;
	private final static String newline = "\n";
	private Chat chatHub;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JMenu menu;
	private JMenuItem start, quit;
	private JMenuBar menuBar;
	private String username;
	
	private SimpleAttributeSet me, them, server;

	public ChatFrame(Chat ch, String uname) {
		username = uname;
		setTitle(uname + " - 7 Wonders Chat");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(280, 400);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setSize(280, 600);
		chatHub = ch;
		
		me = new SimpleAttributeSet();
		StyleConstants.setForeground(me, Color.RED);
		StyleConstants.setBackground(me, Color.LIGHT_GRAY);
		
		them = new SimpleAttributeSet();
		StyleConstants.setForeground(them, Color.BLUE);
		StyleConstants.setBackground(them, Color.LIGHT_GRAY);
		
		server = new SimpleAttributeSet();
		StyleConstants.setForeground(server, new Color(0, 150, 0));
		StyleConstants.setBackground(server, Color.LIGHT_GRAY);
		StyleConstants.setBold(server, true);
		
		
		txtField = new JTextField();
		txtField.setDocument(new JTextFieldLimit(200, JTextFieldLimit.ALL_TEXT));
		txtField.setBounds(0, 0, 263, 21);
		
		
		textArea = new JTextPane();
		doc = textArea.getStyledDocument();
		textArea.setSize(280, 570);
		textArea.setLocation(0, 0);
		textArea.setEditable(false);
		
		scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(0, 21, 263, 324);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		menuBar = new JMenuBar();
		menu = new JMenu("Match Options");
		start = new JMenuItem("Start");
		quit = new JMenuItem("Quit");
		
		// Add contents to the window.
		
		// Display the window.
		txtField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				if(!txtField.getText().equals("")) {
					String text = txtField.getText();
					txtField.setText("");
					chatHub.sendMsg(text);
					textArea.setCaretPosition(textArea.getDocument().getLength());
				}
			}
		});
		
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				chatHub.getClient().sendStartRequest();
			}
		});
		
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				int option = JOptionPane.showConfirmDialog (null, "Do you wish to quit the match?");
				if(option == JOptionPane.YES_OPTION){
					dispose();
					//CLIENT KILL CONNECTION CLOSE EXTERNAL CONNECTIONS
					chatHub.getClient().quitMatch();
				}
			
			}
		});	


		// Add Components to this panel.
		
		panel.add(txtField);
		panel.add(scrollPane);
		setContentPane(panel);
		
		menuBar.add(menu);
		menu.add(start);
		menu.add(quit);
		setJMenuBar(menuBar);
		
	}

	public void appendChat(String u, String m) {
		try {
			SimpleAttributeSet sas = null;
			if(u.equals("SYSTEM")) sas = server;
			else if(u.equals(username)) sas = me;
			else sas = them;
			doc.insertString(doc.getLength(), "[" + u + "]", sas);
			doc.insertString(doc.getLength(), ": " + m + newline, null);
		} catch (BadLocationException e) {
			System.out.println(e);
		}
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	public void showGUI() {
		// Create and set up the window.
		setVisible(true);
	}
	
	public void setStart(boolean b){
		if(b){
			start.setEnabled(true);
			quit.setEnabled(false);
		}
		else{
			start.setEnabled(false);
			quit.setEnabled(true);
		}
	}
	
	public void clear(){
		textArea.setText("");
		txtField.setText("");
	}
		
	
	public static void main(String args[]){
		ChatFrame f = new ChatFrame(null, null);
		f.setStart(false);
		f.showGUI();
	}
	

}
