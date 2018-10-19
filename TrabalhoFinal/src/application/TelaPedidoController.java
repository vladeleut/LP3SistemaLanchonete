package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class TelaPedidoController {
	
	@FXML
	private TextField txtTelefone;
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtEndereco;
	
	@FXML
	private TextField txtComplemento;
	
	@FXML
	private TextField txtBairro;
	
	@FXML
	private TextField txtReferencia;
	
	@FXML
	private TextField txtObs;
	
	@FXML
	private Button btnSalvar;
	
	@FXML
	private Button btnFinalizar;
	
	@FXML
	private Button btnSelecionar;
	
	@FXML
	private CheckBox cbxUsaUmaVez;
	
	@FXML
	private CheckBox cbxBalcao;
	
	@FXML
	private Button btnInserirItem;
	
	@FXML
	private Label lblPreco;
	
	@FXML
	private TableView<Item> tblItensPedido;
}
