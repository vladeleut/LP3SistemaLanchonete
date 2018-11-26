package application;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class TelaPrincipalController {
	
	ClienteDAO c = new ClienteDAO();
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private Label lblNomeUsuario;
	
	@FXML
	private Label lblTsLogin;
	
	Usuario logado = new Usuario();
	
	public void setInfosIniciais(Usuario user) {
		
		logado.setLogin(user.getLogin());
		logado.setNome(user.getNome());
		logado.setFlagAdmin(user.getFlagAdmin());
		
		DateTimeFormatter ts = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		LocalDateTime now = LocalDateTime.now();
		
		
		if(user.getFlagAdmin() == 1) {
			lblNomeUsuario.setText(lblNomeUsuario.getText() + "(Admin) " + user.getNome().toString());
		}else {
			lblNomeUsuario.setText(lblNomeUsuario.getText() + user.getNome().toString());
		}
		 
		lblTsLogin.setText(lblTsLogin.getText() + ts.format(now) );
	}
	
	////////////////////////////////////////////////////////
	/////////////////// ABA CAIXA// ////////////////////////
	////////////////////////////////////////////////////////

	@FXML
	private TableView<Pedido> tblCaixa;

	@FXML
	private Button btnAbrirCaixa;

	@FXML
	private Button btnFecharCaixa;
	
	@FXML
	private Button btnDiaEquivalente;
	
	@FXML
	private Button btnHistorico;
	
	public void abreCaixa() throws SQLException {
		CaixaDAO cxdao = new CaixaDAO();
		cxdao.abreCaixa(logado);
		JOptionPane.showMessageDialog(null, "Caixa Aberto com Sucesso", "Sucesso", JOptionPane.OK_OPTION);
	}
	
	public void fechaCaixa() throws SQLException {
		CaixaDAO cxdao = new CaixaDAO();
		cxdao.fechaCaixa();
		JOptionPane.showMessageDialog(null, "Caixa Fechado com Sucesso", "Sucesso", JOptionPane.OK_OPTION);
	}
	
	
	
	
		
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
	
	
	public ObservableList<Cliente> clientes;//precisei quebrar para utilizar fora do escopo de initialize
	
	public void initialize() throws SQLException {
		
		clientes = FXCollections.observableArrayList(c.getLista()); // quebrado aqui
		for(Cliente c : clientes) {
			
		}
		
		//NÃO PODE FAZER CAST ASSIM
		//clientes = (ObservableList<Cliente>) c.getLista();
		
		TableColumn<Cliente, String> colTelCli = new TableColumn<>("Telefone");
		colTelCli.setMinWidth(50);
		colTelCli.setCellValueFactory(new PropertyValueFactory<>("telefone"));
		
		TableColumn<Cliente, String> colNomeCli = new TableColumn<>("Nome");
		colNomeCli.setMinWidth(50);
		colNomeCli.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Cliente, String> colEndCli = new TableColumn<>("Endereço");
		colEndCli.setMinWidth(100);
		colEndCli.setCellValueFactory(new PropertyValueFactory<>("endereco"));
		
		TableColumn<Cliente, String> colComplCli = new TableColumn<>("Complemento");
		colComplCli.setMinWidth(100);
		colComplCli.setCellValueFactory(new PropertyValueFactory<>("complemento"));
		
		TableColumn<Cliente, String> colBairroCli = new TableColumn<>("Bairro");
		colBairroCli.setMinWidth(100);
		colBairroCli.setCellValueFactory(new PropertyValueFactory<>("bairro"));
		
		TableColumn<Cliente, String> colRefCli = new TableColumn<>("Referencia");
		colRefCli.setMinWidth(100);
		colRefCli.setCellValueFactory(new PropertyValueFactory<>("referencia"));
		
		TableColumn<Cliente, String> colObsCli = new TableColumn<>("Observações");
		colObsCli.setMinWidth(100);
		colObsCli.setCellValueFactory(new PropertyValueFactory<>("observacoes"));        
        
		/////////////COLOCANDO O BOTÃO DE DELETAR

		TableColumn deleteCol = new TableColumn("");
		deleteCol.setCellValueFactory(new PropertyValueFactory<>("bairro"));

		Callback<TableColumn<Cliente, String> , TableCell<Cliente, String>> cellFactory =  new Callback<TableColumn<Cliente, String> , TableCell<Cliente, String>>() {
			@Override
			public TableCell call(final TableColumn<Cliente, String> param) {
				final TableCell<Cliente, String> cell = new TableCell<Cliente, String>() {

					

					File imageFile = new File("C:/Users/eleut/OneDrive/ADS 2017/2018-2/LP3/Codigos/TrabalhoFinal/src/images/TrashRszd.png");
					Image trashImage = new Image(imageFile.toURI().toString());
					final Button btn = new Button("", new ImageView(trashImage));
					

					@Override
					public void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							btn.setOnAction(event -> {
								ClienteDAO cdao = new ClienteDAO();
								Cliente person = getTableView().getItems().get(getIndex());
								cdao.apagaPorTelefone(person.getTelefone());
								try {
									atualizaTblCliente();
								} catch (SQLException e) {
									System.out.println(e.getMessage());
								}
							});
							setGraphic(btn);
							setText(null);
						}
					}
				};
				return cell;
			}
		};
		
		deleteCol.setCellFactory(cellFactory);
		/////////////FIM DA COLUNA DE BOTÕES, SÓ ADICIONA ELE ABAIXO.
		
		tblClientes.setItems(clientes);
		tblClientes.getColumns().addAll(deleteCol, colTelCli, colNomeCli, colEndCli,colComplCli, colBairroCli, colRefCli, colObsCli);
		
		//////////////////TABELA DE PRODUTOS
		ProdutoDAO pdao = new ProdutoDAO();
		listaProdutos = FXCollections.observableArrayList(pdao.getLista());
		
		TableColumn<Produto, String> colNomeProd = new TableColumn<>("Nome");
		colNomeProd.setMinWidth(50);
		colNomeProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Produto, String> colPrecoProd = new TableColumn<>("Preço");
		colPrecoProd.setMinWidth(50);
		colPrecoProd.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		TableColumn<Produto, String> colIngsProd = new TableColumn<>("Ingredientes");
		colIngsProd.setMinWidth(50);
		colIngsProd.setCellValueFactory(new PropertyValueFactory<>("ingredientes"));
		
		tblProdutos.setItems(listaProdutos);
		tblProdutos.getColumns().addAll(colNomeProd, colPrecoProd, colIngsProd);
		
		
		//////////////////TABELA DE PEDIDOS
		listaPedidos = FXCollections.observableArrayList(peddao.getLista());

		TableColumn<Pedido, String> colNroPed = new TableColumn<>("Número");
		colNroPed.setPrefWidth(35);
		colNroPed.setCellValueFactory(new PropertyValueFactory<>("numero"));
		TableColumn<Pedido, String> colTelCliPed = new TableColumn<>("Telefone");
		colTelCliPed.setMinWidth(50);
		colTelCliPed.setCellValueFactory(new PropertyValueFactory<>("tel_cli"));
		TableColumn<Pedido, String> colNomeCliPed = new TableColumn<>("Cliente");
		colNomeCliPed.setMinWidth(50);
		colNomeCliPed.setCellValueFactory(new PropertyValueFactory<>("nome_cli"));
		TableColumn<Pedido, String> colSitPed = new TableColumn<>("Situação");
		colSitPed.setMinWidth(50);
		colSitPed.setCellValueFactory(new PropertyValueFactory<>("situacao"));
		TableColumn<Pedido, String> coltsAberturaPed = new TableColumn<>("Abertura");
		coltsAberturaPed.setMinWidth(50);
		coltsAberturaPed.setCellValueFactory(new PropertyValueFactory<>("tsAbertura"));
		//colNomeProd.setMinWidth(50);


		tblPedidos.setItems(listaPedidos);
		tblPedidos.getColumns().addAll(colNroPed, colNomeCliPed, colTelCliPed, coltsAberturaPed, colSitPed);
		
	}
	
	@FXML
	public void atualizaTblCliente() throws SQLException {
		clientes = FXCollections.observableArrayList(c.getLista());
		tblClientes.setItems(clientes);
	}
	
	@FXML
	public void atualizaTblPedidos() throws SQLException {
		listaPedidos = FXCollections.observableArrayList(peddao.getLista());
		tblPedidos.setItems(listaPedidos);
	}
	
	
	
	@FXML
	public void chamaNovoCliente() throws IOException, SQLException {//chama janela para ccriar cliente
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaCliente.fxml"));
		Pane root = loader.load();
		
		TelaClienteController controller = (TelaClienteController)loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Criar novo cliente");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner( lblPesquisa.getScene().getWindow() );
		stage.showAndWait();
		atualizaTblCliente();
	}
	/*
	@FXML
	public void clickItem()
	{
	    if (event.getClickCount() == 2) //Checking double click
	    {
	        System.out.println(tblClientes.getSelectionModel().getSelectedItem().getTelefone());
	        System.out.println(event.getClickCount());
	        /*System.out.println(tableID.getSelectionModel().getSelectedItem().getBrewery());
	        System.out.println(tableID.getSelectionModel().getSelectedItem().getCountry());
	    }
	}*/
	
	@FXML
	public void editarCliente() {
		
	}
	
	@FXML
	public void chamaCliente(MouseEvent event) throws IOException, SQLException {//chama janela para ccriar cliente

		String telefone = "";

		if (event.getClickCount() == 2){ 
			System.out.println(tblClientes.getSelectionModel().getSelectedItem().getTelefone());
			telefone = tblClientes.getSelectionModel().getSelectedItem().getTelefone();
			/*System.out.println(tableID.getSelectionModel().getSelectedItem().getBrewery());
	        System.out.println(tableID.getSelectionModel().getSelectedItem().getCountry());*/


			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaEditaCliente.fxml"));
			Pane root = loader.load();

			TelaEditaClienteController controller = (TelaEditaClienteController)loader.getController();
			Scene scene = new Scene(root);
			Stage stage = new Stage();


			Cliente c = new Cliente();
			ClienteDAO cdao = new ClienteDAO();

			c = cdao.procuraPorTelefone(telefone);
			controller.setInfosIniciais(c);

			stage.setScene(scene);
			stage.setTitle("Editar cliente");
			stage.showAndWait();
			atualizaTblCliente();
			stage.setAlwaysOnTop(true);
		}
	}
	
	public void pesquisa() {
		/*
		try {
		ObservableList<Cliente> cli = clientes;
		
		for(Cliente c : clientes) {
			if(c.getNome().contains(txtPesquisaCliente.getText())) {
				cli.add(c);
			}
		}
		clientes = cli;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}*/ //PESQUISARRRRRRRRRRRRR
	}
	
	
	
