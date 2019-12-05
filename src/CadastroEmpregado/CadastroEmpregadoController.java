package CadastroEmpregado;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import CadastroCliente.Cliente;
import CadastroUsuario.Usuario;
import DAO.EmpregadoDAO;
import DAO.UsuarioDAO;
import acchpoo.Iniciar;
import acchpoo.Mensagem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CadastroEmpregadoController implements Initializable {
	
		private int idEmpregado = 0;
		
	    @FXML
	    private ImageView imgEmpregado;
	
	  	@FXML
	    private Button btnIncluirNovo;

	    @FXML
	    private TextField txtCpf;

	    @FXML
	    private TextField txtNumero;

	    @FXML
	    private TableColumn<EmpregadoDTO, String> tcAtivo;

	    @FXML
	    private TextField txtRUa;

	    @FXML
	    private Button btnEditar;

	    @FXML
	    private CheckBox cbxAtivo;

	    @FXML
	    private TableView<EmpregadoDTO> tvEmpregado;

	    @FXML
	    private Button btnSalvar;

	    @FXML
	    private TableColumn<EmpregadoDTO, String> tcNomeCompleto;

	    @FXML
	    private Button btnVoltar;

	    @FXML
	    private TextField txtCidade;
	    

	    @FXML
	    private TextField txtTelefone;

	    @FXML
	    private TableColumn<EmpregadoDTO, Integer> tcCodigo;

	    @FXML
	    private TableColumn<EmpregadoDTO, String> tcCpf;

	    @FXML
	    private TextField txtNomeCompleto;

	    @FXML
	    private TextField txtBairro;
	    
	    @FXML
	    private ComboBox<Usuario> cboUsuario;
	    
	    private static EmpregadoDAO dao = new EmpregadoDAO();
	    private static UsuarioDAO usarioDao = new UsuarioDAO();

	    @FXML
		private void voltar() throws IOException {
			Iniciar.subStage.close();
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

		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			tcCodigo.setCellValueFactory(
	                new PropertyValueFactory<>("idEmpregado"));
	        tcNomeCompleto.setCellValueFactory(
	                new PropertyValueFactory<>("nomeCompleto"));
	        tcCpf.setCellValueFactory(
	                new PropertyValueFactory<>("cpf"));
	        tcAtivo.setCellValueFactory(
	                new PropertyValueFactory<>("ativo"));
	        
	        Image img =  new Image(getClass().getResourceAsStream("/img/empregado.png"));
	        
	        this.imgEmpregado = new ImageView(img);
	        this.carregarUsuarios();
			this.cboUsuario.getSelectionModel().selectFirst();
			this.atualizarListaEmpregados();
		}

		private void carregarUsuarios() {
			try {
				this.cboUsuario.setItems(FXCollections.observableArrayList(this.usarioDao.get()));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		private void atualizarListaEmpregados() {
			try {
				tvEmpregado.setItems(getEmpregados());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private ObservableList<EmpregadoDTO> getEmpregados() throws SQLException {
			return this.dao.get();
		}
		
		private void incluirNovo() {
			this.idEmpregado = 0;
			this.txtNomeCompleto.clear();
			this.txtTelefone.clear();
			this.txtCpf.clear();
			this.txtRUa.clear();
			this.txtNumero.clear();
			this.txtBairro.clear();
			this.txtCidade.clear();
			this.cbxAtivo.setSelected(true);
			this.cboUsuario.getSelectionModel().selectFirst();
		}
		
		private void salvar() {
			if(!validar()) {
				Mensagem.abrir("Atenção!", "Cadastro de empregado", "Existem dados essenciais não informados!", Alert.AlertType.WARNING);
				return;
			}
			
			Empregado empregado = new Empregado();
			
			empregado.setIdEmpregado(this.idEmpregado);
			empregado.setNomeCompleto(this.txtNomeCompleto.getText());
			empregado.setTelefone(this.txtTelefone.getText());
			empregado.setCpf(this.txtCpf.getText());
			empregado.setRua(this.txtRUa.getText());
			empregado.setNumero(this.txtNumero.getText());
			empregado.setBairro(this.txtBairro.getText());
			empregado.setCidade(this.txtCidade.getText());
			empregado.setAtivo(this.cbxAtivo.isSelected());
			empregado.setIdUsuario(this.cboUsuario.getSelectionModel().getSelectedItem().getIdUsuario());
			
			this.dao.salvar(empregado);
			
			Mensagem.abrir("Sucesso!", "Cadastro de empregado", "Empregado "+empregado.getNomeCompleto() +" salvo com sucesso!", Alert.AlertType.INFORMATION);
						
			this.incluirNovo();
			
			this.atualizarListaEmpregados();
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

		private void editar() throws SQLException {
			this.incluirNovo();
			EmpregadoDTO edit = tvEmpregado.getSelectionModel().getSelectedItem();
			
			if(edit == null) {
				return;
			}
			
			Empregado empregado = this.dao.getById(edit.getIdEmpregado());
			
			this.idEmpregado = empregado.getIdEmpregado();
			this.txtNomeCompleto.setText(empregado.getNomeCompleto());
			this.txtTelefone.setText(empregado.getTelefone());
			this.txtCpf.setText(empregado.getCpf());
			this.txtRUa.setText(empregado.getRua());
			this.txtNumero.setText(empregado.getNumero());
			this.txtBairro.setText(empregado.getBairro());
			this.txtCidade.setText(empregado.getCidade());
			this.cboUsuario.getSelectionModel().select(this.usarioDao.get(edit.getIdUsuario()));
			this.cbxAtivo.setSelected(empregado.isAtivo());
		}
}
