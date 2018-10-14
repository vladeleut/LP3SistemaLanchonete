package application;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TelaPrincipalController {
	@FXML
	private Label lblNomeUsuario;
	
	@FXML
	private Label lblTsLogin;
	
	public void setInfosIniciais(Usuario user) {
		
		DateTimeFormatter ts = DateTimeFormatter.ofPattern("dd/mm/yyyy HH:mm");
		LocalDateTime now = LocalDateTime.now();
		
		
		if(user.getFlagAdmin() == 1) {
			lblNomeUsuario.setText(lblNomeUsuario.getText() + "(Admin)" + user.getNome().toString());
		}else {
			lblNomeUsuario.setText(lblNomeUsuario.getText() + user.getNome().toString());
		}
		 
		lblTsLogin.setText(lblTsLogin.getText() + ts.format(now) );
	}
	
	
}
