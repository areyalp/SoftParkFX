package app.softparkmulti.view;

import java.util.ArrayList;

import app.softparkmulti.model.Db;
import app.softparkmulti.model.Login;
import app.softparkmulti.model.Transaction;
import app.softparkmulti.util.MaskField;
import app.softparkmulti.util.MessageBox;
import app.softparkmulti.util.NumericField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
	private Group group_Additional;
	
	@FXML
	private CheckBox checker;
	
	private ArrayList<Transaction> transactionsType;
	private int indexTType;
	private Stage dialogStage;
	private boolean isLost =false, isManual =false;


	public PaymentViewController() {
		// TODO Auto-generated constructor stub
		
	}
	
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setTicketLost(boolean state){isLost = state;}
    
    public boolean getTicketLost(){return isLost;}
    
    public void setTicketManual(boolean state){isManual = state;} 
    
    public boolean getTicketManual(){return isManual;}
    
    private String getStationAmount(int index){
	
	transactionsType = Db.loadTransactionTypes();
	Transaction transaction = null;
		try{
			transaction = transactionsType.get(index-1);
			return Double.toString(transaction.getMaxAmount());
		}catch(Exception ex){ex.printStackTrace();}
		
		return "";
		
	}
    
    private void init_groups()
    {
		group_DE.setVisible(false);
		
		if (!getTicketManual() && !getTicketLost()){

			group_Vehicle.setVisible(false);
			group_Address.setVisible(false);
			group_Additional.setVisible(false);
			
		}
		
		if(getTicketLost()){
			group_Vehicle.setVisible(true);
			group_Address.setVisible(true);
			group_Additional.setVisible(true);
		}
		if (getTicketManual()){
			group_Vehicle.setVisible(false);
			group_Address.setVisible(false);
			group_Additional.setVisible(true);
		}

		
    }

    private void setTType(int stationType){
    	switch (stationType)
		{
			case 1: //Caja
				indexTType = 3;
				break;
			case 2: //Entrada
				indexTType = 3;
				break;
			case 3: //Salida
				indexTType = 3;
				break;
			case 4: //Valet
				indexTType = 1;
				break;
			case 5: //Entrada/salida
				indexTType = 3;
				break;
		}
    	
    }
    
	@FXML
	private void initialize(){

		 Platform.runLater( () ->init_groups());
		 Platform.runLater(() ->txt_ticketNumber.requestFocus());
		 setTType(Login.fromStation.getType());
		view_ticketTotal.setText(getStationAmount(indexTType) + " Bs.");
		
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
