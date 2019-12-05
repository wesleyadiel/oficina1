package PesquisaMercadoria;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import CadastrarGrupo.Grupo;
import DAO.MercadoriaDAO;
import acchpoo.Iniciar;
import acchpoo.Mensagem;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;

public class PesquisaMercadoriaController implements Initializable {
	  	@FXML
	    private TableColumn<PesquisaMercadoriaDTO, Double> tcQtdEstoque;

	    @FXML
	    private TableView<PesquisaMercadoriaDTO> tvMercadoria;

	    @FXML
	    private TextField txtPesquisa;

	    @FXML
	    private TableColumn<PesquisaMercadoriaDTO, Integer> tcCodigo;

	    @FXML
	    private TableColumn<PesquisaMercadoriaDTO, String> tcDescricao;

	    @FXML
	    private Button btnPesquisar;

	    @FXML
	    private TableColumn<PesquisaMercadoriaDTO, String> tcPreco;
	    
	    private MercadoriaDAO dao = new MercadoriaDAO();

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			tcCodigo.setCellValueFactory(
	                new PropertyValueFactory<>("idMercadoria"));
	        tcDescricao.setCellValueFactory(
	                new PropertyValueFactory<>("descricao"));
	        tcQtdEstoque.setCellValueFactory(
	                new PropertyValueFactory<>("qtdEstoque"));
	        tcPreco.setCellValueFactory(
	                new PropertyValueFactory<>("preco"));
	        
	        this.listarMercadoria("");
		}
		
		@FXML
		public void pesquisarAction() {
	        this.listarMercadoria(this.txtPesquisa.getText());
		}

		private void listarMercadoria(String pesquisa) {
			try {
				this.tvMercadoria.setItems(FXCollections.observableArrayList(this.dao.pesquisaMercadoria(pesquisa)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		@FXML
	    void selecionar(ActionEvent event) throws SQLException {
			PesquisaMercadoriaDTO p = this.tvMercadoria.getSelectionModel().getSelectedItem();
			
			if(p == null) {
				Mensagem.abrir("Atenção", "Pesquisa de mercadoria", "Nenhuma mercadoria selecionada!", AlertType.WARNING);
				return;
			}
			
			Iniciar.idMercadoriaSelecionada = p.getIdMercadoria();
			Iniciar.mercadoriaStage.close();
	    }
		
		@FXML
	    void voltar(ActionEvent event) throws SQLException {
			Iniciar.idMercadoriaSelecionada = 0;
			Iniciar.mercadoriaStage.close();
	    }
}
