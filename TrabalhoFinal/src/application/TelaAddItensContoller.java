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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaAddItensContoller {
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	private Button btnAddItem;
	
	@FXML
	private Label lblPreco;
	
	@FXML
	private TextField txtObs;
	
	@FXML
	public void cancelar() {
		((Stage)btnCancelar.getScene().getWindow()).close();
	}
	
	@FXML
	private TableView<Produto> tblProdutos;
	
	@FXML
	private TableView<Ingrediente> tblIngredsProduto;
	
	@FXML
	private TableView<Ingrediente> tblAcrescimos;
	
	public ObservableList<Produto> listaProdutos;
	
	public ObservableList<Ingrediente> listaIngreds;
	
	public ObservableList<Ingrediente> acrescimos;
	
	ProdutoDAO pdao = new ProdutoDAO();
	
	IngredienteDAO idao = new IngredienteDAO();
	
	public void initialize(){
		
		//////////////////TABELA DE PRODUTOS
		listaProdutos = FXCollections.observableArrayList(pdao.getLista());

		TableColumn<Produto, String> colNomeProd = new TableColumn<>("Nome");
		colNomeProd.setMinWidth(120);
		colNomeProd.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Produto, String> colPrecoProd = new TableColumn<>("Preço");
		colPrecoProd.setMinWidth(65);
		colPrecoProd.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		tblProdutos.setItems(listaProdutos);
		tblProdutos.getColumns().addAll(colNomeProd, colPrecoProd);
		
		
		//////////Tabela de ingredientes do produto selecionado		
		TableColumn<Ingrediente, String> colNomeIng = new TableColumn<>("Ingrediente");
		colNomeIng.setMinWidth(120);
		colNomeIng.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		//TableColumn<Ingrediente, String> colSelected = new TableColumn<>("Ingrediente");
		//colNomeIng.setMinWidth(120);
		//colSelected.setCellValueFactory(CheckBoxTableCell<S, T>.forTableColumn(colSelected));
		
		tblIngredsProduto.getColumns().addAll(colNomeIng);
		tblIngredsProduto.setItems(listaIngreds);
		
		
		//////////tabela dos Acréscimos
		acrescimos = FXCollections.observableArrayList(idao.getLista());
		
		TableColumn<Ingrediente, String> colNomeAcres = new TableColumn<>("Nome");
		colNomeAcres.setMinWidth(60);
		colNomeAcres.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Ingrediente, String> colPrecoAcres = new TableColumn<>("Preço");
		colPrecoAcres.setMinWidth(40);
		colPrecoAcres.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		TableColumn<Ingrediente, String> colDescAcres = new TableColumn<>("Descrição");
		colDescAcres.setMinWidth(90);
		colDescAcres.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		
		tblAcrescimos.setItems(acrescimos);
		tblAcrescimos.getColumns().addAll(colNomeAcres, colPrecoAcres, colDescAcres);
		
	}
	
	@FXML
	public void preencheIngreds(MouseEvent event) throws IOException {
		
		String nome = "";
		if (event.getClickCount() == 2){ 
			System.out.println(tblProdutos.getSelectionModel().getSelectedItem().getNome());
			nome = tblProdutos.getSelectionModel().getSelectedItem().getNome();
			/*System.out.println(tableID.getSelectionModel().getSelectedItem().getBrewery());
	        System.out.println(tableID.getSelectionModel().getSelectedItem().getCountry());*/

			IngredienteDAO idao = new IngredienteDAO();
			listaIngreds = FXCollections.observableArrayList(idao.getListaPorProd(nome));
			
			tblIngredsProduto.setItems(listaIngreds);
		}
	}
	
	@FXML
	public void acrescentaItem(MouseEvent event) {
		if (event.getClickCount() == 2){ 
			System.out.println("cliquei");
		}
	}
	
	@FXML
	public void addItem() throws SQLException {
		PedidoDAO peddao = new PedidoDAO();
		ProdutoDAO pdao = new ProdutoDAO();
		try {
			int idProd = pdao.recuperaIdProd(tblProdutos.getSelectionModel().getSelectedItem().getNome());
			peddao.addProdutoNoPedidoNovo(idProd, txtObs.getText());
		}catch(Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "É preciso selecionar um produto na tabela", "Erro", JOptionPane.ERROR_MESSAGE);
		}
		((Stage)btnCancelar.getScene().getWindow()).close();
	}
	
}
