package Menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import acchpoo.Iniciar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController implements Initializable {
	
	@FXML
	private MenuBar menuBar;
	
	@FXML
	private Menu mnuSair;
	
	@FXML
	private MenuItem CadastroMercadoria;
	
	@FXML
	private MenuItem CadastroUsuario;
	
	@FXML
	private MenuItem CadastroGrupo;
	
	@FXML
	private MenuItem CadastroSubGrupo;
	
	@FXML
	private MenuItem CadastroCliente;
	
	
	@FXML
    void sairAction(ActionEvent event) throws IOException {
        Iniciar.subStage.close();
        Iniciar.stage.close();
    }
	
	@FXML
    void abrirCadastroMercadoriaAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/CadMercadorias/CadMercadorias.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirCadastroUsuarioAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/CadastroUsuario/CadastroUsuario.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirCadastroGrupoAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/CadastrarGrupo/CadastrarGrupo.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirCadastroSubGrupoAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/CadastrarSubGrupo/CadastrarSubGrupo.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//CadastroMercadoria.setVisible(false);
	}

}
