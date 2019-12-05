/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicial;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import acchpoo.Iniciar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class InicialController implements Initializable {

    @FXML
    private Button btn_rC;

    @FXML
    private Label label;

    @FXML
    private Button btn_slcCon;

    @FXML
    private Button btn_rsCCP;

    @FXML
    private Button btn_s;

    @FXML
    private void SlcButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();            
            Pane root = fxmlLoader.load(getClass().getResource("/Login/Login.fxml").openStream());
            
            Iniciar.stage.centerOnScreen();
            Iniciar.stage.setScene(new Scene(root));
            Iniciar.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void rcButtonAction(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();

            Pane root = fxmlLoader.load(getClass().getResource("/CadastroUsuario/CadastroUsuario.fxml").openStream());
            Iniciar.stage.setScene(new Scene(root, 500, 500));
            Iniciar.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void s(ActionEvent event) {
        Iniciar.stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
