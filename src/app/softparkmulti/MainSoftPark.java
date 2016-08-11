package app.softparkmulti;

import java.io.IOException;

import app.softparkmulti.view.LoginDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainSoftPark extends Application {

	private Stage primaryStage;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SoftPark - Login");

        showLoginDialog();
		
	}

	/**
     * Initializes the root layout.
     */
    public void showLoginDialog() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainSoftPark.class.getResource("view/loginDialog.fxml"));
            AnchorPane loginDialog = (AnchorPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(loginDialog);

            //Show the root stage
            primaryStage.setScene(scene);
            primaryStage.show();
            
            //instantiate the loginDialogController to set the dialog stage on the controller class
            LoginDialogController loginController = loader.getController();
            loginController.setDialogStage(primaryStage);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
