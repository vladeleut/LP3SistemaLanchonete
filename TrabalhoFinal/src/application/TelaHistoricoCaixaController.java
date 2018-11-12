package application;

import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TelaHistoricoCaixaController {
	
	@FXML
	private TableView<Caixa> tblHistCaixa;
	
	public ObservableList<Caixa> histCaixa;
	
	CaixaDAO cxdao = new CaixaDAO();
	
	public void initialize() throws SQLException {
		histCaixa = FXCollections.observableArrayList(cxdao.getMovimentacao());
		
		TableColumn<Caixa, String> colIdSessao = new TableColumn<>("Nro");
		colIdSessao.setMinWidth(40);
		colIdSessao.setCellValueFactory(new PropertyValueFactory<>("id_sessao"));
		
		TableColumn<Caixa, String> colNomeUsuario = new TableColumn<>("Nome");
		colNomeUsuario.setMinWidth(50);
		colNomeUsuario.setCellValueFactory(new PropertyValueFactory<>("nomeUsuario"));
		
		TableColumn<Caixa, String> colDataAbertura = new TableColumn<>("Abertura");
		colDataAbertura.setMinWidth(170);
		colDataAbertura.setCellValueFactory(new PropertyValueFactory<>("dataAbertura"));

		TableColumn<Caixa, String> colDataFechamento = new TableColumn<>("Fechamento");
		colDataFechamento.setMinWidth(170);
		colDataFechamento.setCellValueFactory(new PropertyValueFactory<>("dataFechamento"));
		
		TableColumn<Caixa, String> colMovto = new TableColumn<>("Movimento");
		colMovto.setMinWidth(100);
		colMovto.setCellValueFactory(new PropertyValueFactory<>("movimento"));
		
		tblHistCaixa.setItems(histCaixa);
		tblHistCaixa.getColumns().addAll(colIdSessao, colNomeUsuario, colDataAbertura,colDataFechamento, colMovto);
		
	}

}
