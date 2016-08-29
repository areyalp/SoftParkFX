package app.softparkmulti.view;

import app.softparkmulti.util.NumericField;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PaymentViewController {
	
	@FXML
	private TextField txt_ticketNumber;
	
	@FXML
	private TextField txt_ticketEntrance;
	
	@FXML
	private TextField txt_ticketTimeSpent;
	
	@FXML
	private TextField txt_ticketExpiry;
	
	@FXML
	private DatePicker dp_ticketDateIn;
	
	@FXML
	private Label view_ticketTotal;
	
	@FXML
	private NumericField txt_ticketPayHanded;
	
	@FXML
	private NumericField txt_ticketPayReturned;
	

	public PaymentViewController() {
		// TODO Auto-generated constructor stub
	}
	
	
	
	

}
