package CadastrarGrupo;

import acchpoo.Banco;
import acchpoo.Conexao;
import acchpoo.Iniciar;
import acchpoo.Mensagem;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.GrupoDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CadastrarGrupoController implements Initializable {
    @FXML
    private Button btn_c;

    @FXML
    private Button btn_v;

    @FXML
    private Button btn_s;

    @FXML
    private TextField txt_g;

    @FXML
    private Label txt_r;

    private static GrupoDAO dao = new GrupoDAO();
    
    @FXML
    void btn_c(ActionEvent event) throws SQLException {
        Grupo g = new Grupo();
        g.setIdGrupo(0);
        g.setDescricao(txt_g.getText());
        
        this.dao.salvar(g);
        
        Mensagem.abrir("Sucesso!", "Cadastro de grupo", "Grupo "+ g.getDescricao() +" salvo com sucesso!", AlertType.INFORMATION);
        
        this.txt_g.clear();
    }
    
    @FXML
    void btn_v(ActionEvent event) {
    	Iniciar.subStage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
