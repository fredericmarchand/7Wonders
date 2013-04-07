package View.resources;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class JTextFieldLimit extends PlainDocument {
	private static final long serialVersionUID = 1L;
	
	private int limit;
	private int type;
	public static int NUMBER_ONLY = 0;
	public static int ALL_TEXT = 1;

	JTextFieldLimit(int l, int t) {
		super();
		limit = l;
		type = t;
	}

	public void insertString( int offset, String str, AttributeSet attr ) throws BadLocationException {
		if (str == null) return;
		
		if (type == NUMBER_ONLY) {
			if(str == "0" || str == "1" || str == "2" || str == "3" || str == "4" || str == "5" || str == "6" || str == "7" || str == "8" || str == "9")
				super.insertString(offset, str, attr);
		}
		
		if ((getLength() + str.length()) <= limit) {
			super.insertString(offset, str, attr);
		}
	}
}