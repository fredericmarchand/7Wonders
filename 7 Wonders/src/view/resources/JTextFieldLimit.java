package view.resources;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {
	private static final long serialVersionUID = 1L;
	
	private int limit;
	private int type;
	public static int NUMBER_ONLY = 0;
	public static int ALL_TEXT = 1;

	public JTextFieldLimit(int l, int t) {
		super();
		limit = l;
		type = t;
	}

	public void insertString( int offset, String str, AttributeSet attr ) throws BadLocationException {
		if (str == null) return;

		if ((getLength() + str.length()) <= limit) {
			if (type == NUMBER_ONLY) {
				if(str.equals("0") || str.equals("1") || str.equals("2") || str.equals("3") 
					|| str.equals("4") || str.equals("5") || str.equals("6") || str.equals("7") 
					|| str.equals("8") || str.equals("9"))
					super.insertString(offset, str, attr);
				else
					return;
			} else {
				super.insertString(offset, str, attr);
			}
		}
	}
}