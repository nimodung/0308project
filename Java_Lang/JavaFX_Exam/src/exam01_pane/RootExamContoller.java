package exam01_pane;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class RootExamContoller implements Initializable {

	@FXML private Button btn_ty;
	@FXML private Button btn_irin;
	@FXML private Button btn_sola;
	@FXML private Button btn_iu;
	@FXML private ImageView imgview_ty;
	@FXML private ImageView imgview_irin;
	@FXML private ImageView imgview_sola;
	@FXML private ImageView imgview_iu;
	
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
		btn_ty.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				handlebtnAction(event);
				//imgview_ty.setVisible(true);
				//imgview_irin.setVisible(false);
				//imgview_sola.setVisible(false);
				//imgview_iu.setVisible(false);
				
			}
		});
		btn_irin.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				handlebtnAction(event);
				//imgview_irin.setVisible(true);
				//imgview_sola.setVisible(false);
				//imgview_iu.setVisible(false);
				//imgview_ty.setVisible(false);
				
			}
		});
		btn_sola.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				handlebtnAction(event);
				//imgview_sola.setVisible(true);
				//imgview_iu.setVisible(false);
				//imgview_ty.setVisible(false);
				//imgview_irin.setVisible(false);
			}
		});
		btn_iu.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				handlebtnAction(event);
				//imgview_iu.setVisible(true);
				//imgview_ty.setVisible(false);
				//imgview_irin.setVisible(false);
				//imgview_sola.setVisible(false);
			}
		});

	}
	
	public void handlebtnAction(ActionEvent event) {
		imgview_ty.setVisible(false);
		imgview_irin.setVisible(false);
		imgview_sola.setVisible(false);
		imgview_iu.setVisible(false);
		String btnID = ((Button)event.getSource()).getId();
		if(btnID.equals("btn_ty")) imgview_ty.setVisible(true);
		else if(btnID.equals("btn_irin")) imgview_irin.setVisible(true);
		else if(btnID.equals("btn_sola")) imgview_sola.setVisible(true);
		else if(btnID.equals("btn_iu")) imgview_iu.setVisible(true);
	}

}
