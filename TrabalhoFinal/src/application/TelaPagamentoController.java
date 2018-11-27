package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaPagamentoController {
	
	double valor;
	
	@FXML 
	private Button btnFinalizar;
	
	@FXML
	private TextField txtFormaPagto;
	
	//@FXML
	//private ChoiceBox cb;
	
	@FXML
	private Label lblValor;
	
	public void finalizar() {
		PedidoDAO pddao = new PedidoDAO();
		//double valor = Double.valueOf(lblValor.getText());
		try {
		pddao.registraPagto(valor, txtFormaPagto.getText());
		}catch(Exception e) {
			System.out.println(e.getMessage() + "Erro muito feio");
		
		}
		((Stage)btnFinalizar.getScene().getWindow()).close();
	}
	
	public void setInfosIniciais(double valor) {
		this.valor = valor;
		String a = "" + valor;
		lblValor.setText(a);
	}
	
}
