package CadastroUsuario;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import DAO.UsuarioDAO;
import acchpoo.Iniciar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CadastroUsuarioController implements Initializable {
	private static UsuarioDAO dao = new UsuarioDAO();
	
	@FXML
	private TextField txtUsuario;
	
	@FXML
	private PasswordField txtSenha;
	
	@FXML
	private PasswordField txtConfirmacao;
	
	@FXML
    private ComboBox<String> cbxTipo;

    @FXML
    private Label lblTipo;
	
	@FXML
    void salvar(ActionEvent event) throws SQLException {
        if(validarDados()) {
        	String tipo = "E";
        	
        	if(Iniciar.IdUsuario != 0) {
        		tipo = cbxTipo.getValue().substring(0, 1);
        	}
        	
        	if(this.dao.salvar(txtUsuario.getText(), txtSenha.getText(), tipo) == 0) {
    			JOptionPane.showMessageDialog(null, "Falha ao Salvar!", "Cadastro de usuario!", JOptionPane.ERROR_MESSAGE);
    			return;
        	}
        }
        
    }
	
	@FXML
    void voltar(ActionEvent event) throws IOException {
		if(Iniciar.IdUsuario != 0) {
			Iniciar.subStage.close();
			return;
		}
		
		Iniciar.stage.close();
		Parent root = FXMLLoader.load(getClass().getResource("/Inicial/Inicial.fxml"));
        Scene scene = new Scene(root);

        Iniciar.stage.setScene(scene);
		Iniciar.stage.centerOnScreen();
        Iniciar.stage.show();
    }
	
	private Boolean validarDados() {
		if(txtUsuario.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Usuário não informado!", "Cadastro de usuario!", JOptionPane.WARNING_MESSAGE);
			return false;
        }
		
		if(txtSenha.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Senha não informada!", "Cadastro de usuario!", JOptionPane.WARNING_MESSAGE);
			return false;
        }
		
		if(txtConfirmacao.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "Senha de confirmação não informada!", "Cadastro de usuario!", JOptionPane.WARNING_MESSAGE);
			return false;
        }
		
		if(!txtSenha.getText().equals(txtConfirmacao.getText())) {
			JOptionPane.showMessageDialog(null, "Senhas não coincidem!", "Cadastro de usuario!", JOptionPane.WARNING_MESSAGE);
			return false;
        }
		
		return true;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		ObservableList<String> options = 
			    FXCollections.observableArrayList(
			        "Administrador",
			        "Empregado"
			    );
		this.cbxTipo.setItems(options);
		this.cbxTipo.setValue("Empregado");
		
		if(Iniciar.IdUsuario == 0) {
    		cbxTipo.setVisible(false);
    		lblTipo.setVisible(false);
    	}
	}
}
