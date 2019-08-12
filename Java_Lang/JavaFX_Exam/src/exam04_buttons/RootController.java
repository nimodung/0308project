package exam04_buttons;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class RootController implements Initializable {

	@FXML private CheckBox chk1, chk2;
	@FXML private ImageView img_chk, img_chart;
	@FXML private ToggleGroup group;
	@FXML private Button btn_finish;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				Image image = new Image(getClass().getResource("image/" 
							+ ((RadioButton)newValue).getText() + ".png").toString());
				img_chart.setImage(image);				
			}
		});
		
		
	}   

	public void handleChkAction(ActionEvent event) {
		if(chk1.isSelected() && chk2.isSelected()) {
			img_chk.setImage(new Image(getClass().getResource("image/geek-glasses-hair.gif").toString()));
		}
		else if(chk1.isSelected()){
			img_chk.setImage(new Image(getClass().getResource("image/geek-glasses.gif").toString()));
		}
		else if(chk2.isSelected()){
			img_chk.setImage(new Image(getClass().getResource("image/geek-hair.gif").toString()));
		}
		else {
			img_chk.setImage(new Image(getClass().getResource("image/geek.gif").toString()));
		}
	}
	
	public void handleBtnFinishAction(ActionEvent event) {
		Platform.exit();
	}
}
