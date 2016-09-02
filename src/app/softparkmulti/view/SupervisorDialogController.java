package app.softparkmulti.view;

import app.softparkmulti.model.Db;
import app.softparkmulti.model.Login;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SupervisorDialogController {
	
	@FXML
	private TextField txt_superUser;
	@FXML
	private PasswordField txt_superPass;
	@FXML
	private Button btn_accept;
	
	private boolean succeeded;
	private Stage superDialogStage;

	public SupervisorDialogController() {
		// TODO Auto-generated constructor stub
		
	}
	
    public void setDialogStage(Stage dialogStage) {
        this.superDialogStage = dialogStage;
    }
	
	@FXML
	private void initialize(){
		btn_accept.setDefaultButton(true);
	}
	
	@FXML
	private void handleAccept(){
		String username = getUsername();
		String password = getPassword();

		if (Login.authenticate(username, password)) {
            succeeded = true;
        } else {
            succeeded = false;
        }
		superDialogStage.close();

	}
	
	protected String getUsername() {
        return txt_superUser.getText().trim();
    }
 
    private String getPassword() {
        return txt_superPass.getText();
    }
    
    protected int getSuperID(){
    	return Db.getUserId(getUsername());
    }
 
    public boolean isSucceeded() {
        return succeeded;
    }

}
