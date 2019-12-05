/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GerarRelatorioEstoqueMercadoria;

import acchpoo.Conexao;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author free
 */
public class GerarRelatorioEstoqueMercadoriaController implements Initializable {

    @FXML
    private Button btn_v;

    @FXML
    private Button btn_s;

    @FXML
    private Label lb_s;

    @FXML
    private ListView<String> lvw;

    private void seletList() {
        Conexao con = new Conexao();
        ResultSet rs = con.ListarEstoqueMercadoria();
        try {
            while (rs.next()) {
                String produtos = rs.getString("Nomedocampoprodutosemestoque");
                int quantidade = rs.getInt("quantidade");
                lvw.getItems().add(produtos + "  --  " + quantidade);

            }
        } catch (Exception e) {
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

            Pane root = fxmlLoader.load(getClass().getResource(nome = "/Menu/Menu.fxml").openStream());
            stage.setScene(new Scene(root, 370, 424));

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        seletList();
    }

}
