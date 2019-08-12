package exam01_pane;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class RootGridpaneController implements Initializable {

	@FXML private Button btn_login;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btn_login.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.out.println("Login");
				
			}
		});
		
	}
	
	

}
