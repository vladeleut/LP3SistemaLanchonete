package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TelaPagamentoController {
	
	@FXML 
	private Button btnFinalizar;
	
	public void finalizar() {
		((Stage)btnFinalizar.getScene().getWindow()).close();
	}
	
}
