package acchpoo;

import javafx.scene.control.Alert;

public class Mensagem {
	public static void abrir(String titulo, String cabecalho, String conteudo, Alert.AlertType tipo) {
        Alert dialogoInfo = new Alert(tipo);
        dialogoInfo.setTitle(titulo);
        dialogoInfo.setHeaderText(cabecalho);
        dialogoInfo.setContentText(conteudo);
        dialogoInfo.showAndWait();
	}
}
