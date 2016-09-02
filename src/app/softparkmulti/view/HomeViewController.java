package app.softparkmulti.view;

import app.softparkmulti.model.PrinterCommand;
import app.softparkmulti.model.User;
import app.softparkmulti.util.CommPortUtils;
import app.softparkmulti.util.MessageBox;

import java.io.IOException;

import app.softparkmulti.MainSoftPark;
import app.softparkmulti.model.Db;
import app.softparkmulti.model.Login;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import tfhka.PrinterException;
import tfhka.ve.Tfhka;

public class HomeViewController {

	@FXML
	private Menu item_ports;
	@FXML
	private Label label_status;
	@FXML
	private MenuButton btn_printer;
	@FXML
	private MenuItem item_conn,item_test, item_calc,
		item_ZReport,item_XReport,item_logout,item_exit;
	@FXML
	private CheckMenuItem item_viewToolBar,
		item_viewStatusBar;
	@FXML
	private HBox toolBar;
	@FXML
	private VBox upper_box;
	@FXML
	private HBox lower_box;
	
	
	private MainSoftPark mainSoftPark;
	private SupervisorDialogController superLoginDialog;
	private Stage homeStage;
	
	private boolean isPrinterConnected;
	private Tfhka fiscalPrinter;
	private String activePort;


	
	public HomeViewController() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 *This method is automatically called after the fxml file has been loaded.
     */
		@FXML
		private void initialize(){
			fiscalPrinter = new tfhka.ve.Tfhka();
			getCOMPortItems();
		}
		
		@FXML
		private void handleCierre(){
			
			MessageBox.show(homeStage, "Cierre", "Testing ", " ", MessageBox.typeInformation);
		}
		
		@FXML
		private void handleCobro(){
			
			//MessageBox.show(homeStage, "Cobro", "Testing", " ", MessageBox.typeInformation);
			mainSoftPark.showPaymentView();
		}
		
		@FXML
		private void handleTicket_Manual(){
			
			MessageBox.show(homeStage, "Ticket manual", "Testing", " ", MessageBox.typeInformation);
		}
		
		@FXML
		private void handleTicket_Lost(){
			//MessageBox.show(homeStage, "Ticket perdido", "Testing", " ", MessageBox.typeInformation);
			mainSoftPark.showTicketLostView();
		}
		
		@FXML
		private void handlePrinter(){
			
		}
		
		@FXML
		private void handlePrinterConn(){
			
			
			if (!activePort.isEmpty() && !activePort.equals(null)) {
				
				startThread(item_conn.getText());

				
			} else {
				label_status.setText("No hay puerto COM activo");
			}
		

		}
		
		
		@FXML
		private void handlePrinterPort(){
			//MessageBox.show(homeStage, "Printer port", "Testing", " ", MessageBox.typeInformation);
			
		}
		
		@FXML
		private void handleTest(){

			//boolean sentCmd =false;
			if (isPrinterConnected)
			{
				try {
					 //sentCmd = 
					fiscalPrinter.SendCmd(PrinterCommand.printTest());
	
				} catch (PrinterException e) {
					e.printStackTrace();
				}
			}
		}
		
		@FXML
		private void handleLogout(){
			homeStage.hide();
			if (mainSoftPark.showLoginDialog())
			{
				homeStage.setTitle("SoftPark - Home                                 "
		        		+ "Bienvenido/a: " + Login.loggedUser.getName());
				homeStage.show();
			}
		
			
		}
		
		@FXML
		private void handleZReport(){
			
			if (mainSoftPark.showSupervisorDialog())
			{
				final Db db = new Db();
				User supervisor = db.loadUserInfo(superLoginDialog.getSuperID());
				if(supervisor.canPrintReportZ) {

						startThread("ReporteZ");

				}else{
					MessageBox.show(homeStage, "Error", 
				
	        			"Acceso no autorizado.", 
	        			"El usuario ingresado no tiene permisos para generar el reporte Z.",
	        			MessageBox.typeError);
				}
			
			} else{
	        	MessageBox.show(homeStage, "Acceso denegado", 
	        			"Usuario o contraseña inválidos", 
	        			"Por favor, verifique las credenciales introducidas.",
	        			MessageBox.typeError);
			}
		}
		
		
		@FXML
		private void handleXReport(){
			
			startThread("ReporteX");
	
		}
		
		

	    
	    @FXML
	    private void handleToolBar(){
	    	if (item_viewToolBar.isSelected())
	    	{
	    		toolBar.setVisible(true);
	    		upper_box.setPrefHeight(98);
	    		
	    	}else{
	    		toolBar.setVisible(false);
	    		upper_box.setPrefHeight(25);
	    	}
	    	
	    }
	    
	    @FXML
	    private void handleStatusBar(){
	    	
	    	if (item_viewStatusBar.isSelected())
	    	{
	    		lower_box.setVisible(true);
	    		lower_box.setPrefHeight(55);
	    		
	    	}else{
	    		lower_box.setVisible(false);
	    		lower_box.setPrefHeight(0);
	    		
	    	}
	    }
	    @FXML
	    private void handleExit(){
	    	homeStage.close();
	    }
	    
