/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GerarRelatorioVendas;

import GerarRelatorioEstoqueMercadoria.*;
import RealizarVenda.MercadoriaVendaDTO;
import acchpoo.Conexao;
import acchpoo.Iniciar;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import CadastroCliente.ClienteDTO;
import DAO.VendaDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author free
 */
public class GerarRelatorioVendasController implements Initializable {

	@FXML
    private TextField txtTotalLiquido;

    @FXML
    private TableColumn<MercadoriaVendaRelatorioDTO, Double> tcPrecoMercadoria;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<MercadoriaVendaRelatorioDTO, Double> tcValorTotal;

    @FXML
    private TableColumn<MercadoriaVendaRelatorioDTO, Double> tcQuantidade;

    @FXML
    private TableView<MercadoriaVendaRelatorioDTO> tvMercadoriaVenda;

    @FXML
    private TableColumn<MercadoriaVendaRelatorioDTO, Integer> tcCodigo;

    @FXML
    private TableColumn<MercadoriaVendaRelatorioDTO, String> tcDescricao;

    @FXML
    private TextField txtTotalDesconto;

    @FXML
    private TableColumn<MercadoriaVendaRelatorioDTO, Double> tcPrecoVenda;

    @FXML
    private TextField txtTotalBruto;
    
    private static VendaDAO vendaDAO = new VendaDAO();

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
        this.tcPrecoMercadoria.setCellValueFactory(
                new PropertyValueFactory<>("valorMercadoria"));
        this.tcValorTotal.setCellValueFactory(
                new PropertyValueFactory<>("valorTotal"));
        
    	try {
    	   buscar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    private void buscar() throws SQLException {
       buscarLista();
       buscarTotais();
    }

	private void buscarTotais() throws SQLException {
		TotaisDTO tot = this.vendaDAO.buscarTotaisRelatorio();
		
		this.txtTotalBruto.setText(tot.getTotalBruto().toString());
		this.txtTotalDesconto.setText(tot.getTotalDesconto().toString());
		this.txtTotalLiquido.setText(tot.getTotalLiquido().toString());
	}

	private void buscarLista() throws SQLException {
		this.tvMercadoriaVenda.setItems(FXCollections.observableArrayList(this.vendaDAO.buscarListaRelatorio()));
	}

}
