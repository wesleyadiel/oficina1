package CadMercadorias;

import acchpoo.Banco;
import acchpoo.Conexao;
import acchpoo.Iniciar;
import acchpoo.Mercadoria;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CadMercadoriaController implements Initializable {

    @FXML
    private Button btn_fc;

    @FXML
    private TextField txt_t;

    @FXML
    private Button btn_v;

    @FXML
    private Button btn_s;

    @FXML
    private TextField txt_m;

    @FXML
    private TextField txt_d;

    @FXML
    private TextField txt_q;

    @FXML
    private Button btn_fa;

    @FXML
    private TextField txt_g;

    @FXML
    private Label res;


    @FXML
    void fButtonAction(ActionEvent event) throws SQLException {
        Conexao c = new Conexao();
        Banco b = new Banco(c);
        Mercadoria m = new Mercadoria();
        try {
            m.setTipo(txt_t.getText());
            m.setMarca(txt_m.getText());
            m.setDescricao(txt_d.getText());
            m.setQuantidade(Integer.parseInt(txt_q.getText()));
            m.setGrupo(txt_g.getText());
            b.inserirMercadoria(m);
            res.setText("Cadastrado com sucesso!");
        } catch (Exception e) {
            res.setText("Dados inválidos!");
        }
    }

    @FXML
    void faButtonAction(ActionEvent event) throws SQLException {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            String nome;

            Pane root = fxmlLoader.load(getClass().getResource(nome = "/Agendamento/Agendamento.fxml").openStream());
            stage.setScene(new Scene(root, 659, 224));

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void sButtonAction(ActionEvent event) {
        Iniciar.subStage.close();
    }

    @FXML
    void vButtonAction(ActionEvent event) {
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
