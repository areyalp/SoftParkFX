package app.softparkmulti.view;

import app.softparkmulti.util.MessageBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import app.softparkmulti.model.Login;

public class LoginDialogController {

	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtPassword;
	@FXML
	private Button btn_login;
	
	private Stage dialogStage;
	private boolean succeeded;
	private boolean action; //true for access 
	
	/**
     *This method is automatically called after the fxml file has been loaded.
     */
	@FXML
	private void initialize(){
		btn_login.setDefaultButton(true);
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
		
		String username = getUsername();
		String password = getPassword();
		
		if (Login.authenticate(username, password)) {
            succeeded = true;
            MessageBox.show(dialogStage, "Acceso concedido", 
        			"Usuario y contraseña válidos", 
        			"You're good to go :)",
        			MessageBox.typeConfirmation);
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
            succeeded = false;
        	
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
