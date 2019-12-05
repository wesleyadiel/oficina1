package ControlarEstoqueMercadoria;

import acchpoo.Banco;
import acchpoo.Conexao;
import acchpoo.Iniciar;
import acchpoo.Mensagem;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import CadMercadorias.Mercadoria;
import CadastroCliente.Cliente;
import DAO.ClienteDAO;
import DAO.MercadoriaDAO;
import DAO.MercadoriaEstoqueDAO;
import DAO.VendaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ControlarEstoqueMercadoriaController implements Initializable {
	
	private static MercadoriaEstoqueDAO dao = new MercadoriaEstoqueDAO();

    @FXML
    private Button btn_l;

    @FXML
    private Button btn_s;

    @FXML
    private Button btn_v;

    @FXML
    private TextField txt_d;

    @FXML
    private TextField txt_q;

    @FXML
    private Label res;

    @FXML
    private Button btnPesquisar;
    
    private static MercadoriaDAO mercadoriaDao = new MercadoriaDAO();
    
    private static Mercadoria mercadoria = new Mercadoria();

    @FXML
    void lButtonAction(ActionEvent event) {
        try {
        	if(this.mercadoria == null) {
        		Mensagem.abrir("Atenção", "Lançamento de estoque materia prima", "Mercadoria não esta correta!", AlertType.WARNING);
        		return;
        	}
        	
        	Double quantidade = 0.00;
        	
        	try {
				quantidade = Double.parseDouble(txt_q.getText());
			} catch (Exception e) {
        		Mensagem.abrir("Atenção", "Lançamento de estoque materia prima", "Quantidade não esta em formato valido, considere utilizar ponto no lugar de virgula!", AlertType.WARNING);
			}
        	
        	Mercadoria mercadoria = new MercadoriaDAO().get(this.mercadoria.getIdMercadoria());
        	this.dao.salvar(mercadoria, quantidade);
        	
        	this.mercadoria = null;
        	this.txt_q.clear();
        	this.txt_d.clear();
        	Mensagem.abrir("Sucesso!", "Lançamento de estoque materia prima", "Lançado com sucesso!", AlertType.INFORMATION);
        } catch (Exception e) {
        	Mensagem.abrir("Atenção", "Lançamento de estoque materia prima", "Ops, erro ao lançar estoque da mercadoria materia prima!", AlertType.WARNING);
        }
    }

    @FXML
    void vButtonAction(ActionEvent event) {
        Iniciar.subStage.close();
    }

    @FXML
    public void pesquisarMercadoria() throws IOException, SQLException {
    	Iniciar.mercadoriaStage.close();
		Iniciar.mercadoriaStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/PesquisaMercadoria/PesquisaMercadoria.fxml").openStream());

		Iniciar.mercadoriaStage.setScene(new Scene(root));
		Iniciar.mercadoriaStage.showAndWait();

    	this.mercadoria = null;
    	this.txt_q.clear();
    	this.txt_d.clear();
    	
		this.mercadoria = this.mercadoriaDao.get(Iniciar.idMercadoriaSelecionada);
		Iniciar.idMercadoriaSelecionada = 0;
		
		if(this.mercadoria == null) {
			return;
		}
		
		if(this.mercadoria.getIdTipo() == 2) {
			Mensagem.abrir("Atenção", "Lançamento de estoque", "Mercadoria do tipo materia prima, não pode ser lançado estoque de mercadoria!", AlertType.WARNING);
			return;
		}
		
		this.txt_d.setText(this.mercadoria.getDescricao());
		txt_q.setText("1.00");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
