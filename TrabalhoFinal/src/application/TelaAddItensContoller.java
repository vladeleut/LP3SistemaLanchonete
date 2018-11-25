package application;

import java.io.IOException;

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
	public void cancelar() {
		((Stage)btnCancelar.getScene().getWindow()).close();
	}
	
	@FXML
	private TableView<Produto> tblProdutos;
	
	@FXML
	private TableView<Ingrediente> tblIngredsProduto;
	
	public ObservableList<Produto> listaProdutos;
	
	public ObservableList<Ingrediente> listaIngreds;
	
	ProdutoDAO pdao = new ProdutoDAO();
	
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
		
		
		
		
		TableColumn<Ingrediente, String> colNomeIng = new TableColumn<>("Ingrediente");
		colNomeIng.setMinWidth(120);
		colNomeIng.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		tblIngredsProduto.getColumns().addAll(colNomeIng);
		tblIngredsProduto.setItems(listaIngreds);
		
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
	
	
}
