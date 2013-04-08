package Resources;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;

import client.MClient;

public class Lobby extends JFrame implements ListSelectionListener {
	private static final long serialVersionUID = 1L;
	private JList<String> list;
	
	private JButton create, join, quit, refresh;
	JPanel buttonPanel;
	private int index = 0;
	private MClient mclient;
	private DefaultListModel<String> listModel;
	
	public Lobby(MClient c){
		getContentPane().setLayout(new BorderLayout());
		listModel = new DefaultListModel<String>();
		list = new JList<String>(listModel);
		create = new JButton("CREATE");
		refresh = new JButton("REFRESH");
		join = new JButton("JOIN");
		quit = new JButton("QUIT");
		buttonPanel = new JPanel();
		mclient = c;
		

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setSelectedIndex(0);
	    list.addListSelectionListener(this);
	    
	    list.setVisibleRowCount(10);
	    
	    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
	    buttonPanel.add(create);
        buttonPanel.add(join);
        buttonPanel.add(refresh);
        buttonPanel.add(quit);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));   
	    
        JScrollPane listScrollPane = new JScrollPane(list);
        
        getContentPane().add(listScrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
        
        
	    create.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
            	setVisible(false);
	            mclient.getLink().launchCreateMenu();
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
	    setSize(288,200);
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	}
	
	public void valueChanged(ListSelectionEvent e) {}
	
	public void update(ArrayList<String> list){
		listModel.clear();
		for(String  s: list){
			listModel.insertElementAt(s, index++);
		}
		index = 0;
	}
	
	public void failedJoin(){
		JOptionPane.showMessageDialog(null, "Match is full or in progress \nTry another one!");
	}
	
	public void showGUI() {
 
        //Display the window.
        pack();
        setVisible(true);
    }
}
