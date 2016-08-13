package app.softparkmulti.view;

import app.softparkmulti.util.MessageBox;
import javafx.fxml.FXML;
import javafx.stage.Stage;

public class HomeViewController {

	private Stage homeStage;
	
	public HomeViewController() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 *This method is automatically called after the fxml file has been loaded.
     */
		@FXML
		private void initialize(){
			
		}
		@FXML
		private void handleCierre(){
			
			MessageBox.show(homeStage, "Cierre", "Testing ", " ", MessageBox.typeInformation);
		}
		@FXML
		private void handleCobro(){
			
			MessageBox.show(homeStage, "Cobro", "Testing", " ", MessageBox.typeInformation);
		}
		@FXML
		private void handleTicket_Manual(){
			
			MessageBox.show(homeStage, "Ticket manual", "Testing", " ", MessageBox.typeInformation);
		}
		@FXML
		private void handleTicket_Lost(){
			MessageBox.show(homeStage, "Ticket perdido", "Testing", " ", MessageBox.typeInformation);
			
		}

	    public void setDialogStage(Stage dialogStage) {
	        this.homeStage = dialogStage;
	    }
		
}
