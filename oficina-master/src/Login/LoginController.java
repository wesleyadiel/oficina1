package Login;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import DAO.LoginDAO;
import acchpoo.Iniciar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginController implements Initializable {

	private static LoginDAO dao = new LoginDAO();

	@FXML
	private TextField txtUsuario;

	@FXML
	private PasswordField txtSenha;

	@FXML
	private Button btnLogar;

	@FXML
	private Button btnVoltar;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("static-access")
	@FXML
	void logarAction(ActionEvent event) throws IOException, SQLException {
		if (this.dao.validarLogin(txtUsuario.getText(), txtSenha.getText()) == 0) {
			JOptionPane.showMessageDialog(null, "Usuário/Senha inválidos!", "Login!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			FXMLLoader fxmlLoader = new FXMLLoader();
			
			Iniciar.stage.centerOnScreen();
			Iniciar.stage.setFullScreen(true);

			Pane root = fxmlLoader.load(getClass().getResource("/Menu/Menu.fxml").openStream());

			Iniciar.stage.setScene(new Scene(root));
			Iniciar.stage.show();
		}
	}

	@FXML
	void voltarAction(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();

		Pane root = fxmlLoader.load(getClass().getResource("/Inicial/Inicial.fxml").openStream());

		Iniciar.stage.setScene(new Scene(root));
		Iniciar.stage.show();
	}

}
