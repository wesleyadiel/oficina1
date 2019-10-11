package Menu;

import acchpoo.ContaBancaria;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MenuController implements Initializable {

    @FXML
    private Button btn_sc;

    @FXML
    private Button btn_d;

    @FXML
    private Button btn_grc;

    @FXML
    private Button btn_t;

    @FXML
    private Button btn_rmv;

    @FXML
    private Button btn_s;

    @FXML
    private Button btn_v;

    @FXML
    private Button btn_grtc;

    @FXML
    private Button btn_grtp;
    
    @FXML
    void dButtonAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            String nome;

            Pane root = fxmlLoader.load(getClass().getResource(nome = "/Depositar/Depositar.fxml").openStream());
            stage.setScene(new Scene(root, 395, 259));
            stage.showAndWait();
            stage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void grcButtonAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            String nome;

            Pane root = fxmlLoader.load(getClass().getResource(nome = "/GerarRelatorioConta/GerarRelatorioConta.fxml").openStream());
            stage.setScene(new Scene(root, 591, 228));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void grtcButtonAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            String nome;

            Pane root = fxmlLoader.load(getClass().getResource(nome = "/GerarRelatorioContasCorrente/GerarRelatorioContas.fxml").openStream());
            stage.setScene(new Scene(root, 498, 364));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void grtcpButtonAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            String nome;

            Pane root = fxmlLoader.load(getClass().getResource(nome = "/GerarRelatorioContasPoupanca/GerarRelatorioContas.fxml").openStream());
            stage.setScene(new Scene(root, 498, 364));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void rButtonAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            String nome;

            Pane root = fxmlLoader.load(getClass().getResource(nome = "/RemoverConta/RemoverConta.fxml").openStream());
            stage.setScene(new Scene(root, 370, 314));
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
    void saButtonAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            String nome;

            Pane root = fxmlLoader.load(getClass().getResource(nome = "/Sacar/Sacar.fxml").openStream());
            stage.setScene(new Scene(root, 615, 292));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tButtonAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            String nome;

            Pane root = fxmlLoader.load(getClass().getResource(nome = "/Transferencia/Transferencia.fxml").openStream());
            stage.setScene(new Scene(root, 395, 391));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void vButtonAction(ActionEvent event) {
        try {
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader();
            String nome;

            Pane root = fxmlLoader.load(getClass().getResource(nome = "/Inicial/Inicial.fxml").openStream());
            stage.setScene(new Scene(root, 663, 275));
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
