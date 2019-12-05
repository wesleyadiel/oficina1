package RealizarVenda;

import acchpoo.Banco;
import acchpoo.Conexao;
import acchpoo.Iniciar;
import acchpoo.Mensagem;
import acchpoo.Venda;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import CadMercadorias.Mercadoria;
import CadMercadorias.MercadoriaDTO;
import CadastroCliente.Cliente;
import DAO.ClienteDAO;
import DAO.MercadoriaDAO;
import DAO.VendaDAO;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class RealizarVendaController implements Initializable {
	@FXML
    private TextField txtDesconto;

    @FXML
    private TableView<MercadoriaVendaDTO> tvMercadoria;

    @FXML
    private Button btnPesquisarMercadoria;

    @FXML
    private Button btnFinalizar;

    @FXML
    private TextField txtQuantidade;

    @FXML
    private TableColumn<MercadoriaVendaDTO, Double> tcQuantidade;

    @FXML
    private TextField txtCliente;

    @FXML
    private TableColumn<MercadoriaVendaDTO, Double> tcPrecoVenda;

    @FXML
    private TextField txtMercadoria;

    @FXML
    private TextField txtPrecoMercadoria;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<MercadoriaVendaDTO, Integer> tcCodigo;

    @FXML
    private Button btnVenderMercadoria;

    @FXML
    private TextField txtPrecoVenda;

    @FXML
    private TableColumn<MercadoriaVendaDTO, String> tcDescricao;

    @FXML
    private Button btnPesquisarCliente;
    
    private static MercadoriaDAO mercadoriaDao = new MercadoriaDAO();
    private static ClienteDAO clienteDao = new ClienteDAO();
    private static VendaDAO dao = new VendaDAO();
    
    private static Mercadoria mercadoria = new Mercadoria();
    private static Cliente cliente = new Cliente();
    
    @FXML
    void finalizarAction(ActionEvent event) throws SQLException {
    	List<MercadoriaVenda> mercadorias = new ArrayList<MercadoriaVenda>();
    	
    	for (MercadoriaVendaDTO item : this.tvMercadoria.getItems()) {
			MercadoriaVenda merc = new MercadoriaVenda();
			
			merc.setIdCliente(item.getIdCliente());
			merc.setIdMercadoria(item.getIdMercadoria());
			merc.setQuantidade(item.getQuantidade());
			merc.setValorDesconto(item.getValorDesconto());
			merc.setValorMercadoria(item.getValorMercadoria());
			merc.setValorVenda(item.getValorVenda());
			
			mercadorias.add(merc);
		}
    	
    	this.salvar(mercadorias);
    }

    private void salvar(List<MercadoriaVenda> mercadorias) throws SQLException {
		this.dao.salvar(mercadorias);
		this.limpar();
		this.tvMercadoria.getItems().clear();
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
		this.txtPrecoMercadoria.setText(this.mercadoria.getPreco().toString());
		this.txtPrecoVenda.setText(this.mercadoria.getPreco().toString());
		this.txtQuantidade.setText("1.00");
		this.txtDesconto.setText("0.00");
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
    	this.tcCodigo.setCellValueFactory(
                new PropertyValueFactory<>("idMercadoria"));
    	this.tcDescricao.setCellValueFactory(
                new PropertyValueFactory<>("descricao"));
        this.tcPrecoVenda.setCellValueFactory(
                new PropertyValueFactory<>("valorVenda"));
        this.tcQuantidade.setCellValueFactory(
                new PropertyValueFactory<>("quantidade"));
        
    }
    
    @FXML
    public void venderMercadoriaAction() {
    	this.venderMercadoria();
    }

	private void venderMercadoria() {
		MercadoriaVendaDTO dto = new MercadoriaVendaDTO();
		if(this.mercadoria == null) {
			Mensagem.abrir("Falha!", "Venda de mercadoria", "Mercadoria selecionada inválida!", AlertType.ERROR);
			return;
		}
		
		Double quantidade = 0.00;
		Double valorMercadoria = 0.00;
		Double valorVenda = 0.00;
		Double valorDesconto = 0.00;
		
		try {
			quantidade = Double.parseDouble(this.txtQuantidade.getText());
		} catch (Exception e) {
			Mensagem.abrir("Atenção", "Quantidade", "Quantidade não esta em formato valido, considere utilizar ponto no lugar de virgula!", AlertType.WARNING);
			return;
		}
		
		try {
			valorMercadoria = Double.parseDouble(this.txtPrecoMercadoria.getText());
		} catch (Exception e) {
			Mensagem.abrir("Atenção", "Preço mercadoria", "Preço da mercadoria não esta em formato valido, considere utilizar ponto no lugar de virgula!", AlertType.WARNING);
			return;
		}
		
		try {
			valorVenda = Double.parseDouble(this.txtPrecoVenda.getText());
		} catch (Exception e) {
			Mensagem.abrir("Atenção", "Preço venda", "Preço de venda da mercadoria não esta em formato valido, considere utilizar ponto no lugar de virgula!", AlertType.WARNING);
			return;
		}
		
		try {
			valorDesconto = Double.parseDouble(this.txtDesconto.getText());
		} catch (Exception e) {
			Mensagem.abrir("Atenção", "Valor de desconto", "Valor de desconto da mercadoria não esta em formato valido, considere utilizar ponto no lugar de virgula!", AlertType.WARNING);
			return;
		}
				
		dto.setIdCliente(this.cliente == null ? 0 : this.cliente.getIdCliente());
		dto.setDescricao(this.txtMercadoria.getText());
		dto.setIdMercadoria(this.mercadoria.getIdMercadoria());
		dto.setNomeCompleto(this.cliente == null ? "" : this.cliente.getNomeCompleto());
		dto.setQuantidade(quantidade);
		dto.setValorDesconto(valorDesconto);
		dto.setValorMercadoria(valorMercadoria);
		dto.setValorVenda(valorVenda);
		
		this.tvMercadoria.getItems().add(dto);
		
		this.limpar();
	}

	private void limpar() {
		this.mercadoria = null;
		this.cliente = null;
		this.txtCliente.clear();
		this.txtDesconto.clear();
		this.txtMercadoria.clear();
		this.txtPrecoMercadoria.clear();
		this.txtPrecoVenda.clear();
		this.txtQuantidade.clear();
	}

}
