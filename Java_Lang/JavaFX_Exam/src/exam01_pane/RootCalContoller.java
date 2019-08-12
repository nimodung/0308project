package exam01_pane;


import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.*;

public class RootCalContoller implements Initializable {

	@FXML private ImageView img0;
	@FXML private ImageView img1; 
	@FXML private ImageView img2; 
	@FXML private ImageView img3; 
	@FXML private ImageView img4; 
	@FXML private ImageView img5; 
	@FXML private ImageView img6; 
	@FXML private ImageView img7; 
	@FXML private ImageView img8; 
	@FXML private ImageView img9; 
	@FXML private ImageView img_add;
	@FXML private ImageView img_sub;
	@FXML private ImageView img_mul;
	@FXML private ImageView img_div;
	@FXML private ImageView img_equal;
	@FXML private ImageView img_clear; 
	@FXML private Label resultlabel;
	
	private int number1 = 0, result = 0, number2 = 0;
	private String str = "";
	private String opcode = "";
	private boolean first_input_flag = true;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
	}
	
	public void handleNumberBtnAction(MouseEvent event) {
		String imgID = ((ImageView)event.getSource()).getId();
		if(first_input_flag) {
			resultlabel.setText("");
			first_input_flag = false;
		}
		
		if(imgID.equals("img0")) {
			//resultlabel.setText(resultlabel.getText() + "0");
			str += "0";
		}
		else if(imgID.equals("img1")) {
			//resultlabel.setText(resultlabel.getText() + "1");
			str += "1";
		}
		else if(imgID.equals("img2")) {
			//resultlabel.setText(resultlabel.getText() + "2");
			str += "2";
		}
		else if(imgID.equals("img3")) {
			//resultlabel.setText(resultlabel.getText() + "3");
			str += "3";
		}
		else if(imgID.equals("img4")) {
			//resultlabel.setText(resultlabel.getText() + "4");
			str += "4";
		}
		else if(imgID.equals("img5")) {
			//resultlabel.setText(resultlabel.getText() + "5");
			str += "5";
		}
		else if(imgID.equals("img6")) {
			//resultlabel.setText(resultlabel.getText() + "6");
			str += "6";
		}
		else if(imgID.equals("img7")) {
			//resultlabel.setText(resultlabel.getText() + "7");
			str += "7";
		}
		else if(imgID.equals("img8")) {
			//resultlabel.setText(resultlabel.getText() + "8");
			str += "8";
		}
		else if(imgID.equals("img9")) {
			//resultlabel.setText(resultlabel.getText() + "9");
			str += "9";
		}
		resultlabel.setText(str);
	}

	public void handleOpcodeBtnAction(MouseEvent event) {
		String imgOP = ((ImageView)event.getSource()).getId();
		if(imgOP.equals("img_clear")) {
			number1 = 0; number2 = 0;
			opcode = "";
			str = "";
			first_input_flag = true;
			resultlabel.setText("");
		}
		else if(imgOP.equals("img_equal")) {
				String[] number = str.split(" +  |  - | * | /");
				number1 = Integer.parseInt(number[0]);
				number2 = Integer.parseInt(number[2]);
				
				result = calculator(number1, number2);
				resultlabel.setText(String.valueOf(result));
				opcode = "";
				str = String.valueOf(result);
				first_input_flag = true;
		}
		else {
			if(opcode.equals("")) {
				opcode = imgOP;
				if(opcode.equals("img_add")) str += " + ";
				else if(opcode.equals("img_sub")) str += " - ";
				else if(opcode.equals("img_mul")) str += " * ";
				else if(opcode.equals("img_div")) str += " / ";
				
				resultlabel.setText(str);
			}
			else {	
				number1 = calculator(number1, number2);
				str = String.valueOf(number1);
				resultlabel.setText(str);
				opcode = "";
				first_input_flag = true;
			}
		}
	}
	
	public int calculator(int number1, int number2) {
		if(opcode.equals("img_add")) 
			return number1 + number2;
		else if(opcode.equals("img_sub"))
			return number1 - number2;
		else if(opcode.equals("img_mul"))
			return number1 * number2;
		else if(opcode.equals("img_div"))
			return number1 / number2;
		else return 0;
	}
}
