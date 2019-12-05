package Encomenda;

import acchpoo.Iniciar;
import acchpoo.Mensagem;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ResourceBundle;

import CadMercadorias.Mercadoria;
import CadastroCliente.Cliente;
import DAO.ClienteDAO;
import DAO.EncomendaDAO;
import DAO.MercadoriaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class EncomendaController implements Initializable {

	@FXML
    private Button btnSalvar;

    @FXML
    private TextField txtMercadoria;

    @FXML
    private DatePicker txtData;

    @FXML
    private Button btnPesquisarMercadoria;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private Button btnVoltar;

    @FXML
    private Button btnPesquisarCliente;

    @FXML
    private TextField txtCliente;

    private static EncomendaDAO dao = new EncomendaDAO();
    private static MercadoriaDAO mercadoriaDao = new MercadoriaDAO();
    private static ClienteDAO clienteDao = new ClienteDAO();
    private Mercadoria mercadoria = new Mercadoria();
    private Cliente cliente = new Cliente();

    @FXML
    void salvarAction(ActionEvent event) throws SQLException {
        try {
        	Encomenda encomenda = new Encomenda();
        	
        	if(this.mercadoria == null) {
            	Mensagem.abrir("Falha!", "Lançamento de Encomenda", "Mercadoria não informada corretamente!", AlertType.ERROR);
        		return;
        	}
        	
        	if(this.cliente == null) {
            	Mensagem.abrir("Falha!", "Lançamento de Encomenda", "Cliente não informado corretamente!", AlertType.ERROR);
        		return;
        	}
        	
        	Double quantidade = 0.00;
    		
    		try {
    			quantidade = Double.parseDouble(this.txtQuantidade.getText());
    		} catch (Exception e) {
    			Mensagem.abrir("Atenção", "Quantidade", "Quantidade não esta em formato valido, considere utilizar ponto no lugar de virgula!", AlertType.WARNING);
    			return;
    		}
    		
        	encomenda.setData(this.txtData.getValue());
        	encomenda.setFinalizada(false);
        	encomenda.setIdCliente(this.cliente.getIdCliente());
        	encomenda.setIdMercadoria(this.mercadoria.getIdMercadoria());
        	encomenda.setQuantidade(quantidade);
        	
        	this.dao.salvar(encomenda);
        	
        	this.limpar();
        	
        	Mensagem.abrir("Sucesso!", "Lançamento de Encomenda", "Encomenda salva com sucesso!", AlertType.INFORMATION);
        } catch (Exception e) {
        	Mensagem.abrir("Falha!", "Lançamento de Encomenda", "Ops, ocorreu uma falha ao salvar encomenda", AlertType.ERROR);
        }

    }
    
    private void limpar() {
    	this.mercadoria = null;
		this.cliente = null;
		this.txtCliente.clear();
		this.txtMercadoria.clear();
		this.txtQuantidade.clear();
    	this.txtData.setValue(LocalDate.now());
		
	}

	@FXML
    void pesquisarMercadoriaAction(ActionEvent event) throws IOException, SQLException {
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
		
		this.txtMercadoria.setText(this.mercadoria.getDescricao());
		this.txtQuantidade.setText("1.00");
    }
    
    @FXML
    void pesquisarClienteAction(ActionEvent event) throws IOException, SQLException {
    	Iniciar.clienteStage.close();
		Iniciar.clienteStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/PesquisaCliente/PesquisaCliente.fxml").openStream());

		Iniciar.clienteStage.setScene(new Scene(root));
		Iniciar.clienteStage.showAndWait();
		
		this.cliente = this.clienteDao.get(Iniciar.idCliente);
		Iniciar.idCliente = 0;
		
		if(this.cliente == null) {
			return;
		}
		
		this.txtCliente.setText(this.cliente.getNomeCompleto());
    }

    @FXML
    void voltarAction(ActionEvent event) {
    	Iniciar.subStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {    	
    	this.txtData.setValue(LocalDate.now());
    }

}
