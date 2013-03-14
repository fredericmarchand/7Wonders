package Resources;


/* TextDemo.java requires no other files. */
 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 
public class ChatFrame extends JPanel implements ActionListener {
    protected JTextField textField;
    protected JTextArea textArea;
    private final static String newline = "\n";
    private Chat chatHub;
    private String message;
 
    public ChatFrame(Chat ch) {
        super(new GridBagLayout());
        chatHub = ch;
        textField = new JTextField(20);
        textField.addActionListener(this);
 
        textArea = new JTextArea(5, 20);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
 
        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
 
        c.fill = GridBagConstraints.HORIZONTAL;
        add(textField, c);
 
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane, c);
    }
 
    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        textField.setText("");
        //textArea.append(text + newline);
        String message = text;
        chatHub.sendMsg(message);
        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }
 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public void appendChat(String c){
    	textArea.append(c + newline);
    }
    public void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("7 Wonders Chat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Add contents to the window.
        frame.add(new ChatFrame(chatHub));
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
//    public static void main(String[] args) {
//        //Schedule a job for the event dispatch thread:
//        //creating and showing this application's GUI.
//        javax.swing.SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                createAndShowGUI();
//            }
//        });
//    }
}
