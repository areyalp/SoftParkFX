package app.softparkmulti;

import java.io.IOException;

import app.softparkmulti.view.HomeViewController;
import app.softparkmulti.view.LoginDialogController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainSoftPark extends Application {

	private Stage primaryStage;
	
	
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
        this.primaryStage.setTitle("SoftPark - Home");

        if(showLoginDialog())
        {
        	showHomeView();
        	
        }
		
	}

	/**
     * Initializes login layout.
     */
    public boolean showLoginDialog() {
    	boolean result = false;
        try {
        	//Load fxml layout
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainSoftPark.class.getResource("view/loginDialog.fxml"));
            AnchorPane loginDialog = (AnchorPane) loader.load();

         // Create the dialog Stage.
            Stage loginStage = new Stage();
            loginStage.setTitle("SoftPark - Login");
            loginStage.initModality(Modality.WINDOW_MODAL);
            loginStage.initOwner(primaryStage);
	        
	        
            //instantiate the loginDialogController to set the dialog stage on the controller class
            LoginDialogController loginController = loader.getController();
            loginController.setDialogStage(loginStage);

            
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(loginDialog);
            //Show the root stage
            loginStage.setScene(scene);
            loginStage.showAndWait();
            result = loginController.getAction();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public void showHomeView() {
	    try {
	        // Load the root fxml file
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainSoftPark.class.getResource("view/homeView.fxml"));
	        BorderPane homeView = (BorderPane) loader.load();

	        // Create the dialog Stage.
	        Scene scene = new Scene(homeView);
	        primaryStage.setScene(scene);
	        primaryStage.setTitle("SoftPark - Home");
	        primaryStage.setMaximized(true);
            primaryStage.show();
            
            //Instantiate the controller
            HomeViewController homeController = loader.getController();
            homeController.setDialogStage(primaryStage);

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
    
    
    
    
    
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
