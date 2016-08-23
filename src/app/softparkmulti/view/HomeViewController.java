package app.softparkmulti.view;

import app.softparkmulti.util.CommPortUtils;
import app.softparkmulti.util.MessageBox;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import tfhka.ve.Tfhka;

public class HomeViewController {

	@FXML
	private Menu item_ports;
	@FXML
	private MenuItem item_conn;
	@FXML
	private Label label_status;
	@FXML
	private MenuButton btn_printer;
	
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
			
			MessageBox.show(homeStage, "Cobro", "Testing", " ", MessageBox.typeInformation);
		}
		
		@FXML
		private void handleTicket_Manual(){
			
			MessageBox.show(homeStage, "Ticket manual", "Testing", " ", MessageBox.typeInformation);
		}
		
		@FXML
		private void handleTicket_Lost(){
			MessageBox.show(homeStage, "Ticket perdido", "Testing", " ", MessageBox.typeInformation);
			
		}
		
		@FXML
		private void handlePrinter(){
			
		}
		
		@FXML
		private void handlePrinterConn(){
			
			Task<Boolean> oTask = OpenCOMTask();
			

			oTask.setOnFailed(a ->{
				label_status.setText("Failed");
				btn_printer.setTextFill(Color.web("#F50C0C")); 
				homeStage.show();
			});
			
			if (!activePort.isEmpty() && !activePort.equals(null)) {
				Thread thread = new Thread(oTask);
			    //thread.setDaemon(true);
			    thread.start();
			} else {
				label_status.setText("No hay puerto COM activo");
			}
		

		}
		
		@FXML
		private void handlePrinterPort(){
			//MessageBox.show(homeStage, "Printer port", "Testing", " ", MessageBox.typeInformation);
			
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

						}
						
					});
					
					item_ports.getItems().add(portItem);

				}
			}
	    	
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
						}
					});
	    			 return null;
	    			 
	    		 }
	    		
	    	};
	    	
	    }
	    
}
