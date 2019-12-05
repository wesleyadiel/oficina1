package PesquisaCliente;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.ClienteDAO;
import acchpoo.Iniciar;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PesquisaClienteController implements Initializable {
	

    @FXML
    private TableColumn<ClientePesquisaDTO, String> tcNomeCompleto;

    @FXML
    private TextField txtPesquisa;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnSelecionar;

    @FXML
    private TableColumn<ClientePesquisaDTO, Integer> tcCodigo;

    @FXML
    private TableColumn<ClientePesquisaDTO, String> tcCpf;

    @FXML
    private Button btnPesquisa;

    @FXML
    private TableView<ClientePesquisaDTO> tvCodigo;
    
    private ClienteDAO dao = new ClienteDAO();
    
    @FXML
    private void pesquisarAction() throws SQLException {
    	this.tvCodigo.setItems(FXCollections.observableArrayList(this.dao.get(this.txtPesquisa.getText())));
    }
    
    @FXML
    private void selecionarAction() throws SQLException {
    	ClientePesquisaDTO dto = this.tvCodigo.getSelectionModel().getSelectedItem();
    	
    	if(dto == null) {
    		return;
    	}
    	
    	Iniciar.idCliente = dto.getIdCliente();
    	Iniciar.clienteStage.close();
    }
    
    @FXML
    private void voltarAction() throws SQLException {
    	Iniciar.idCliente = 0;
    	Iniciar.clienteStage.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
    	this.tcCodigo.setCellValueFactory(
                new PropertyValueFactory<>("idCliente"));
    	this.tcNomeCompleto.setCellValueFactory(
                new PropertyValueFactory<>("nomeCompleto"));
        this.tcCpf.setCellValueFactory(
                new PropertyValueFactory<>("cpf"));
	}

}
