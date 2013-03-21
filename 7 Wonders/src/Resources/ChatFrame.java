package Resources;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class ChatFrame extends JPanel {
	protected JTextField textField;
	protected JTextArea textArea;
	private JFrame frame;
	private final static String newline = "\n";
	private Chat chatHub;
	JScrollPane scrollPane;
	JPanel p;
	JMenu menu;
	JMenuItem start,quit;
	JMenuBar menuBar;

	public ChatFrame(Chat ch,String uname) {
		super(new GridBagLayout());
		chatHub = ch;
		textField = new JTextField(20);
		frame = new JFrame(uname + " - 7 Wonders Chat");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuBar = new JMenuBar();
		menu = new JMenu("Match Options");
		start = new JMenuItem("Start");
		quit = new JMenuItem("Quit");
		
		
		
		// Add contents to the window.
		frame.add(this);
		// Display the window.
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				String text = textField.getText();
				textField.setText("");
				chatHub.sendMsg(text);
				textArea.setCaretPosition(textArea.getDocument().getLength());
			}
		});
		textArea = new JTextArea(5, 20);
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);

		// Add Components to this panel.
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;

		c.fill = GridBagConstraints.HORIZONTAL;
		add(textField, c);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 1.0;
		add(scrollPane, c);
		
		menu.add(start);
		menu.add(quit);
		menuBar.add(menu);
		menuBar.setSize(50,this.getX());
		menuBar.setVisible(true);
		frame.add(menuBar);
	}

	public void appendChat(String c) {
		textArea.append(c + newline);
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

	public void createAndShowGUI() {
		// Create and set up the window.
		frame.pack();
		frame.setVisible(true);

	}
	
	public static void main(String args[]){
		ChatFrame f = new ChatFrame(null,null);
		f.createAndShowGUI();
	}

}
