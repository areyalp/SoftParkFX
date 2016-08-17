package app.softparkmulti.view;

import javax.swing.JMenuItem;

import app.softparkmulti.util.CommPortUtils;
import app.softparkmulti.util.MessageBox;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

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
	
	public HomeViewController() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 *This method is automatically called after the fxml file has been loaded.
     */
		@FXML
		private void initialize(){
			
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
					item_ports.getItems().add(new MenuItem(port));

					/*subMenuItem.setActionCommand(port);
					subMenuItem.addActionListener(lForMenuItem);
					subMenu2.add(subMenuItem);*/
				}
			}
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
			
		}
		@FXML
		private void handlePrinterPort(){
			MessageBox.show(homeStage, "Printer port", "Testing", " ", MessageBox.typeInformation);
			
		}
		

	    public void setDialogStage(Stage dialogStage) {
	        this.homeStage = dialogStage;
	    }
		
}
