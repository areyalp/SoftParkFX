package app.softparkmulti.view;

import app.softparkmulti.util.MessageBox;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.sql.ResultSet;

import app.softparkmulti.MainSoftPark;
import app.softparkmulti.model.Db;
import app.softparkmulti.model.GetNetworkAddress;
import app.softparkmulti.model.Login;
import app.softparkmulti.model.Station;

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
		 checkStation();
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
	
	public void checkStation(){
		
    	try{
			Db db = new Db();
			String macAddress = GetNetworkAddress.GetAddress("mac");
			
			/*MessageBox.show(dialogStage, 
    				"Test",
    				"MAC is:", 
    				macAddress, 
    				MessageBox.typeInformation);*/
			
			
			if(!(macAddress == null)){
				if(true) {
					
					ResultSet rowsMac = db.select("SELECT Id, TypeId, Name FROM Stations WHERE"
							+ " MacAddress = '" + macAddress + "'");
					
					if(rowsMac.next()){
						
						Login.fromStation = 
								new Station(
								rowsMac.getInt("Id"),
								rowsMac.getInt("TypeId"),
								rowsMac.getString("Name"));
						
						/*MessageBox.show(dialogStage, 
			    				"Test",
			    				"Station: ", 
			    				Login.fromStation.getName(), 
			    				MessageBox.typeInformation);*/
						
					}else{
						
						//select one station from a list
					}
				}
			}else{
				
				MessageBox.show(dialogStage, 
	    				"Error al obtener la estación",
	    				"No está conectado a la red", 
	    				"Conéctese a la red e intente de nuevo", 
	    				MessageBox.typeError);
				
			}
    	}catch(Exception ex){
    		
    		MessageBox.show(dialogStage, 
    				"Error al obtener la estación",
    				"Ocurrió un error al tratar de obtener la dirección MAC de la estación. ", 
    				"Detalle: " + ex.toString(), 
    				MessageBox.typeError);
    	}
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
