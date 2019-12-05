package RealizarVenda;

import CadMercadorias.*;
import acchpoo.Banco;
import acchpoo.Conexao;
import acchpoo.Mercadoria;
import acchpoo.Venda;
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
import javafx.stage.Stage;

public class RealizarVendaController implements Initializable {

    @FXML
    private Button btn_fc;

    @FXML
    private Button btn_v;

    @FXML
    private Button btn_s;

    @FXML
    private Button btn_fa;

    @FXML
    private Label res;

    @FXML
    private TextField txt_quantidade;

    @FXML
    private TextField txt_desconto;
    @FXML
    private TextField txt_nome;

    @FXML
    private TextField txt_precoMercadoria;

    @FXML
    private TextField txt_precoVenda;

    @FXML
    private TextField txt_mercadoria;

    @FXML
    void fButtonAction(ActionEvent event) throws SQLException {
        Conexao c = new Conexao();
        Banco b = new Banco(c);
        Venda v = new Venda();
        try {
            v.setNomeCliente(txt_nome.getText());
            v.setDescricaoMercadoria(txt_mercadoria.getText());
            v.setQuantidade(Integer.parseInt(txt_quantidade.getText()));
            v.setPrecoVenda(Double.parseDouble(txt_precoVenda.getText()));
            v.setPrecoMercadoria(Double.parseDouble(txt_precoMercadoria.getText()));
            v.setDesconto(Double.parseDouble(txt_desconto.getText()));

            b.inserirVenda(v);
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
        // TODO
    }

}
