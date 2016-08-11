package app.softparkmulti.view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class LoginDialogController {

	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtPassword;
	
	private Stage dialogStage;
	
	/**
     *This method is automatically called after the fxml file has been loaded.
     */
	@FXML
	private void initialize(){
		
	}
	
	public LoginDialogController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	
	@FXML
	private void handleLogin(){
		
		String username = txtUser.getText();
		String password = txtPassword.getText();
		
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Hey!");
        alert.setHeaderText("You're trying to access with the following credentials:");
        alert.setContentText("Username: " + username
        		+ "\r\n Password: " + password + "\r\n Click OK to continue...");
        alert.showAndWait();
		
	}
	
	@FXML
	private void handleCancel(){
		dialogStage.close();
		
	}
	
	
	

}
