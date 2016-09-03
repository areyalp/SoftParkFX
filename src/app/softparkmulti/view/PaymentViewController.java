package app.softparkmulti.view;

import app.softparkmulti.model.Login;
import app.softparkmulti.util.MaskField;
import app.softparkmulti.util.NumericField;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
	private TextField txt_DPId;
	
	@FXML
	private TextField txt_DPName;
	
	@FXML
	private TextField txt_DPLastname;
	
	@FXML
	private TextField txt_VPlate;
	
	@FXML
	private TextField txt_VPropId;
	
	@FXML
	private TextField txt_VPropName;
	
	@FXML
	private TextField txt_VProLastName;
	
	@FXML
	private TextField txt_AZipCode;
	

	@FXML
	private MaskField txt_ATelephone;
	
	@FXML
	private TextArea txt_VDescription;
	
	@FXML
	private TextArea txt_ADescription;
	
	@FXML
	private ComboBox<?> txt_DEName;
	
	@FXML
	private ComboBox<?> txt_DELocal;
	
	@FXML
	private ComboBox<?> combo_VState;
	
	@FXML
	private ComboBox<?> combo_VColor;
	
	@FXML
	private ComboBox<?> combo_VBrand;
	
	@FXML
	private ComboBox<?> combo_VModel;	

	@FXML
	private ComboBox<?> combo_ACountry;

	@FXML
	private ComboBox<?> combo_AState;

	@FXML
	private ComboBox<?> combo_ACity;
	@FXML
	private TextField txt_DPEmail;
	
	@FXML
	private MaskField txt_DPPhone;
	
	@FXML
	private MaskField dp_ticketDateIn;
	
	@FXML
	private Label view_ticketTotal;
	
	@FXML
	private NumericField txt_ticketPayHanded;
	
	@FXML
	private NumericField txt_ticketPayReturned;
	
	@FXML
	private Group group_DP;
	
	@FXML
	private Group group_DE;
	
	@FXML
	private Group group_Address;
	
	@FXML
	private Group group_Vehicle;
	
	@FXML
	private CheckBox checker;
	

	public PaymentViewController() {
		// TODO Auto-generated constructor stub
	}
	
	@FXML
	private void initialize(){
		group_DE.setVisible(false);
		
		switch (Login.fromStation.getId())
		{
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			
		}
	}
	
	
	
	@FXML
	private void handleChecker(){
		if (checker.isSelected()){
			group_DP.setVisible(false);
			group_DE.setVisible(true);
		}else{
			group_DP.setVisible(true);
			group_DE.setVisible(false);
			
		}
		
	}
	
	
	

}
