package GerarRelatorioEstoqueMP;

import GerarRelatorioEstoqueMercadoria.*;
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

public class GerarRelatorioEstoqueMPController implements Initializable {

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
        ResultSet rs = con.ListarEstoqueMP();
        try {
            while (rs.next()) {
                int gasta = rs.getInt("quantidadeGasta");
                int restante = rs.getInt("quantidadeRestante");
                lvw.getItems().add(gasta + "  --  " + restante);

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
