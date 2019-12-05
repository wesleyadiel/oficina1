package PrevisaoDeCompras;

import acchpoo.Conexao;
import acchpoo.Iniciar;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import DAO.VendaDAO;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrevisaoComprasController implements Initializable {

	@FXML
    private TableColumn<PrevisaoCompraRelatorioDTO, Double> tcQtdEstoque;

    @FXML
    private Button btnVoltar;

    @FXML
    private TableColumn<PrevisaoCompraRelatorioDTO, Integer> tcCodigo;

    @FXML
    private TableColumn<PrevisaoCompraRelatorioDTO, String> tcDescricao;

    @FXML
    private TableColumn<PrevisaoCompraRelatorioDTO, Double> tcQtdCompra;

    @FXML
    private TableView<PrevisaoCompraRelatorioDTO> tvCompra;
    
    private static VendaDAO dao = new VendaDAO();
    
    @FXML
    void voltarAction(ActionEvent event) {
        Iniciar.subStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
		tcCodigo.setCellValueFactory(
                new PropertyValueFactory<>("idMercadoria"));
        tcDescricao.setCellValueFactory(
                new PropertyValueFactory<>("descricao"));
        tcQtdEstoque.setCellValueFactory(
                new PropertyValueFactory<>("quantidadeEstoque"));
        tcQtdCompra.setCellValueFactory(
                new PropertyValueFactory<>("quantidadeCompra"));
        
    	try {
			this.tvCompra.setItems(FXCollections.observableArrayList(this.dao.buscarPrevisaoCompraRelatorio()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
