package application;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaTopClientesController {
	
	@FXML
	private TableView<TopCliente> tblTopClientes;
	
	public ObservableList<TopCliente> lista;
	
	TopClienteDAO tcdao = new TopClienteDAO();
	
	public void initialize() throws SQLException {
		lista = FXCollections.observableArrayList(tcdao.getLista());
		
		TableColumn<TopCliente, String> colNome = new TableColumn<>("Nome");
		colNome.setMinWidth(400);
		colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
		
		TableColumn<TopCliente, Double> colValor = new TableColumn<>("Valor Gasto");
		colValor.setMinWidth(172);
		colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
		
		tblTopClientes.setItems(lista);
		tblTopClientes.getColumns().addAll(colNome, colValor);
		
	}

}
