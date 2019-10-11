package GerarRelatorioEstoque;

import Sacar.SacarController;
import TelaLogin.TelaLoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GerarRelatorioEstoqueController implements Initializable {

    @FXML
    private Button btn_v;

    @FXML
    private Button btn_s;

    @FXML
    private Label lb_num;

    @FXML
    private Label lb_s;

    @FXML
    private Label lb_s1;

    @FXML
    private Label lb_s2;
    
    @FXML
    void sButton(ActionEvent event) {
        Stage stage = (Stage) btn_s.getScene().getWindow(); //Obtendo a janela atual
        stage.close();
    }

    @FXML
    void vButton(ActionEvent event) {
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
        //classe que vai passar as infos que irei pegar a baixo
        SacarController sa = new SacarController();
        lb_num.setText(String.valueOf(Integer.valueOf(nomedoObjetoQueIraPegarEssaInfo.getEstoque().getQuantidadeEmEstoque())));
        lb_s1.setText(String.valueOf(Double.valueOf(nomedoObjetoQueIraPegarEssaInfo.getEstoque().getQuantidadeTotal())));
        lb_s.setText(String.valueOf(Double.valueOf(nomedoObjetoQueIraPegarEssaInfo.getMateriaPrima().getMateriaPrimaGasta())));
        lb_s2.setText(String.valueOf(Double.valueOf(nomedoObjetoQueIraPegarEssaInfo.getMateriaPrima().getPrimaRestante())));
    }

}
