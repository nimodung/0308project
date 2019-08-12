package exam02_serial;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class appMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Root2.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("LED Controller");
		primaryStage.setResizable(false); //������ ������ ������ �Ӽ� false
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
