package application;

import java.io.IOException;
import java.sql.SQLException;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
	
	public ObservableList<Ingrediente> ingredientes;
	public ObservableList<Ingrediente> ingredientesAtuais;
	
	IngredienteDAO idao = new IngredienteDAO();
	
	public void initialize() throws SQLException {
		
		///tabela de ingredientes disponíveis
		ingredientes = FXCollections.observableArrayList(idao.getLista());
		
		TableColumn<Ingrediente, String> colNomeIng = new TableColumn<>("Nome");
		colNomeIng.setMinWidth(40);
		colNomeIng.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Ingrediente, String> colPrecoIng = new TableColumn<>("Preço");
		colPrecoIng.setMinWidth(50);
		colPrecoIng.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		TableColumn<Ingrediente, String> colDescIng = new TableColumn<>("Descrição");
		colDescIng.setMinWidth(170);
		colDescIng.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		
		tblIngredientes.setItems(ingredientes);
		tblIngredientes.getColumns().addAll(colNomeIng, colPrecoIng, colDescIng);
		
		
		/////tabela de ingredientes atuais
		ingredientesAtuais = FXCollections.observableArrayList();
		
		TableColumn<Ingrediente, String> colNomeIngAt = new TableColumn<>("Nome");
		colNomeIngAt.setMinWidth(40);
		colNomeIngAt.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<Ingrediente, String> colPrecoIngAt = new TableColumn<>("Preço");
		colPrecoIngAt.setMinWidth(50);
		colPrecoIngAt.setCellValueFactory(new PropertyValueFactory<>("preco"));
		
		TableColumn<Ingrediente, String> colDescIngAt = new TableColumn<>("Descrição");
		colDescIngAt.setMinWidth(170);
		colDescIngAt.setCellValueFactory(new PropertyValueFactory<>("descricao"));
		///tem as mesmas colunas
		tblIngredsAtuais.setItems(ingredientesAtuais);
		tblIngredsAtuais.getColumns().addAll(colNomeIngAt, colPrecoIngAt, colDescIngAt);
		
	}
	
	@FXML
	public void addIngrediente(MouseEvent event) throws IOException {
		//para detalhes desse listener, ver TelaPrincipalController, aba Cliente
		
		if (event.getClickCount() == 2){ 
			
			Ingrediente i = new Ingrediente();
			i.setNome(tblIngredientes.getSelectionModel().getSelectedItem().getNome());
			i.setPreco(tblIngredientes.getSelectionModel().getSelectedItem().getPreco());
			i.setDescricao(tblIngredientes.getSelectionModel().getSelectedItem().getDescricao());
			
			ingredientesAtuais.add(i);
		}
	}
	
	@FXML
	public void rmIngrediente(MouseEvent event) throws IOException {
		//para detalhes desse listener, ver TelaPrincipalController, aba Cliente
		try {
		if (event.getClickCount() == 2){ 
			
			ObservableList<Ingrediente> SelectedIng = tblIngredsAtuais.getSelectionModel().getSelectedItems();
			
			/*
			Ingrediente i = new Ingrediente();
			i.setNome(tblIngredsAtuais.getSelectionModel().getSelectedItem().getNome());
			i.setPreco(tblIngredsAtuais.getSelectionModel().getSelectedItem().getPreco());
			i.setDescricao(tblIngredsAtuais.getSelectionModel().getSelectedItem().getDescricao());*/
			for(Ingrediente i : SelectedIng) {
				ingredientesAtuais.remove(i);
			}
			
		}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@FXML
	public void criaProduto() {
		ProdutoDAO pdao = new ProdutoDAO();
		Produto p = new Produto();
		p.setNome(txtNomeProd.getText());
		p.setPreco(Double.valueOf(txtPrecoProd.getText()));
		pdao.insereProduto(p);
		//rever necessidade de atributos de produto
		//edit: acho q precisa, só não aqui
		
		for(Ingrediente i : tblIngredsAtuais.getItems()) {
			pdao.relacionaProdIng(p.getNome(), i.getNome());
		}
		((Stage)btnCriarProd.getScene().getWindow()).close();
	}
	
}
