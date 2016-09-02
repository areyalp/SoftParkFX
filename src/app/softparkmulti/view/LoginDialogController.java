package app.softparkmulti.view;

import app.softparkmulti.util.MessageBox;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import app.softparkmulti.MainSoftPark;
import app.softparkmulti.model.Login;

public class LoginDialogController {

	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Button btn_login;
	
	private MainSoftPark mainSoftPark;
	private Stage dialogStage;
	private boolean succeeded;
	private boolean action; //true for access 
	
	/**
     *This method is automatically called after the fxml file has been loaded.
     */
	@FXML
	private void initialize(){
		btn_login.setDefaultButton(true);
		 Platform.runLater( () -> txtUser.requestFocus() );
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
    
	 /* Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainSoftPark mainApp) {
        this.mainSoftPark = mainApp;

    }
	
	
	@FXML
	private void handleLogin(){
		
		String username = getUsername();
		String password = getPassword();
		
		if (Login.authenticate(username, password)) {
            succeeded = true;
            Login.setUserInfo(getUsername());
            dialogStage.close();
            action = true;
            //dispose();
        } else {
        	
        	MessageBox.show(dialogStage, "Acceso denegado", 
        			"Usuario o contraseña inválidos", 
        			"Por favor, verifique las credenciales introducidas.",
        			MessageBox.typeError);
        	
        	// reset username and password
            txtUser.setText("");
            txtPassword.setText("");
            txtUser.requestFocus();
            succeeded = false;
            action = false;
        	
        }
		
	}
	
	@FXML
	private void handleCancel(){
		dialogStage.close();
		
	}
	
	
	protected String getUsername() {
        return txtUser.getText().trim();
    }
 
    private String getPassword() {
        return txtPassword.getText();
    }
 
	
    protected boolean isSucceeded() {
        return succeeded;
    }
    
    public boolean getAction(){
    	return action;
    }

	
	

}
