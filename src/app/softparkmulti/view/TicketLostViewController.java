package app.softparkmulti.view;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class TicketLostViewController {
	
	@FXML
	private TextField txt_carPlate;
	
	@FXML
	private TextField txt_ID;
	
	@FXML
	private TextField txt_stationEntrance;
	
	@FXML
	private DatePicker dp_DateInit;
	
	@FXML
	private DatePicker dp_DateEnd;
	
	@FXML
	private ComboBox<?> combo_lostType;
	
	@FXML
	private ComboBox<?> combo_entranceLevel;

	public TicketLostViewController() {
		// TODO Auto-generated constructor stub
	}
	
	
	

}
