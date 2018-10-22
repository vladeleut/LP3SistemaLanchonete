package application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TelaPrincipalController {
	@FXML
	private Label lblNomeUsuario;
	
	@FXML
	private Label lblTsLogin;
	
	public void setInfosIniciais(Usuario user) {
		
		DateTimeFormatter ts = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm");
		LocalDateTime now = LocalDateTime.now();
		
		
		if(user.getFlagAdmin() == 1) {
			lblNomeUsuario.setText(lblNomeUsuario.getText() + "(Admin)" + user.getNome().toString());
		}else {
			lblNomeUsuario.setText(lblNomeUsuario.getText() + user.getNome().toString());
		}
		 
		lblTsLogin.setText(lblTsLogin.getText() + ts.format(now) );
	}
	
	////////////////////////////////////////////////////////
	/////////////////// ABA PEDIDOS ////////////////////////
	////////////////////////////////////////////////////////

	@FXML
	private TableView<Pedido> tblCaixa;

	@FXML
	private Button btnAbrirCaixa;

	@FXML
	private Button btnFecharCaixa;
	
	@FXML
	private Button btnDiaEquivalente;
	
	
		
	////////////////////////////////////////////////////////
	/////////////////// ABA CLIENTE ////////////////////////
	////////////////////////////////////////////////////////
	@FXML
	private TableView<Cliente> tblClientes;
	
	@FXML
	private TextField txtPesquisaCliente;
	
	@FXML
	private Button btnNovoCliente;
	
	@FXML
	private Button btnAtualizaTbl;
	
	@FXML
	private Label lblPesquisa;
	
	public void initialize() throws SQLException {
		
		ClienteDAO c = new ClienteDAO();
		
		ObservableList<Cliente> clientes = FXCollections.observableArrayList(c.getLista());
		
		//NÃO PODE FAZER CAST ASSIM
		//clientes = (ObservableList<Cliente>) c.getLista();
		
		TableColumn<Cliente, String> colNomeCli = new TableColumn<>("Nome");
		colNomeCli.setMinWidth(50);
		colNomeCli.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Cliente, String> colEndCli = new TableColumn<>("Endereço");
		colEndCli.setMinWidth(100);
		colEndCli.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		
		TableColumn<Cliente, String> colTelCli = new TableColumn<>("Telefone");
		colTelCli.setMinWidth(50);
		colTelCli.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		
		tblClientes.setItems(clientes);
		tblClientes.getColumns().addAll(colNomeCli, colEndCli, colTelCli);
		
		
	}
	
	@FXML
	public void atualizaTblCliente() {
		tblClientes.refresh();
	}
	
	@FXML
	public void chamaNovoCliente() throws IOException {//chama janela para ccriar cliente
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaCliente.fxml"));
		Pane root = loader.load();
		
		TelaClienteController controller = (TelaClienteController)loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Criar novo cliente");
		//stage.initModality(Modality.WINDOW_MODAL);
		//stage.initOwner( btnNovoCliente.getScene().getWindow() );
		stage.showAndWait();
		
	}
	/*
	@FXML
	public void chamaCliente() throws IOException {//chama janela para ccriar cliente
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaCliente.fxml"));
		Pane root = loader.load();
		
		TelaClienteController controller = (TelaClienteController)loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		
		
		Cliente c = new Cliente();
		controller.setInfosIniciais(c, 1);
		
		stage.setScene(scene);
		stage.setTitle("Editar cliente");
		stage.show();
		stage.setAlwaysOnTop(true);
		
	}
	*/
	
	
////////////////////////////////////////////////////////
/////////////////// ABA PEDIDOS ////////////////////////
////////////////////////////////////////////////////////
	
	@FXML
	private TableView<Pedido> tblPedidos;
	
	@FXML
	private Button btnNovoPedido;
	
	@FXML
	private TextField txtPesquisaPedido;
	
	@FXML
	public void NovoPedido() throws IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaPedido.fxml"));
		Pane root = loader.load();
		
		TelaPedidoController controller = (TelaPedidoController)loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Novo cliente");
		stage.show();
		
		
	}
	
	
////////////////////////////////////////////////////////
/////////////////// ABA PRODUTOS////////////////////////
////////////////////////////////////////////////////////

	@FXML
	private TableView<Produto> tblProdutos;
	
	@FXML
	private Button btnNovoProduto;
	
	@FXML
	private TextField txtPesquisaProduto;
	
	@FXML
	public void NovoProduto() throws IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaProduto.fxml"));
		Pane root = loader.load();
		
		TelaProdutoController controller = (TelaProdutoController)loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Novo cliente");
		stage.show();
		
		
	}
	
}
