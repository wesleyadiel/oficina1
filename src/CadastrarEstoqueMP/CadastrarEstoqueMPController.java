package CadastrarEstoqueMP;

import acchpoo.Iniciar;
import acchpoo.Mensagem;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import CadMercadorias.Mercadoria;
import CadMercadorias.MercadoriaDTO;
import DAO.MateriaPrimaDAO;
import DAO.MercadoriaDAO;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;


    public class CadastrarEstoqueMPController implements Initializable {
    	
    	@FXML
        private TextField txtMercadoria;

        @FXML
        private TableView<MateriaPrimaDTO> tvMateriaPrima;

        @FXML
        private Button btnVoltar;

        @FXML
        private TableColumn<MateriaPrimaDTO, Double> tcQuantidade;

        @FXML
        private TableColumn<MateriaPrimaDTO, Integer> tcCodigo;

        @FXML
        private TableColumn<MateriaPrimaDTO, String> tcDescricao;

        @FXML
        private TextField txtCodigo;

        @FXML
        private Button btnPesquisar;

        @FXML
        private Button btnIncluirMateriaPrima;
        
        private static MateriaPrimaDAO dao = new MateriaPrimaDAO();
        private static MercadoriaDAO mercadoriaDao = new MercadoriaDAO();
        private static Mercadoria mercadoria = new Mercadoria();
        
        @FXML
    	public void pesquisarAction() throws IOException, SQLException {
        	Iniciar.mercadoriaStage.close();
    		Iniciar.mercadoriaStage.centerOnScreen();
    		
    		FXMLLoader fxmlLoader = new FXMLLoader();
    		Pane root = fxmlLoader.load(getClass().getResource("/PesquisaMercadoria/PesquisaMercadoria.fxml").openStream());

    		Iniciar.mercadoriaStage.setScene(new Scene(root));
    		Iniciar.mercadoriaStage.showAndWait();
    		
    		this.mercadoria = this.mercadoriaDao.get(Iniciar.idMercadoriaSelecionada);
    		Iniciar.idMercadoriaSelecionada = 0;
    		
    		if(this.mercadoria == null) {
    			return;
    		}
    		
    		this.txtCodigo.setText(this.mercadoria.getIdMercadoria().toString());
    		this.txtMercadoria.setText(this.mercadoria.getDescricao());
    		
    		this.buscarMateriasPrimas();
    	}
        
    	private void buscarMateriasPrimas() {
    		try {
				this.tvMateriaPrima.setItems(FXCollections.observableArrayList(this.dao.buscarByIdMercadoria(this.mercadoria.getIdMercadoria())));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@FXML
    	public void incluirMateriaPrimaAction() throws IOException, SQLException {
			Iniciar.mercadoriaStage.close();
    		Iniciar.mercadoriaStage.centerOnScreen();
    		
    		FXMLLoader fxmlLoader = new FXMLLoader();
    		Pane root = fxmlLoader.load(getClass().getResource("/PesquisaMercadoria/PesquisaMercadoria.fxml").openStream());

    		Iniciar.mercadoriaStage.setScene(new Scene(root));
    		Iniciar.mercadoriaStage.showAndWait();
    		
    		Mercadoria mp = this.mercadoriaDao.get(Iniciar.idMercadoriaSelecionada);
    		Iniciar.idMercadoriaSelecionada = 0;
    		
    		if(mp.getIdTipo() == 1) {
    			Mensagem.abrir("Atenção", "Cadastro Materia Prima", "Mercadoria selecionado não é do tipo materia prima!", AlertType.WARNING);
    			return;
    		}
    		
    		MateriaPrimaDTO dto = new MateriaPrimaDTO();
    		
    		dto.setCodigo(mp.getIdMercadoria());
    		dto.setIdMercadoria(this.mercadoria.getIdMercadoria());
    		dto.setDescricao(mp.getDescricao());
    		dto.setQuantidade(1.00);
    		
    		tvMateriaPrima.getItems().add(dto);
    	}
    	
    	@FXML
    	public void voltarAction() {
    		Iniciar.subStage.close();
    	}
    	
    	@FXML
    	public void salvarAction() throws SQLException {
    		this.salvar();
    	}
    	
    	@FXML
    	public void limparAction() throws SQLException {
    		this.limpar();
    	}
    	
        private void limpar() {
			this.txtCodigo.clear();
			this.txtMercadoria.clear();
			this.tvMateriaPrima.getItems().clear();
		}

		private void salvar() throws SQLException {
			List<MateriaPrimaDTO> mps = this.tvMateriaPrima.getItems();
			
			for (MateriaPrimaDTO materiaPrimaDTO : mps) {
				MateriaPrima prima = new MateriaPrima();
				prima.setIdMercadoria(materiaPrimaDTO.getIdMercadoria());
				prima.setIdMercadoriaMateriaPrima(materiaPrimaDTO.getCodigo());
				prima.setQuantidade(materiaPrimaDTO.getQuantidade());
				
				this.dao.salvar(prima);
			}
			this.limpar();
			
		}

		@Override
        public void initialize(URL url, ResourceBundle rb) {
			this.tcCodigo.setCellValueFactory(
	                new PropertyValueFactory<>("codigo"));
			this.tcDescricao.setCellValueFactory(
	                new PropertyValueFactory<>("descricao"));
	        this.tcQuantidade.setCellValueFactory(
	                new PropertyValueFactory<>("quantidade"));
	        this.tvMateriaPrima.setEditable(true);
	        this.tcQuantidade.setEditable(true);
        }

    }
