package acchpoo;

import java.sql.ResultSet;
import java.sql.SQLException;

import CadastroCliente.ClienteDTO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Iniciar extends Application {

	public static Stage stage = new Stage();
	public static Stage subStage = new Stage();
	public static Stage clienteStage = new Stage();
	public static int idCliente = 0;
	public static Stage mercadoriaStage = new Stage();
	public static int idMercadoriaSelecionada = 0;
	public static int IdUsuario = 0;
	
    @SuppressWarnings("static-access")
	@Override
    public void start(Stage stage) throws Exception {
    	validarUsuarioAdmin();
    
        Parent root = FXMLLoader.load(getClass().getResource("/Inicial/Inicial.fxml"));
        Scene scene = new Scene(root, 663, 275);

        this.stage.setScene(scene);
        this.stage.show();
    }

    private void validarUsuarioAdmin() throws SQLException {
		Conexao connection = new Conexao();
		ObservableList<ClienteDTO> clientes =  FXCollections.observableArrayList();
		
		ResultSet rs = connection.executeBusca("SELECT id FROM usuario WHERE usuario = 'ADMIN'");
		
        if (!rs.next()) {
    		connection = new Conexao();
        	connection.executeSQL("INSERT INTO usuario(usuario, senha, tipo) VALUES ('ADMIN', 'ADMIN', 'A')");
        }
	}

	public static void main(String[] args) {
        launch(args);
    }

}
