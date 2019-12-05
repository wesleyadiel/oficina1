package Menu;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import CadastroUsuario.Usuario;
import DAO.UsuarioDAO;
import acchpoo.Iniciar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController implements Initializable {
	
	private static UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	@FXML
	private MenuBar menuBar;
	
	@FXML
	private Menu mnuSair;
	
	@FXML
	private MenuItem CadastroMercadoria;
	
	@FXML
	private MenuItem CadastroUsuario;
	
	@FXML
	private MenuItem CadastroGrupo;
	
	@FXML
	private MenuItem CadastroSubGrupo;
	
	@FXML
	private MenuItem CadastroCliente;

	@FXML
	private MenuItem CadastroEmpregado;

	@FXML
	private MenuItem CadastroEstoqueMateriaPrima;

	@FXML
	private MenuItem RelatorioEstoqueMercadoria;

	@FXML
	private MenuItem RelatorioEstoqueMateriaPrima;

	@FXML
	private MenuItem RelatorioVenda;

	@FXML
	private MenuItem RelatorioPrevisaoCompra;

	@FXML
	private MenuItem EstoqueMateriaPrima;

	@FXML
	private MenuItem EstoqueMercadoria;

	@FXML
	private MenuItem Encomenda;

	@FXML
	private MenuItem Venda;
	
	
	@FXML
    void sairAction(ActionEvent event) throws IOException {
        Iniciar.subStage.close();
        Iniciar.stage.close();
    }
	
	@FXML
    void abrirCadastroMercadoriaAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/CadMercadorias/CadMercadorias.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirCadastroClienteAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/CadastroCliente/CadastroCliente.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirCadastroEmpregadoAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/CadastroEmpregado/CadastroEmpregado.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.showAndWait();
    }
	
	@FXML
    void abrirCadastroUsuarioAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/CadastroUsuario/CadastroUsuario.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirCadastroGrupoAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/CadastrarGrupo/CadastrarGrupo.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirCadastroSubGrupoAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/CadastrarSubGrupo/CadastrarSubGrupo.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirCadastroEstoqueMateriaPrimaAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/CadastrarEstoqueMP/CadastrarEstoqueMP.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirVendaAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/RealizarVenda/RealizarVenda.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirEstoqueMercadoriaAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/ControlarEstoqueMercadoria/ControlarEstoqueMercadoria.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirEstoqueMateriaPrimaAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/ControlarEstoqueMP/ControlarEstoqueMP.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirEncomandaAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/Encomenda/Encomenda.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirRelatorioVendaAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/GerarRelatorioVendas/GerarRelatorioVendas.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirRelatorioEstoqueMercadoriaAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/GerarRelatorioEstoqueMercadoria/GerarRelatorioEstoqueMercadoria.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirRelatorioEstoqueMateriaPrimaAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/GerarRelatorioEstoqueMP/GerarRelatorioEstoqueMP.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	
	@FXML
    void abrirRelatorioPrevisaoCompraAction(ActionEvent event) throws IOException {
		Iniciar.subStage.close();
		Iniciar.subStage.centerOnScreen();
		
		FXMLLoader fxmlLoader = new FXMLLoader();
		Pane root = fxmlLoader.load(getClass().getResource("/PrevisaoDeCompras/PrevisaoCompras.fxml").openStream());

		Iniciar.subStage.setScene(new Scene(root));
		Iniciar.subStage.show();
    }
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Usuario usu = new Usuario();
		
		try {
			usu = this.usuarioDAO.get(Iniciar.IdUsuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!"A".equals(usu.getTipo()) || usu.getTipo().isEmpty()) {
			this.CadastroCliente.setVisible(false);
			this.CadastroGrupo.setVisible(false);
			this.CadastroMercadoria.setVisible(false);
			this.CadastroSubGrupo.setVisible(false);
			this.CadastroUsuario.setVisible(false);
			this.CadastroEstoqueMateriaPrima.setVisible(false);
			this.CadastroEmpregado.setVisible(false);
		}
		
	}

}
