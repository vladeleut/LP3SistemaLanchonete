package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
	
	@FXML
	public void pagamento() throws IOException {//chama tela de pagamento 
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaPagamento.fxml"));
		Pane root = loader.load();
		
		
		TelaPagamentoController controller = (TelaPagamentoController)loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Forma de Pagamento");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner( lblPreco.getScene().getWindow() );
		stage.showAndWait();
		stage.close();
		//controller.setinfos
	}
	
	@FXML
	public void addItens() throws IOException {//chama tela de adicionar itens 
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaAddItens.fxml"));
		Pane root = loader.load();
		
		
		TelaAddItensContoller controller = (TelaAddItensContoller)loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Forma de Pagamento");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner( lblPreco.getScene().getWindow() );
		stage.showAndWait();
		stage.close();
		//controller.setinfos
	}

}
