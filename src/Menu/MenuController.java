package Menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuController implements Initializable {
	
	@FXML
	private MenuBar menuBar;
	
	@FXML
	private MenuItem CadastroMercadoria;
	
	@FXML
	private Menu mnuSair;
	
	@FXML
    void sairAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) menuBar.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		CadastroMercadoria.setVisible(false);
	}

}
