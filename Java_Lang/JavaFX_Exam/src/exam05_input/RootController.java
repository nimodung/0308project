package exam05_input;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RootController implements Initializable {

	@FXML private ComboBox<String> combobox;
	@FXML private TextField tf_title;
	@FXML private PasswordField pf_password;
	@FXML private DatePicker dp_finish;
	@FXML private TextArea ta_review;
	@FXML private Button btn_ok, btn_cancle;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		
	}

	public void handleBtnOkAction(ActionEvent event) {
		String title = tf_title.getText();
		System.out.println("���� : " + title);
		
		String password = pf_password.getText();
		System.out.println("��й�ȣ : " + password);
		
		String strPublic = combobox.getValue();
		System.out.println("���� ���� : " + strPublic);
		
		LocalDate localDate = dp_finish.getValue();
		if(localDate != null) {
			System.out.println("dateFinish : " + localDate.toString());
		}
		
		String review = ta_review.getText();
		System.out.println("���� : " + review);
	}
	
	public void handleBtnCancleAction(ActionEvent event) {
		Platform.exit();
	}
}
