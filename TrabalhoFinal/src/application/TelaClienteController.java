package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaClienteController {
	
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
	private Button btnCancelar;
	
	@FXML
	private void gravar() {
		Cliente cli = new Cliente();
		cli.setTelefone(txtTelefone.getText());
		cli.setNome(txtNome.getText());
		cli.setEndereco(txtEndereco.getText());
		cli.setComplemento(txtComplemento.getText());
		cli.setBairro(txtBairro.getText());
		cli.setReferencia(txtReferencia.getText());
		cli.setObservacoes(txtObs.getText());
		
		ClienteDAO c = new ClienteDAO();
		c.insereCliente(cli);
		//confirmar?
		
		
		((Stage)btnCancelar.getScene().getWindow()).close();
	}
	
	@FXML
	private void cancelar() {
		((Stage)btnCancelar.getScene().getWindow()).close();
	}

}
