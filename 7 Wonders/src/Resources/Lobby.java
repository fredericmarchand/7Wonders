package Resources;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import client.MClient;



@SuppressWarnings("serial")
public class Lobby extends JFrame implements ListSelectionListener {
	private JList list;
	private JButton create,join,quit,refresh;
	JPanel buttonPanel;
	private int index = 0;
	private MClient mclient;
	private DefaultListModel listModel;
	
	
	
	
	@SuppressWarnings("unchecked")
	public Lobby(MClient c){
		setLayout(new BorderLayout());
		listModel = new DefaultListModel();
		list = new JList(listModel);
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
	    
	    buttonPanel.setLayout(new BoxLayout(buttonPanel,
                BoxLayout.LINE_AXIS));
	    buttonPanel.add(create);
        buttonPanel.add(Box.createHorizontalStrut(5));
        buttonPanel.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPanel.add(Box.createHorizontalStrut(5));
        buttonPanel.add(join);
        buttonPanel.add(refresh);
        buttonPanel.add(quit);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));   
	    
        JScrollPane listScrollPane = new JScrollPane(list);
        
        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.PAGE_END);
        
        
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
	    setSize(300,200);
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		 this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        populate();
	}
	
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@SuppressWarnings("unchecked")
	public void populate(){
		for(Long id: mclient.getMatchList()){
			System.out.println(id);
			listModel.insertElementAt(id, index);
			index++;
		}
	}
	
	public void update(ArrayList<Long> list){

		for(Long id: list){
			listModel.insertElementAt(id, index++);
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
