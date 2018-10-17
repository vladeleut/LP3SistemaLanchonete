package application;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaLoginController {
	
	@FXML
	private TextField txtLogin;
	
	@FXML
	private PasswordField txtSenha;
	
	@FXML
	private Button btnEntrar;
	
	@FXML
	private Button btnCancelar;
	
	@FXML
	public void fechar() {
		System.exit(0);
	}
	
	@FXML
	public void entrar() throws SQLException, IOException {
		UsuarioDAO u = new UsuarioDAO();
		Boolean loginValido = false;
		String msg = "";
		
		Usuario logando = new Usuario();
		/*
		logando.setLogin(txtLogin.getText());
		logando.setSenha(txtSenha.getText());
		*/
		logando.setLogin("vlad");
		logando.setSenha("root");
		Usuario existente = new Usuario();
		List<Usuario> listaUsuarios = u.getLista();
		
		for(Usuario user : listaUsuarios) {
			if(user.getLogin().equals(logando.getLogin())) {
				existente.setLogin(user.getLogin());
				existente.setNome(user.getNome());
				existente.setSenha(user.getSenha());
				existente.setFlagAdmin(user.getFlagAdmin());
				
				if((existente.getSenha()).equals(logando.getSenha())) {
					msg = "Entrou";
					
					if(existente.getFlagAdmin() == 1) {
						msg += " como Administrador";
					}else {
						msg += "!";
					}
					//DESCOMENTAR ISSO!! SEPRECISAR
					//JOptionPane.showMessageDialog(null, msg, "Sucesso", JOptionPane.INFORMATION_MESSAGE);
					loginValido = true;
					break;
				}else {
					JOptionPane.showMessageDialog(null, "Senha Inv�lida", "Erro", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
		}
		
		if(!loginValido) {
			JOptionPane.showMessageDialog(null, "Usu�rio N�o encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
		}else {

		
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaPrincipal.fxml"));
			Pane root = loader.load();
			
			TelaPrincipalController controller = (TelaPrincipalController)loader.getController();
			//controller.initialize();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Principal");
			stage.show();
			//stage.setMaximized(true);
			
			controller.setInfosIniciais(existente);
			
			//slide 40 aula 17 exemplo
			((Stage)btnEntrar.getScene().getWindow()).close();
			
		}
		
	}
	
	

}