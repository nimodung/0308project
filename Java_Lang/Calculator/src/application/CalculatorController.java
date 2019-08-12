package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class CalculatorController implements Initializable {

	@FXML private Button num_0, num_1, num_2, num_3, num_4,
						num_5, num_6, num_7, num_8, num_9;
	@FXML private Button btn_clear, btn_equal, btn_div, 
						btn_mul, btn_sub, btn_add;
	@FXML private Label lb_result;
	
	private double number1 = 0; //ù��° �Է¹޴� ����
	private String operator = ""; //������
	private boolean first_input_flag = true; //���ڸ� ó�� �Է��ϴ����� ���� flag
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	
		
	}
	
	@FXML
	public void process_numbers(ActionEvent event) {
		if(first_input_flag) { //ó�� ���ڸ� �Է��ϸ� label clear ���ֱ� //0�� �Ⱥپ� ������
			lb_result.setText("");
			first_input_flag = false;
		}
		String value = ((Button)event.getSource()).getText();
		lb_result.setText(lb_result.getText() + value);
	}
	
	
	@FXML
	public void process_operators(ActionEvent event) {
		
		String value = ((Button)event.getSource()).getText();
		if(value.equals("C")) {
			number1 = 0;
			
			first_input_flag = true;
			operator = "";
			lb_result.setText("0");
		}
		else {
			if(operator.equals("=") || operator.equals(""))
				number1 = Double.parseDouble(lb_result.getText());
			else 
				number1 = calculator(number1, Double.parseDouble(lb_result.getText()));
			
			lb_result.setText(String.valueOf(number1));
			operator = value;
			first_input_flag = true;
		}
		
	}
	
	public double calculator(double number1, double number2) {
		
		switch (operator) {
		case "+":
			return number1 + number2;
		case "-":
			return number1 - number2;
		case "*":
			return number1 * number2;
		case "/":
			return number1 / number2;

		default:
			return 0;
		}
		
	}

}
