package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginController {

	@FXML

	private TextField emailUser;

	@FXML
	private TextField passWordUser;

	public Stage primaryStage;

	public void entrar() {
		System.out.println("Login realizado com sucesso ;D");
		if (emailUser.getText().equals("admin") && passWordUser.getText().equals("admin")) {
			System.out.println("Realizando login");
			try {

				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/dashBoard.fxml"));

				StackPane root = loader.load();
				

				Scene scene = new Scene(root,650,400);

				Stage currentStage = (Stage)passWordUser.getScene().getWindow();
				currentStage.setScene(scene);
				currentStage.setTitle("DashBoard");
				currentStage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			mensagemDeErro();
		}
	}

	public void mensagemDeErro() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Sua Senha esta Errada");
		alert.setContentText("Senha incorreta");
		alert.setHeaderText(null);
		alert.showAndWait();
	}

}
