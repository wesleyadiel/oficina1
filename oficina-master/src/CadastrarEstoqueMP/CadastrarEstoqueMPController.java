package CadastrarEstoqueMP;

import ControlarEstoqueMP.*;
import acchpoo.Banco;
import acchpoo.Conexao;
import acchpoo.Estoque;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


    public class CadastrarEstoqueMPController implements Initializable {

        @FXML
        private Button btn_l;

        @FXML
        private Button btn_s;

        @FXML
        private Button btn_v;

        @FXML
        private TextField txt_d;

        @FXML
        private TextField txt_q;

        @FXML
        private Label res;

        @FXML
        private TextField txt_ta;

        @FXML
        private TextField txt_tp;

        @FXML
        private TextField txt_c;

        void lButtonAction(ActionEvent event) {
            Conexao c = new Conexao();
            Banco b = new Banco(c);
            Estoque es = new Estoque();
            try {
                es.setCor(txt_c.getText());
                es.setDescricao(txt_d.getText());
                es.setQuantidade(Integer.parseInt(txt_q.getText()));
                es.setTamanho(Double.parseDouble(txt_ta.getText()));
                es.setTipo(txt_tp.getText());
                b.cadastrarEstoqueMP(es);
                res.setText("Estoque cadastrado com sucesso!");
            } catch (Exception e) {
                res.setText("Dados inválidos!");
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
