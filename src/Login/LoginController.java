package Login;

import java.awt.HeadlessException;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
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
		this.btnLogar.setOnKeyPressed(ke -> {
		    if(new KeyCodeCombination(KeyCode.ENTER).match(ke)) { 
		    	try {
					this.logar();
				} catch (HeadlessException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
	    	}
	    });
		
		this.btnVoltar.setOnKeyPressed(ke -> {
		    if(new KeyCodeCombination(KeyCode.ENTER).match(ke)) { 
		    	try {
						this.voltar();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
		    	}
	    });
	}

	@SuppressWarnings("static-access")
	@FXML
	void logarAction(ActionEvent event) throws IOException, SQLException {
		this.logar();
	}
	
	@SuppressWarnings("static-access")
	private void logar() throws HeadlessException, SQLException, IOException {
		if (this.dao.validarLogin(txtUsuario.getText(), txtSenha.getText()) == 0) {
			JOptionPane.showMessageDialog(null, "Usuário/Senha inválidos!", "Login!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			Iniciar.stage.close();
			Iniciar.stage.centerOnScreen();
			Iniciar.stage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
			Iniciar.stage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());

			FXMLLoader fxmlLoader = new FXMLLoader();
			Pane root = fxmlLoader.load(getClass().getResource("/Menu/Menu.fxml").openStream());

			Iniciar.stage.setScene(new Scene(root));
			Iniciar.stage.show();
		}
	}

	@FXML
	void voltarAction(ActionEvent event) throws IOException {
		this.voltar();
	}
	
	@SuppressWarnings("unused")
	private void voltar() throws IOException {
		Iniciar.stage.close();
		FXMLLoader fxmlLoader = new FXMLLoader();
       
		Pane root = fxmlLoader.load(getClass().getResource("/Inicial/Inicial.fxml").openStream());

		Iniciar.stage.setScene(new Scene(root));
		Iniciar.stage.show();
	}
}
