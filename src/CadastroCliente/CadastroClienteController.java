package CadastroCliente;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import CadastroEmpregado.Empregado;
import CadastroEmpregado.EmpregadoDTO;
import DAO.ClienteDAO;
import DAO.LoginDAO;
import acchpoo.Iniciar;
import acchpoo.Mensagem;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CadastroClienteController implements Initializable{
	private static ClienteDAO dao = new ClienteDAO();
	
	private int idCliente;
	
	@FXML
    private Button btnSalvar;
	
    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnIncluirNovo;

    @FXML
    private TextField txtNomeCompleto;

    @FXML
    private TextField txtNumeroTelefone;

    @FXML
    private TextField txtCpf;

    @FXML
    private TextField txtBairro;

    @FXML
    private TextField txtRUa;

    @FXML
    private TextField txtNumero;

    @FXML
    private TextField txtCidade;
    
    @FXML
    private CheckBox cbxAtivo;
    
    //Componentes tabela
    @FXML
    private TableView<ClienteDTO> tvCliente;
    
    @FXML
    private ImageView imgClient;
    
    @FXML
    private TableColumn<ClienteDTO, Integer> tcCodigo;
    
    @FXML
    private TableColumn<ClienteDTO, String> tcNomeCompleto;
    
    @FXML
    private TableColumn<ClienteDTO, String> tcTelefone;
    
    @FXML
    private TableColumn<ClienteDTO, String> tcCpf;

    @FXML
    private TableColumn<ClienteDTO, String> tcAtivo;
    
    @FXML
	void voltarAction(ActionEvent event) throws IOException {
		this.voltar();
	}
	
	@SuppressWarnings("unused")
	private void voltar() throws IOException {
		Iniciar.subStage.close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tcCodigo.setCellValueFactory(
                new PropertyValueFactory<>("idCliente"));
        tcNomeCompleto.setCellValueFactory(
                new PropertyValueFactory<>("nomeCompleto"));
        tcTelefone.setCellValueFactory(
                new PropertyValueFactory<>("telefone"));
        tcCpf.setCellValueFactory(
                new PropertyValueFactory<>("cpf"));
        tcAtivo.setCellValueFactory(
                new PropertyValueFactory<>("ativo"));

        Image img =  new Image(getClass().getResourceAsStream("/img/client.png"));
        
        this.imgClient = new ImageView(img);
		this.atualizarListaClientes();
	}
	
	@FXML
	void incluirNovoAction(ActionEvent event) throws IOException {
		this.incluirNovo();
	}
	
	@FXML
	void salvarAction(ActionEvent event) throws IOException {
		this.salvar();
	}
	
	@FXML
	void editarAction(ActionEvent event) throws IOException, SQLException {
		this.editar();
	}
	
	private ObservableList<ClienteDTO> getClientes() throws SQLException {
		return this.dao.get();
	}
	
	private void salvar() {
		if(!validar()) {
			Mensagem.abrir("Atenção!", "Cadastro de cliente", "Existem dados essenciais não informados!", Alert.AlertType.WARNING);
			return;
		}
		
		Cliente cliente = new Cliente();
		
		cliente.setIdCliente(this.idCliente);
		cliente.setNomeCompleto(this.txtNomeCompleto.getText());
		cliente.setTelefone(this.txtNumeroTelefone.getText());
		cliente.setCpf(this.txtCpf.getText());
		cliente.setRua(this.txtRUa.getText());
		cliente.setNumero(this.txtNumero.getText());
		cliente.setBairro(this.txtBairro.getText());
		cliente.setCidade(this.txtCidade.getText());
		cliente.setAtivo(this.cbxAtivo.isSelected());
		
		this.dao.salvar(cliente);
		
		Mensagem.abrir("Sucesso!", "Cadastro de cliente", "Cliente "+cliente.getNomeCompleto() +" salvo com sucesso!", Alert.AlertType.INFORMATION);
				
		Iniciar.subStage.requestFocus();
		
		this.incluirNovo();
		
		this.atualizarListaClientes();
	}
	
	private boolean validar() {
		if(this.txtNomeCompleto.getText().isEmpty()) {
			return false;
		}
		
		if(this.txtCpf.getText().isEmpty()) {
			return false;
		}
		
		if(this.txtRUa.getText().isEmpty()) {
			return false;
		}
		
		if(this.txtNumero.getText().isEmpty()) {
			return false;
		}
		
		if(this.txtBairro.getText().isEmpty()) {
			return false;
		}

		if(this.txtCidade.getText().isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	private void incluirNovo() {
		this.idCliente = 0;
		this.txtNomeCompleto.clear();
		this.txtNumeroTelefone.clear();
		this.txtCpf.clear();
		this.txtRUa.clear();
		this.txtNumero.clear();
		this.txtBairro.clear();
		this.txtCidade.clear();
		this.cbxAtivo.setSelected(true);
	}
	
	private void editar() throws SQLException {
		this.incluirNovo();
		ClienteDTO edit = tvCliente.getSelectionModel().getSelectedItem();
		
		if(edit == null) {
			return;
		}
		
		Cliente cliente = this.dao.get(edit.getIdCliente());
		
		this.idCliente = cliente.getIdCliente();
		this.txtNomeCompleto.setText(cliente.getNomeCompleto());
		this.txtNumeroTelefone.setText(cliente.getTelefone());
		this.txtCpf.setText(cliente.getCpf());
		this.txtRUa.setText(cliente.getRua());
		this.txtNumero.setText(cliente.getNumero());
		this.txtBairro.setText(cliente.getBairro());
		this.txtCidade.setText(cliente.getCidade());
		this.cbxAtivo.setSelected(cliente.isAtivo());
	}
	
	private void atualizarListaClientes() {
		try {
			tvCliente.setItems(getClientes());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
