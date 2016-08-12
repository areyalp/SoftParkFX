package app.softparkmulti.view;

import app.softparkmulti.util.MessageBox;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import app.softparkmulti.model.Login;

public class LoginDialogController {

	@FXML
	private TextField txtUser;
	@FXML
	private PasswordField txtPassword;
	
	private Stage dialogStage;
	private boolean succeeded;
	
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
		
		String username = getUsername();
		String password = getPassword();
		
		if (Login.authenticate(username, password)) {
            succeeded = true;
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
		
		/*Alert alert = new Alert(AlertType.INFORMATION);
        alert.initOwner(dialogStage);
        alert.setTitle("Hey!");
        alert.setHeaderText("You're trying to access with the following credentials:");
        alert.setContentText("Username: " + username
        		+ "\r\n Password: " + password + "\r\n Click OK to continue...");
        alert.showAndWait();*/
		
	}
	
	@FXML
	private void handleCancel(){
		dialogStage.close();
		
	}
	
	
	protected String getUsername() {
        return txtUser.getText().trim();
    }
 
    private String getPassword() {
        return new String(txtPassword.getText());
    }
 
	
    protected boolean isSucceeded() {
        return succeeded;
    }
	
	
	

}
