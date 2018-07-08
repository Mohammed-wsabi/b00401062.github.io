package grapher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Grapher extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/Grapher.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add("/Grapher.css");
		stage.setTitle("Grapher");
		stage.setScene(scene);
		stage.setMaximized(true);
		stage.show();
		stage.setResizable(false);
	}
	public static void main(String[] args) {
		launch(args);
	}
}
