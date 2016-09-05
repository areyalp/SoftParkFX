package app.softparkmulti.view;

import java.util.ArrayList;

import org.joda.time.DateTime;

import app.softparkmulti.model.Db;
import app.softparkmulti.model.Login;
import app.softparkmulti.model.Ticket;
import app.softparkmulti.model.Transaction;
import app.softparkmulti.util.DateUtil;
import app.softparkmulti.util.MaskField;
import app.softparkmulti.util.MessageBox;
import app.softparkmulti.util.NumericField;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class PaymentViewController {
	
	@FXML
	private Button btn_accept;
	
	@FXML
	private Button btn_cancel;
	
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
	private Group group_ticket;
	
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
    
    private double calcChange(double value1, double value2){
    	if (value2 > value1)
    		return value2 - value1;
    	else
    		return 0;
    }

    private boolean getPayState(double value1,double value2){
    	if (value1 >= value2)
    		return true;
    	else
    		return false;
    }
    
    private void init_groups()
    {
		group_DE.setVisible(false);
		btn_accept.setDisable(true);
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
    
    private void fieldsState(boolean state)  {
    	if (state)
    	{
    		txt_ticketNumber.setEditable(true);
    		txt_ticketEntrance.setEditable(true);
    		txt_ticketTimeSpent.setEditable(true);
    		txt_ticketExpiry.setEditable(true);
    		dp_ticketDateIn.setEditable(true);
    		txt_ticketNumber.clear();
    		txt_ticketEntrance.clear();
    		txt_ticketTimeSpent.clear();
    		txt_ticketExpiry.clear();
    		dp_ticketDateIn.clear();
    		txt_ticketPayReturned.clear();
    		txt_ticketPayHanded.clear();
    		Platform.runLater(() ->txt_ticketNumber.requestFocus());
    		
    		
    	}else{
    		
    		txt_ticketNumber.setEditable(false);
    		txt_ticketEntrance.setEditable(false);
    		txt_ticketTimeSpent.setEditable(false);
    		txt_ticketExpiry.setEditable(false);
    		dp_ticketDateIn.setEditable(false);
    		txt_ticketPayReturned.setEditable(false);
    		Platform.runLater(() ->txt_ticketPayHanded.requestFocus());
    		
    	}
    }
    
    
    
    private void loadTicketUI(Ticket ticket){
    	txt_ticketEntrance.setText(Integer.toString(ticket.getEntryStationId()));		

    	String duration = DateUtil.getDuration(new DateTime(ticket.getEntryDate()), 
    			DateTime.now());
	
    	txt_ticketTimeSpent.setText(duration);
    	
    	 dp_ticketDateIn.setText(ticket.getEntryDate().toString());
    	
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
	private void handleTicketN_keyReleased(KeyEvent event){
		if (event.getCode() == KeyCode.ENTER){
		    int ticketNum = Integer.parseInt(txt_ticketNumber.getText());
		    Db db = new Db();
		    Ticket ticket = db.loadTicketInfo(ticketNum);
		    if (ticket!=null){
		    	loadTicketUI(ticket);
		    	fieldsState(false);
		    }else{
		    	
		    	MessageBox.show(dialogStage, 
		    			"Ticket no registrado", 
		    			"El número de ticket ingresado no fue "
		    			+ "encontrado en la base de datos", "", 
		    			MessageBox.typeWarning);
		    }
		    	
		  }
	}
	
	@FXML
	private void handleTicketPH_keyReleased(KeyEvent event){
		double total = Double.parseDouble(view_ticketTotal.getText().substring(0,
				view_ticketTotal.getText().length()-4));
		double handed = 0;
		String strHanded = txt_ticketPayHanded.getText();
		if (!strHanded.equals("")){
			handed = Double.parseDouble(strHanded);
		}
		double change = calcChange(total,handed);
		txt_ticketPayReturned.setText(Double.toString(change));
		if (getPayState(handed,total)){
			btn_accept.setDisable(false);
		}else{btn_accept.setDisable(true);}
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
	
	@FXML 
	private void handleAccept(){
		
	}
	
	@FXML
	private void handleCancel(){
		fieldsState(true);
		
	}
	
	

}
