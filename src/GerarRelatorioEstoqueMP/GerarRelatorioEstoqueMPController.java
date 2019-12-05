package GerarRelatorioEstoqueMP;

import GerarRelatorioEstoqueMercadoria.*;
import acchpoo.Conexao;
import acchpoo.Iniciar;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.MateriaPrimaEstoqueDAO;
import javafx.collections.FXCollections;
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

public class GerarRelatorioEstoqueMPController implements Initializable {

	@FXML
    private Button btnVoltar;

    @FXML
    private TableView<MercadoriaEstoqueRelatorioDTO> tvMateriaPrimaEstoque;

    @FXML
    private TableColumn<MercadoriaEstoqueRelatorioDTO, Double> tcQuantidade;

    @FXML
    private TableColumn<MercadoriaEstoqueRelatorioDTO, Double> tcCodigo;

    @FXML
    private TableColumn<MercadoriaEstoqueRelatorioDTO, Double> tcDescricao;

    private static MateriaPrimaEstoqueDAO dao = new MateriaPrimaEstoqueDAO();
    
    private void seletList() throws SQLException {
        this.tvMateriaPrimaEstoque.setItems(FXCollections.observableArrayList(this.dao.buscarRelatorioEstoqueMateriaPrima()));
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
