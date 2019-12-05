/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GerarRelatorioEstoqueMercadoria;

import acchpoo.Conexao;
import acchpoo.Iniciar;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.MercadoriaEstoqueDAO;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author free
 */
public class GerarRelatorioEstoqueMercadoriaController implements Initializable {
	@FXML
	private TableView<MercadoriaEstoqueRelatorioDTO> tvMercadoriaEstoque;
	
	@FXML
	private Button btnVoltar;
	
	@FXML
	private TableColumn<MercadoriaEstoqueRelatorioDTO, Double> tcQuantidade;
	
	@FXML
	private TableColumn<MercadoriaEstoqueRelatorioDTO, Integer> tcCodigo;
	
	@FXML
	private TableColumn<MercadoriaEstoqueRelatorioDTO, String> tcDescricao;
	
    private static MercadoriaEstoqueDAO dao = new MercadoriaEstoqueDAO();
    
    private void seletList() throws SQLException {
        this.tvMercadoriaEstoque.setItems(FXCollections.observableArrayList(this.dao.buscarRelatorioEstoqueMercadoria()));
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
        this.tcQuantidade.setCellValueFactory(
                new PropertyValueFactory<>("quantidade"));
        try {
			seletList();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
