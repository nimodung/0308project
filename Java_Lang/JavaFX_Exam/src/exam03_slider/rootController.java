package exam03_slider;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class rootController implements Initializable {

	@FXML Slider sld_number;
	@FXML Label lb_number;
	@FXML ProgressBar pb_number;
	@FXML TextField tf_text;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		//Bindings.bindBidirectional(lb_number.textProperty(), tf_text.textProperty());
		//tf_text.textProperty().bind(lb_number.textProperty());
		pb_number.progressProperty().bind(Bindings.divide(Bindings.add(sld_number.valueProperty(), 30), 60f));
		//�Ʒ����� �ȵȴ�. bind ������ �߿�!
		//sld_number.valueProperty().bind(Bindings.divide(pb_number.progressProperty(), 100f));
	
		sld_number.valueProperty().addListener(new ChangeListener<Number>() {
								//Number : �����ε� int���� ���� ���� �������� 
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Integer value = newValue.intValue();
				//System.out.println(value.toString());
				//int value = newValue.intValue();
				//System.out.println("" + value);
				
				lb_number.setText(value.toString());
				//pb_number.setProgress(value/100f);
			}
		});
	}

}
