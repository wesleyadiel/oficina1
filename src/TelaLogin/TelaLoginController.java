package TelaLogin;

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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaLoginController implements Initializable {

    @FXML
    private Button btn_l;
 
    @FXML
    private TextField txt_n;
         
    @FXML
    private PasswordField txt_s;

    @FXML
    private Button brn_v;

    @FXML
    private Button btn_s;

    @FXML
    private RadioButton r_ue;

    @FXML
    private ToggleGroup MyGroup;

    @FXML
    private RadioButton r_uc;

    @FXML
    private Label res;
    
    @FXML
    void ue(ActionEvent event) {

    }

    @FXML
    void uc(ActionEvent event) {

    }

    private static Usuario usuario;
    private static String tipo;

    public void setUsuario(Usuario usuario, String tipo) {
        this.usuario = usuario;
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTipo() {
        return tipo;
    }

    @FXML
    void lButtonAction(ActionEvent event) throws SQLException, IOException {
        Conexao c = new Conexao();
        Banco b = new Banco(c);

        if (r_ue.isSelected()) {
            try {
                ContaBancaria usuario = new ContaCorrente();
                usuario = b.consultarUsuarioUe(Integer.parseInt(txt_n.getText()), txt_s.getText());
                res.setText("Logado com sucesso!");
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader();
                String nome;
                Pane root = fxmlLoader.load(getClass().getResource(nome = "/Menu/Menu.fxml").openStream());
                stage.setScene(new Scene(root, 370, 424));
                setUsuario(usuario, "Empregado");
                stage.showAndWait();
            } catch (Exception e) {
                res.setText("Conta inexistente!");
            }
        }

        if (r_uc.isSelected()) {
            try {
                ContaBancaria usuario = new ContaCorrente();
                usuario = b.consultarUsuarioUc(Integer.parseInt(txt_n.getText()), txt_s.getText());
                res.setText("Logado com sucesso!");
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader();
                String nome;
                Pane root = fxmlLoader.load(getClass().getResource(nome = "/Menu/Menu.fxml").openStream());
                stage.setScene(new Scene(root, 370, 424));
                setUsuario(usuario, "Cliente");
                stage.showAndWait();
            } catch (Exception e) {
                res.setText("Conta inexistente!");
            }

        }

        if (r_ue.isSelected() == false && r_uc.isSelected() == false) {
            res.setText("Erro! Selecione um tipo de conta!");
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
            stage.setScene(new Scene(root, 663, 275));

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

}
