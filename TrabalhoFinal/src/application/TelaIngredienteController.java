package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TelaIngredienteController {
	
	@FXML
	private TextField txtNome;
	
	@FXML
	private TextField txtPreco;

	@FXML
	private TextField txtDescricao;
	
	@FXML
	private Button btnInsereIngrediente;
	
	public void insereIngrediente() {
		Ingrediente ing = new Ingrediente();
		ing.setNome(txtNome.getText());
		ing.setPreco(txtPreco.getText());
		ing.setDescricao(txtDescricao.getText());
		
		IngredienteDAO ingdao = new IngredienteDAO();
		ingdao.insere(ing);
		
		//((Stage)btnInsereIngrediente.getScene().getWindow()).close();
		
		txtNome.setText("");
		txtPreco.setText("");
		txtDescricao.setText("");;
		
	}
}