////////////////////////////////////////////////////////
/////////////////// ABA PEDIDOS ////////////////////////
////////////////////////////////////////////////////////
	
	@FXML
	private TableView<Pedido> tblPedidos;
	
	@FXML
	private Button btnNovoPedido;
	
	@FXML
	private TextField txtPesquisaPedido;
	
	public ObservableList<Pedido> listaPedidos;
	
	PedidoDAO peddao = new PedidoDAO();
	
	@FXML
	public void NovoPedido() throws IOException, SQLException{
		PedidoDAO pdao = new PedidoDAO();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaPedido.fxml"));
		Pane root = loader.load();
		
		TelaPedidoController controller = (TelaPedidoController)loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Novo Pedido");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner( btnNovoPedido.getScene().getWindow() );
		stage.showAndWait();
		pdao.evitaErros();
		atualizaTblPedidos();
	}
	
	@FXML
	public void avancaStatus(MouseEvent event) throws SQLException {
		if (event.getClickCount() == 2){ 
			
			int opt = JOptionPane.showConfirmDialog(null, "Deseja atualizar o status?", "Atenção", JOptionPane.YES_NO_OPTION);
			if(opt == 0) {
				int nroPedido = tblPedidos.getSelectionModel().getSelectedItem().getNumero();
				peddao.avancaStatus(nroPedido);
				atualizaTblPedidos();
			}
		}
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
	
	public ObservableList<Produto> listaProdutos;
	
	@FXML
	public void NovoProduto() throws IOException{
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaProduto.fxml"));
		Pane root = loader.load();
		
		TelaProdutoController controller = (TelaProdutoController)loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Novo Produto");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner( btnNovoProduto.getScene().getWindow() );
		stage.showAndWait();
		
	}
	


////////////////////////////////////////////////////////
/////////////////// ABA CONFIGS ////////////////////////
////////////////////////////////////////////////////////

	@FXML
	private Button btnIngredientes;
	
	public void chamaNovoIngrediente() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaIngrediente.fxml"));
		Pane root = loader.load();

		TelaIngredienteController controller = (TelaIngredienteController)loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();

		stage.setScene(scene);
		stage.setTitle("Novo Ingrediente");
		stage.show();
		stage.setAlwaysOnTop(true);
		
	}




////////////////////////////////////////////////////////
/////////////////// ABA RELATÓRIOS//////////////////////
////////////////////////////////////////////////////////



	public void consultaHistorico() throws IOException {

		FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaHistoricoCaixa.fxml"));
		Pane root = loader.load();

		TelaHistoricoCaixaController controller = (TelaHistoricoCaixaController)loader.getController();
		Scene scene = new Scene(root);
		Stage stage = new Stage();

		stage.setScene(scene);
		stage.setTitle("Histórico de movimentações do Caixa");
		stage.show();
		stage.setAlwaysOnTop(true);
		
	}






}