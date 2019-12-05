package CadastrarSubGrupo;

import acchpoo.Banco;
import acchpoo.Conexao;
import acchpoo.Iniciar;
import acchpoo.Mensagem;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import CadastrarGrupo.Grupo;
import DAO.GrupoDAO;
import DAO.SubgrupoDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastrarSubGrupoController implements Initializable {

    @FXML
    private TextField txt_d;

    @FXML
    private Button btn_c;

    @FXML
    private Button btn_v;

    @FXML
    private Button btn_s;

    @FXML
    private Label txt_r;
    
    @FXML
    private ComboBox<Grupo> cboGrupo;
    
    private static SubgrupoDAO dao = new SubgrupoDAO();
    
    private static GrupoDAO grupoDao = new GrupoDAO();

    @FXML
    void btn_c(ActionEvent event) throws SQLException {
        try {
        	Subgrupo sg = new Subgrupo();
        	Grupo grupo = this.cboGrupo.getSelectionModel().getSelectedItem();
        	
        	if(grupo == null) {
        		Mensagem.abrir("Atenção", "Cadastro subgrupo", "Nenhum grupo selecionado!", AlertType.WARNING);
        		return;
        	}
        	
        	sg.setIdSubgrupo(0);
            sg.setDescricao(txt_d.getText());
            sg.setIdGrupo(grupo.getIdGrupo());
            
            this.dao.salvar(sg);
            Mensagem.abrir("Sucesso!", "Cadastro subgrupo", "Subgrupo cadastrado com sucesso!", AlertType.INFORMATION);
            
            this.txt_d.clear();
            this.cboGrupo.getSelectionModel().selectFirst();
        } catch (Exception e) {
            Mensagem.abrir("Falha!", "Cadastro subgrupo", "Ops ocorreu um erro ao salvar subgrupo!", AlertType.WARNING);
        }
    }
    @FXML
    void btn_v(ActionEvent event) {
    	Iniciar.subStage.close();
    }
    public void initialize(URL url, ResourceBundle rb) {
    	try {
			this.cboGrupo.setItems(FXCollections.observableArrayList(this.grupoDao.get()));
			this.cboGrupo.getSelectionModel().selectFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
