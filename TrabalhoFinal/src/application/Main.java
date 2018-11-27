package application;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws IOException {
		/*PRODU��O ========================================================================*/
		FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaLogin.fxml"));
		
		Pane root = loader.load();
		TelaLoginController controller = (TelaLoginController)loader.getController();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Entrar");
		
		primaryStage.show();
		primaryStage.setResizable(false);
		
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icone.png")));
		
		
		///////////////////////////
		//APAGAR SEM D� PARA VOLTAR PRODU��O
		//////////////////////////
		/*FXMLLoader loader = new FXMLLoader(getClass().getResource("TelaPrincipal.fxml"));
		
		Pane root = loader.load();
		TelaPrincipalController controller = (TelaPrincipalController)loader.getController();
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		//primaryStage.setTitle("Entrar");
		
		primaryStage.show();
		primaryStage.setResizable(false);
		
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icone.png")));*/
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
