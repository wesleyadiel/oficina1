package CadastrarSubGrupo;

import acchpoo.Banco;
import acchpoo.Conexao;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CadastrarSubGrupoController implements Initializable {

    @FXML
    private TextField txt_d;

    @FXML
    private TextField txt_g;

    @FXML
    private Button btn_c;

    @FXML
    private Button btn_v;

    @FXML
    private Button btn_s;

    @FXML
    private Label txt_r;

    @FXML
    void btn_c(ActionEvent event) throws SQLException {
         Conexao c = new Conexao();
            Banco b = new Banco(c);
            SubGrupo sg = new SubGrupo();
        try {
            sg.setDesricao(txt_d.getText());
            sg.setGrupo(txt_g.getText());
            b.inserirSubGrupo(sg);
            txt_r.setText("Cadastrado com sucesso!");
        } catch (Exception e) {
            txt_r.setText("Dados inválidos!");
        }
    }

    @FXML
    void btn_s(ActionEvent event) {
 Stage stage = (Stage) btn_s.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }

    @FXML
    void btn_v(ActionEvent event) {
 try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            String nome;

            Pane root = fxmlLoader.load(getClass().getResource(nome = "/Inicial/Inicial.fxml").openStream());
            stage.setScene(new Scene(root, 659, 224));

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initialize(URL url, ResourceBundle rb) {

    }

}
