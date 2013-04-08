package view.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import image.Images;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.MClient;
import java.awt.Font;

public class LobbyPanel extends JPanel implements ListSelectionListener {
	private static final long serialVersionUID = 1L;

	private JList<String> list;
	private JLabel bgimg;
	private JButton create, join, quit, refresh;
	private int index = 0;
	private MClient mclient;
	private DefaultListModel<String> listModel;
	
	public LobbyPanel(MClient c) {
		setLayout(null);
		setSize(1280, 860);
		
		listModel = new DefaultListModel<String>();
		
		create = new JButton("Create");
		create.setLocation(278, 231);
		create.setFont(new Font("Tahoma", Font.BOLD, 22));
		create.setSize(151, 87);
		
		refresh = new JButton("Refresh");
		refresh.setSize(151, 87);
		refresh.setLocation(278, 427);
		refresh.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		join = new JButton("Join");
		join.setSize(151, 87);
		join.setLocation(278, 329);
		join.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		quit = new JButton("Quit");
		quit.setSize(151, 87);
		quit.setLocation(278, 525);
		quit.setFont(new Font("Tahoma", Font.BOLD, 22));
		
		list = new JList<String>(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
		list.addListSelectionListener(this);
		list.setVisibleRowCount(10);
		list.setBounds(441, 231, 554, 381);
		add(list);
		
		mclient = c;
		JScrollPane listScrollPane = new JScrollPane();
		listScrollPane.setLocation(439, 231);
		listScrollPane.setSize(556, 381);
		
		create.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mclient.getMainFrame().launchCreateMenu(mclient.getLink().launchCreateMenu());
				setVisible(false);
			}
		});
		
		join.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(list.getSelectedIndex()>-1){
					String matchName = (list.getSelectedValue()).toString();
					mclient.sendMatchRequest(matchName);
					setVisible(false);
				}
			}
		});
		
		quit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
		refresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				mclient.sendMatchListRequest();
			}
		});
		
		
		add(create);
		add(join);
		add(refresh);
		add(quit);
		add(listScrollPane);
		
		bgimg = new JLabel();
		bgimg.setLocation(0, 0);
		bgimg.setSize(1280, 860);
		add(bgimg);
		

	}
	
	public void showGUI() {
		bgimg.setIcon(Images.get("bg"));
		setVisible(true);
	}
	
	public void update(ArrayList<String> list){
		listModel.clear();
		for(String id: list) listModel.insertElementAt(id, index++);
		index = 0;
	}
	
	public void failedJoin(){
		JOptionPane.showMessageDialog(null, "Match is full or in progress.\nTry another one!");
	}
	
	@Override
	public void valueChanged(ListSelectionEvent arg0) {}
}
