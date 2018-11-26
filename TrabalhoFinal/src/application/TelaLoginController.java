package application;

import java.awt.HeadlessException;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
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
	public void fazerLogin() throws IOException, HeadlessException, SQLException {
		UsuarioDAO u = new UsuarioDAO();
		
		//txtLogin.setText("vlad");
		//txtSenha.setText("root");
		
		if(u.fazLogin(txtLogin.getText(), txtSenha.getText())) {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaPrincipal.fxml"));
			Pane root = loader.load();
			
			TelaPrincipalController controller = (TelaPrincipalController)loader.getController();
			//controller.initialize();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle("Principal");
			stage.show();
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icone.png")));
			//stage.setMaximized(true);
			stage.setResizable(false);
			
			Usuario logado = u.usuarioPorLogin(txtLogin.getText());

			controller.setInfosIniciais(logado);
			
			//slide 40 aula 17 exemplo
			((Stage)btnEntrar.getScene().getWindow()).close();
		}else {
			System.out.println("Não deu Certo");
		}
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
					JOptionPane.showMessageDialog(null, "Senha Inválida", "Erro", JOptionPane.ERROR_MESSAGE);
					break;
				}
			}
		}
		
		if(!loginValido) {
			JOptionPane.showMessageDialog(null, "Usuário Não encontrado!", "Erro", JOptionPane.ERROR_MESSAGE);
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
			stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icone.png")));
			//stage.setMaximized(true);
			stage.setResizable(false);
			
			controller.setInfosIniciais(existente);
			
			//slide 40 aula 17 exemplo
			((Stage)btnEntrar.getScene().getWindow()).close();
			
		}
		
	}

}
