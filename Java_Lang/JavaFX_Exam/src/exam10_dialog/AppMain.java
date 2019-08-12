package exam10_dialog;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("root.fxml"));
		Parent root = loader.load();
		
		RootController controller = loader.getController(); //root.fxml에 적용된 컨트롤러 객체를 넣어주는 것
		
		
		
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		//primaryStage.setResizable(false);
	}

	public static void main(String[] args) {
		launch(args);

	}
}
