package CadastroUsuario;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import DAO.UsuarioDAO;
import acchpoo.Iniciar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class CadastroUsuarioController {
	private static UsuarioDAO dao = new UsuarioDAO();
	
	@FXML
	private TextField txtUsuario;
	
	@FXML
	private PasswordField txtSenha;
	
	@FXML
	private PasswordField txtConfirmacao;
	
	@FXML
    void salvar(ActionEvent event) throws SQLException {
        if(validarDados()) {
        	if(this.dao.salvar(txtUsuario.getText(), txtSenha.getText(), "E") == 0) {
    			JOptionPane.showMessageDialog(null, "Falha ao Salvar!", "Cadastro de usuario!", JOptionPane.ERROR_MESSAGE);
    			return;
        	}
        }
        
    }
	
	@FXML
    void voltar(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/Inicial/Inicial.fxml"));
        Scene scene = new Scene(root, 663, 275);

        Iniciar.stage.setScene(scene);
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
}
