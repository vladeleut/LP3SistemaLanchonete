package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TelaProdutoController {
	@FXML
	private TextField txtNomeProd;
	
	@FXML
	private TextField txtPrecoProd;
	
	@FXML
	private Label lblPrecoSugeridoProd;
	
	@FXML
	private Button btnCriarProd;
	
	@FXML
	private TableView<Ingrediente> tblIngredientes;
	
	@FXML
	private TableView<Ingrediente> tblIngredsAtuais;
	
	
	
}
