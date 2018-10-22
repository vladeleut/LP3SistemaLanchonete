package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class TelaAddItensContoller {
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	private Button btnAddItem;
	
	@FXML
	private Label lblPreco;
	
	@FXML
	public void cancelar() {
		((Stage)btnCancelar.getScene().getWindow()).close();
	}
	
}
