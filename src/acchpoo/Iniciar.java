package acchpoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Iniciar extends Application {

	public static Stage stage = new Stage();
	
    @SuppressWarnings("static-access")
	@Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Inicial/Inicial.fxml"));
        Scene scene = new Scene(root, 663, 275);

        this.stage.setScene(scene);
        this.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
