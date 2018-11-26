package application;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	private Button btnProcuraCliente;
	
	@FXML
	private CheckBox cbxUsaUmaVez;
	
	@FXML
	private CheckBox cbxBalcao;
	
	@FXML
	private Button btnInserirItem;
	
	@FXML
	private Label lblPreco;
	
	@FXML
	private Label lblNroPedido;
	
	@FXML
	private TableView<Item> tblItensPedido;
	
	@FXML
	public void pagamento(int NroPedido) throws IOException {//chama tela de pagamento 
		
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
		stage.setTitle("Selecionar e adicionar um item");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner( lblPreco.getScene().getWindow() );
		stage.showAndWait();
		atualizaTblItens();
		stage.close();
		//controller.setinfos
	}
	
	public void completaDados() {
		
		ClienteDAO clidao = new ClienteDAO();
		Cliente cli = new Cliente();
		cli = clidao.procuraPorTelefone(txtTelefone.getText());
		
		if(cli != null) {
			txtNome.setText(cli.getNome());
			txtEndereco.setText(cli.getEndereco());
			txtComplemento.setText(cli.getComplemento());
			txtBairro.setText(cli.getBairro());
			txtReferencia.setText(cli.getReferencia());
			txtObs.setText(cli.getObservacoes());
		}else {
			JOptionPane.showMessageDialog(null, "Não consegui achar", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	public void selecionaCliente() throws SQLException, IOException {
		btnInserirItem.setDisable(false);
		btnFinalizar.setDisable(false);
		Pedido pedido = new Pedido();
		Cliente c = getDadosCliente();
		PedidoDAO pdao = new PedidoDAO();
		pedido = pdao.criaPedido(c);
		lblNroPedido.setText(String.valueOf(pedido.getNumero()));
		
	}
	
	public Cliente getDadosCliente() {
		Cliente c = new Cliente();
		c.setBairro(txtBairro.getText());
		c.setComplemento(txtComplemento.getText());
		c.setEndereco(txtEndereco.getText());
		c.setNome(txtNome.getText());
		c.setObservacoes(txtObs.getText());
		c.setReferencia(txtReferencia.getText());
		c.setTelefone(txtTelefone.getText());
		
		return c;
	}
	
	public void finalizaPedido() throws NumberFormatException, IOException {
		////PRECISA DE MAIS FUNÇ~/OES
		PedidoDAO pdao = new PedidoDAO();
		pdao.atualizaStatus(2, Integer.valueOf(lblNroPedido.getText()));
		pagamento(Integer.valueOf(lblNroPedido.getText()));
	}
	
	@FXML
	private TableView<Item> tblItens;
	
	public ObservableList<Item> itens;
	
	ItemDAO itdao = new ItemDAO();
	
	public void initialize() {
		try {
		//itens = FXCollections.observableArrayList(itdao.getLista());
		
		TableColumn<Item, String> colNomeItem = new TableColumn<>("Nome");
		colNomeItem.setMinWidth(40);
		colNomeItem.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Item, Double> colPrecoItem = new TableColumn<>("Preço");
		colPrecoItem.setMinWidth(50);
		colPrecoItem.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		TableColumn<Item, String> colObsItem = new TableColumn<>("Observações");
		colObsItem.setMinWidth(170);
		colObsItem.setCellValueFactory(new PropertyValueFactory<>("obs"));
		
		//tblItens.setItems(itens);
		tblItensPedido.getColumns().addAll(colNomeItem, colPrecoItem, colObsItem);
		}catch(Exception e) {
			System.out.println("Erro feio");
		}
	}
	
	public void atualizaTblItens() {
		itens = FXCollections.observableArrayList(itdao.getLista());
		tblItensPedido.setItems(itens);
	}

}
