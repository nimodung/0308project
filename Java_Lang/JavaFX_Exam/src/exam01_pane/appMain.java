package exam01_pane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.stage.Stage;

public class appMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Root_calculator.fxml"));
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("appMain");
		primaryStage.setResizable(true); //윈도우 사이즈 재조정 속성 false
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	

}