	    @FXML
	    private void handleCalc(){
	    	try {
				Runtime.getRuntime().exec("calc");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
	    }
	    	
	    /* Is called by the main application to give a reference back to itself.
	     * 
	     * @param mainApp
	     */
	    public void setMainApp(MainSoftPark mainApp) {
	        this.mainSoftPark = mainApp;

	    }
	    
	    public void setSupervisorDialog(SupervisorDialogController superLogin){
	    	this.superLoginDialog = superLogin;
	    	
	    }
		
	    public void setDialogStage(Stage dialogStage) {
	        this.homeStage = dialogStage;
	    }
	    
	    
	    public void getCOMPortItems(){
	    	
	    	String[] serialPorts = null;
			try {

				serialPorts = CommPortUtils.getSerialPorts();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (serialPorts.length == 0) {
				
				item_ports.getItems().add(new MenuItem("No hay puertos COM"));

			} else {
				for (String port : serialPorts) {
					
					MenuItem portItem = new MenuItem(port);
					
					portItem.setOnAction(new EventHandler<ActionEvent>(){
						
						public void handle(ActionEvent e){
							activePort = portItem.getText();
							label_status.setText("Puerto " + activePort + " seleccionado.");
							startThread("Conectar");
						}
						
					});
					
					item_ports.getItems().add(portItem);

				}
			}
	    	
	    }
	    

		protected void startThread(String action){
			Thread thread = null;
			switch (action)
			{
				case "Conectar" :
					thread = new Thread(OpenCOMTask());
					break;
				case "Desconectar" :
					thread = new Thread(CloseCOMTask());
					break;
				case "ReporteZ" :
					thread = new Thread(PrintZReportTask());
					break;
				case "ReporteX" :
					thread = new Thread(PrintXReportTask());
					break;	
			
			}
			
			
			thread.setDaemon(true);
		    thread.start();
			
		}
		
		
	    
	    private Task<Boolean> OpenCOMTask(){
	    	
	    	Task<Boolean> tsk = new Task<Boolean>() {

				@Override
				protected Boolean call() throws Exception {
					if (fiscalPrinter.OpenFpctrl(activePort)){
		        		
		        		isPrinterConnected = true;
		    			Platform.runLater(new Runnable() {
							@Override
							public void run() {
								item_conn.setText("Desconectar");
								btn_printer.setTextFill(Color.web("#1bea25"));
								label_status.setText("Conectado al puerto " + activePort);
								btn_printer.setTextFill(Color.web("#1bea25"));
								
							}
						});
						return true;
						
					} else {
						isPrinterConnected = false;
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								item_conn.setText("Conectar");
								btn_printer.setTextFill(Color.web("#000000"));
								label_status.setText("Error al conectarse a la impresora");
							}
						});
						fiscalPrinter.CloseFpctrl();
					}
					return false;
				}
	    		
	    	};
	    	
	    	return tsk;
	    }

	    private Task<Void> CloseCOMTask(){
	    	return new Task<Void>(){
	    		 @Override public Void call() {
	    			 fiscalPrinter.CloseFpctrl();
	    			 Platform.runLater(new Runnable() {
						@Override
						public void run() {
							label_status.setText("Se desconectó la impresora");
							item_conn.setText("Conectar");
							btn_printer.setTextFill(Color.web("#000000"));
						}
					});
	    			 return null;
	    			 
	    		 }
	    		
	    	};
	    	
	    }
	    
	    private Task<Void> PrintZReportTask(){
	    	return new Task<Void>(){
	    		
	    		 @Override public Void call() throws Exception{
	    			 
	    			 final Db db = new Db();

	    			 Platform.runLater(new Runnable() {
						@Override
						public void run() {
							
			    			 if(db.testConnection()){
				    				if(isPrinterConnected){
				    					//if(Login.loggedUser.canPrintReportZ){
				    						try {
				    							fiscalPrinter.printZReport();
				    						} catch (PrinterException e) {
				    							MessageBox.show(homeStage, "Error", "Error al imprimir el reporte Z","Error al imprimir: " + e.toString(), MessageBox.typeError);
				    						}
				    					/*}else{
				    						MessageBox.show(homeStage, "Error", "Acceso no autorizado", " ", MessageBox.typeError);
				    					}*/
				    				}
				    				else{
			    						MessageBox.show(homeStage, "Error", "La impresora no está conectada", "Verifique la conexión e intente de nuevo.", MessageBox.typeError);

				    				}
				    			}
						}
					});
	    			 return null;
	    			 
	    		 }
	    		
	    	};
	    	
	    }
	    
	    private Task<Void> PrintXReportTask(){
	    	return new Task<Void>(){
	    		
	    		 @Override public Void call() throws Exception{
	    			 
	    			 final Db db = new Db();

	    			 Platform.runLater(new Runnable() {
						@Override
						public void run() {
							
			    			 if(db.testConnection()){
				    				if(isPrinterConnected){
				    					if(Login.loggedUser.canPrintReportX){
				    						try {
				    							fiscalPrinter.printXReport();
				    						} catch (PrinterException e) {
				    							MessageBox.show(homeStage, "Error", "Error al imprimir el reporte X","Error al imprimir: " + e.toString(), MessageBox.typeError);
				    						}
				    					}else{
				    						MessageBox.show(homeStage, "Error", "Acceso no autorizado", " ", MessageBox.typeError);
				    					}
				    				}
				    				else{
			    						MessageBox.show(homeStage, "Error", "La impresora no está conectada", "Verifique la conexión e intente de nuevo.", MessageBox.typeError);

				    				}
				    			}
						}
					});
	    			 return null;
	    			 
	    		 }
	    		
	    	};
	    	
	    }
	    
	  
}
