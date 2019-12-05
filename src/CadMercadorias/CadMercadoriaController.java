package CadMercadorias;

import acchpoo.Iniciar;
import acchpoo.Mensagem;
import acchpoo.Tipo;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import CadastrarGrupo.Grupo;
import CadastrarSubGrupo.SubgrupoDTO;
import DAO.GrupoDAO;
import DAO.MercadoriaDAO;
import DAO.SubgrupoDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadMercadoriaController implements Initializable {
	
	private Integer idMercadoria = 0;
	
    @FXML
    private TextField txt_t;

    @FXML
    private Button btn_s;
    
    @FXML
    private TextField txt_g;

    @FXML
    private Label res;
    
    @FXML
    private ComboBox<Tipo> cboTipo;

    @FXML
    private TableView<MercadoriaDTO> tvMercadoria;

    @FXML
    private TextField txt_m;

    @FXML
    private ComboBox<Grupo> cboGrupo;

    @FXML
    private TextField txtTamanho;

    @FXML
    private Button btn_v;

    @FXML
    private ComboBox<SubgrupoDTO> cboSubgrupo;

    @FXML
    private TableColumn<MercadoriaDTO, String> tcTipo;

    @FXML
    private Button btnEditar;

    @FXML
    private TextField txt_d;

    @FXML
    private Button btn_fa;

    @FXML
    private Button btn_fc;

    @FXML
    private TextField txtCor;

    @FXML
    private TextField txt_q;

    @FXML
    private TableColumn<MercadoriaDTO, Integer> tcCodigo;

    @FXML
    private TableColumn<MercadoriaDTO, String> tcDescricao;

    @FXML
    private TableColumn<MercadoriaDTO, String> tcGrupo;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnIncluirNovo;

    @FXML
    private TextField txtPreco;

    @FXML
    private TableColumn<String, Double> tcPreco;

    private MercadoriaDAO dao = new MercadoriaDAO();

    private GrupoDAO grupoDao = new GrupoDAO();

    private SubgrupoDAO subgrupoDao = new SubgrupoDAO();

    @FXML
    void fButtonAction(ActionEvent event) throws SQLException {
        Mercadoria m = new Mercadoria();
        try {
        	if(!validar()) {
				Mensagem.abrir("Atenção!", "Cadastro de mercadoria", "Existem dados essenciais não informados!", Alert.AlertType.WARNING);
				return;
			}
        	
        	Double preco = 0.00;
        	try {
        		preco = Double.parseDouble(this.txtPreco.getText());
        	}catch (Exception e) {
				System.out.println(e.getMessage());
				Mensagem.abrir("Atenção", "Cadastro de mercadoria", "O valor do preço está em um formato incorreto, por favor considere apenas usar ponto para decimais!", AlertType.WARNING);
				return;
			}
        	
        	m.setIdMercadoria(this.idMercadoria);
            m.setDescricao(txt_d.getText());
            m.setMarca(txt_m.getText());
            m.setCor(this.txtCor.getText());
            m.setTamanho(this.txtTamanho.getText());
            m.setPreco(preco);
            m.setIdTipo(this.cboTipo.getSelectionModel().getSelectedItem().getIdTipo());
            m.setIdGrupo(this.cboGrupo.getSelectionModel().getSelectedItem().getIdGrupo());
            m.setIdSubgrupo(this.cboSubgrupo.getSelectionModel().getSelectedItem().getIdSubgrupo());
            
            this.dao.salvar(m);
            
            this.incluirNovo();
            
			Mensagem.abrir("Sucesso!", "Cadastro de mercadoria", "Mercadoria salva com sucesso!", AlertType.WARNING);
			
			this.atualizarListaMercadoria();
        } catch (Exception e) {
        	System.out.println(e.getMessage());
			Mensagem.abrir("Atenção", "Cadastro de mercadoria", "Ops ocorreu um erro ao salvar a mercadoria!", AlertType.WARNING);
        }
    }
    
    private boolean validar() {
		if(this.txt_d.getText().isEmpty()) {
			return false;
		}
		
		if(this.txt_m.getText().isEmpty()) {
			return false;
		}
		
		if(this.txtPreco.getText().isEmpty()) {
			return false;
		}
		
		return true;
	}

    private void atualizarListaMercadoria() {
		try {
			tvMercadoria.setItems(FXCollections.observableArrayList(this.dao.getDTO()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@FXML
    void faButtonAction(ActionEvent event) throws SQLException {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            String nome;

            Pane root = fxmlLoader.load(getClass().getResource(nome = "/Encomenda/Encomenda.fxml").openStream());
            stage.setScene(new Scene(root, 659, 224));

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void vButtonAction(ActionEvent event) {
        Iniciar.subStage.close();
    }
    
    @FXML
    void incluirNovoAction(ActionEvent event) {
        this.incluirNovo();
    }

    private void incluirNovo() {
		this.idMercadoria = 0;
		this.txt_d.clear();
		this.txt_m.clear();
		this.txt_q.clear();
		this.txtCor.clear();
		this.txtPreco.clear();
		this.txtTamanho.clear();
		this.cboGrupo.getSelectionModel().selectFirst();
		this.cboSubgrupo.getSelectionModel().selectFirst();
		this.cboTipo.getSelectionModel().selectFirst();
	}
    
    @FXML
    void editarAction(ActionEvent event) throws SQLException {
        this.editar();
    }

	private void editar() throws SQLException {
		MercadoriaDTO edit = this.tvMercadoria.getSelectionModel().getSelectedItem();
		
		if(edit == null) {
			return;
		}
		
		this.idMercadoria = edit.getIdMercadoria();
		this.txt_d.setText(edit.getDescricao());
		this.txt_m.setText(edit.getMarca());
		this.txt_q.setText(edit.getQuantidade().toString());
		this.txtCor.setText(edit.getCor());
		this.txtPreco.setText(edit.getPreco().toString());
		this.txtTamanho.setText(edit.getTamanho());
		
		this.cboGrupo.getSelectionModel().select(this.grupoDao.get(edit.getIdGrupo()));
		this.cboSubgrupo.getSelectionModel().select(this.subgrupoDao.getDTO(edit.getIdSubgrupo()));
		
		Tipo tipo = new Tipo();
		
		tipo.setIdTipo(0);
		tipo.setDescricao("Nenhum");
		
		if(edit.getIdTipo() > 0) {
			tipo.setIdTipo(edit.getIdTipo());
			tipo.setDescricao(edit.getIdTipo() == 1 ? "Mercadoria" : "Materia Prima");
		}
		
		this.cboTipo.getSelectionModel().select(tipo);
		
	}

	 @FXML
	 void exluirAction(ActionEvent event) {
		 MercadoriaDTO exclud = this.tvMercadoria.getSelectionModel().getSelectedItem();
	 
		 if(exclud == null) {
			 return;
		 }
		 
		 this.dao.excluir(exclud.getIdMercadoria());
		 
		 this.atualizarListaMercadoria();
	 }
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		tcCodigo.setCellValueFactory(
                new PropertyValueFactory<>("idMercadoria"));
        tcDescricao.setCellValueFactory(
                new PropertyValueFactory<>("descricao"));
        tcGrupo.setCellValueFactory(
                new PropertyValueFactory<>("grupo"));
        tcPreco.setCellValueFactory(
                new PropertyValueFactory<>("preco"));
        tcTipo.setCellValueFactory(
                new PropertyValueFactory<>("tipo"));
        List<Tipo> tipos = new ArrayList<Tipo>();
        
        Tipo tipo = new Tipo();
        tipo.setIdTipo(0);
        tipo.setDescricao("Nenhum");
        tipos.add(tipo);
        
        tipo = new Tipo();
        tipo.setIdTipo(1);
        tipo.setDescricao("Mercadoria");
        tipos.add(tipo);
        
        tipo = new Tipo();
        tipo.setIdTipo(2);
        tipo.setDescricao("Materia Prima");
        tipos.add(tipo);
        
        this.cboTipo.setItems(FXCollections.observableArrayList(tipos));
        this.cboTipo.getSelectionModel().selectFirst();
        
        this.carregarGrupos();
        this.cboGrupo.getSelectionModel().selectFirst();
        this.carregarSubgrupos();
        this.cboSubgrupo.getSelectionModel().selectFirst();
        
		this.atualizarListaMercadoria();
    }

	private void carregarSubgrupos() {	
		try {
			this.cboSubgrupo.setItems(FXCollections.observableArrayList(this.subgrupoDao.getDTO()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void carregarGrupos() {
        try {
			this.cboGrupo.setItems(FXCollections.observableArrayList(this.grupoDao.get()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
