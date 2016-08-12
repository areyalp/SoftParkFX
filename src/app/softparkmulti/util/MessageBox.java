package app.softparkmulti.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MessageBox {

	public static String typeInformation = "INFORMATION";
	public static String typeConfirmation = "CONFIRMATION";
	public static String typeWarning = "WARNING";
	public static String typeError = "ERROR";
	public static String typeNone = "NONE";
	
	public MessageBox() {
		// TODO Auto-generated constructor stub
	}

	
	public static void show(Stage dialogStage, String title,
			String header, String msg,String alerttype)
	{
		Alert alert = new Alert(AlertType.valueOf(alerttype));
        alert.initOwner(dialogStage);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(msg);
        alert.showAndWait();
		
	}
	
	
}
