package Encomenda;

import acchpoo.Banco;
import acchpoo.Conexao;
import acchpoo.Encomenda;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class EncomendaController implements Initializable {

    @FXML
    private Button btn_fc;

    @FXML
    private TextField txt_n;

    @FXML
    private TextField txt_t;

    @FXML
    private Label res;

    @FXML
    private Button brn_v;

    @FXML
    private Button btn_s;

    @FXML
    private TextField txt_q;

    @FXML
    private TextField txt_nc;

    @FXML
    private Label txt_r;

    @FXML
    private TextField txt_d;

    @FXML
    void fButtonAction(ActionEvent event) throws SQLException {
        Conexao c = new Conexao();
        Banco b = new Banco(c);
        Encomenda ec = new Encomenda();
        try {
            ec.setDescricao(txt_n.getText());
            ec.setTipo(txt_t.getText());
            ec.setQuantidade(Integer.parseInt(txt_q.getText()));
            ec.setData(Integer.parseInt(txt_d.getText()));
            ec.setNomeCliente(txt_nc.getText());
            b.inserirEncomenda(ec);
            txt_r.setText("Agendado com sucesso!");
        } catch (Exception e) {
            txt_r.setText("Dados inválidos!");
        }

    }

    @FXML
    void sButtonAction(ActionEvent event) {
        Stage stage = (Stage) btn_s.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
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
