/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RemoverConta;

import acchpoo.Banco;
import acchpoo.Conexao;
import acchpoo.ContaBancaria;
import acchpoo.ContaCorrente;
import acchpoo.ContaPoupanca;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author free
 */
public class RemoverContaController implements Initializable {

    @FXML
    private Button btn_v;

    @FXML
    private Button btn_s;

    @FXML
    private Label lb_s;

    @FXML
    private Button btn_v1;

    @FXML
    private TextField txt_n;
    @FXML
    private RadioButton cc;

    @FXML
    private RadioButton cp;

    @FXML
    void rButton(ActionEvent event) throws SQLException {
        Conexao c = new Conexao();
        Banco b = new Banco(c);
        if (cc.isSelected()) {
            try {
                ContaBancaria cbc = new ContaCorrente();

                cbc = b.consultarContaCC(Integer.valueOf(txt_n.getText()));
                ContaCorrente cc = new ContaCorrente();

                cc.setNumero(cbc.getNumero());
                cc.setSaldo(cbc.getSaldo());

                b.removerConta(cc);

                lb_s.setText("Removida com sucesso!");

            } catch (Exception e) {
                lb_s.setText("Conta inexistente!");
            }
        }
        if (cp.isSelected()) {

            try {
                ContaBancaria cbp = new ContaPoupanca();

                cbp = b.consultarContaCP(Integer.valueOf(txt_n.getText()));

                ContaPoupanca cp = new ContaPoupanca();

                cp.setNumero(cbp.getNumero());
                cp.setSaldo(cbp.getSaldo());

                b.removerConta(cp);

                lb_s.setText("Removida com sucesso!");

            } catch (Exception e) {
                lb_s.setText("Conta inexistente!");
            }
        }

        if (cp.isSelected() == false && cc.isSelected() == false) {
            lb_s.setText("Escolha um tipo de conta!");
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

            Pane root = fxmlLoader.load(getClass().getResource(nome = "/Menu/Menu.fxml").openStream());
            stage.setScene(new Scene(root, 370, 424));

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
