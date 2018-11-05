package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaEditaClienteController {
	
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
	
	private String txtTelAntigo = "";
	
	ClienteDAO c = new ClienteDAO();
	
	@FXML
	private void cancelar() {
		((Stage)btnCancelar.getScene().getWindow()).close();
	}
	
	public void setInfosIniciais(Cliente c) {
		
		txtTelefone.setText(c.getTelefone());
		txtNome.setText(c.getNome());
		txtEndereco.setText(c.getEndereco());
		txtComplemento.setText(c.getComplemento());
		txtBairro.setText(c.getBairro());
		txtReferencia.setText(c.getReferencia());
		txtObs.setText(c.getObservacoes());
		
		txtTelAntigo = c.getTelefone();
	}
	
	public void AtualizaCliente() {
		Cliente cli = new Cliente();
		cli.setTelefone(txtTelefone.getText());
		cli.setNome(txtNome.getText());
		cli.setEndereco(txtEndereco.getText());
		cli.setComplemento(txtComplemento.getText());
		cli.setBairro(txtBairro.getText());
		cli.setReferencia(txtReferencia.getText());
		cli.setObservacoes(txtObs.getText());
		
		c.atualizaClientePorTelefone(cli, txtTelAntigo);
	}

}
