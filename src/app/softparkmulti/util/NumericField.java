package app.softparkmulti.util;

import javafx.scene.control.TextField;

public class NumericField extends TextField {

	public NumericField() {
		//setMinWidth(25);
		//setMaxWidth(25);
		//setPromptText("Only numbers");
		// TODO Auto-generated constructor stub
	}
	
	public void replaceText(int start, int end, String text) {
        //String oldValue = getText();
        if (!text.matches("[a-z]") && !text.matches("[\\\\!\"#$%&()*+/:;<=>?@\\[\\]^_{|}~]+")) {
            super.replaceText(start, end, text);
        }
       /* if (getText().length() > 2 ) {
            setText(oldValue);
        }*/
    }

    public void replaceSelection(String text) {
        //String oldValue = getText();
        if (!text.matches("[a-z]") && !text.matches("[\\\\!\"#$%&()*+/:;<=>?@\\[\\]^_{|}~]+")) {
            super.replaceSelection(text);
        }
        /*if (getText().length() > 2 ) {
            setText(oldValue);
        }*/
    }
	

}
