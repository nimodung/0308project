package application;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class RootController implements Initializable {

	@FXML Label lb_00, lb_01, lb_02, lb_03, lb_04, lb_05, lb_06, lb_07;
	@FXML Label lb_10, lb_11, lb_12, lb_13, lb_14, lb_15, lb_16, lb_17;
	@FXML Label lb_20, lb_21, lb_22, lb_23, lb_24, lb_25, lb_26, lb_27;
	@FXML Label lb_30, lb_31, lb_32, lb_33, lb_34, lb_35, lb_36, lb_37;
	@FXML Label lb_40, lb_41, lb_42, lb_43, lb_44, lb_45, lb_46, lb_47;
	@FXML Label lb_50, lb_51, lb_52, lb_53, lb_54, lb_55, lb_56, lb_57;
	@FXML Label lb_60, lb_61, lb_62, lb_63, lb_64, lb_65, lb_66, lb_67;
	@FXML Label lb_70, lb_71, lb_72, lb_73, lb_74, lb_75, lb_76, lb_77;
	
	@FXML Button btn_tx_dot, btn_clear, btn_move_left, btn_move_right, 
				btn_move_stop, btn_move_up, btn_move_down;
	
	boolean[][] labelFlag;
	Label[] labelRowArr;
	Label[][] labelArr;
	
	static InputStream in;
	static OutputStream out;
	static SerialPort serialPort;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			new RootController().connect("COM6");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		labelArr = new Label[][] {
			{lb_00, lb_01, lb_02, lb_03, lb_04, lb_05, lb_06, lb_07},
			{lb_10, lb_11, lb_12, lb_13, lb_14, lb_15, lb_16, lb_17},
			{lb_20, lb_21, lb_22, lb_23, lb_24, lb_25, lb_26, lb_27},
			{lb_30, lb_31, lb_32, lb_33, lb_34, lb_35, lb_36, lb_37},
			{lb_40, lb_41, lb_42, lb_43, lb_44, lb_45, lb_46, lb_47},
			{lb_50, lb_51, lb_52, lb_53, lb_54, lb_55, lb_56, lb_57},
			{lb_60, lb_61, lb_62, lb_63, lb_64, lb_65, lb_66, lb_67},
			{lb_70, lb_71, lb_72, lb_73, lb_74, lb_75, lb_76, lb_77}
		};
		
		
		
		labelFlag = new boolean[8][8];
		

		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				labelFlag[i][j] = false;
			}
		}
		
		
	}
	
	public void BtnMoveOnAction(ActionEvent event) {
		try {
			String BtnID = ((Button)event.getSource()).getId();
			
			if(BtnID.equals("btn_move_left")) {
				out.write("moveLeft\n".getBytes());
				
			}
			else if(BtnID.equals("btn_move_right")) {
				out.write("moveRight\n".getBytes());
			}
			
			else if(BtnID.equals("btn_move_stop")) { 
				out.write("moveStop\n".getBytes());
			}
			else if(BtnID.equals("btn_move_up")) { 
				out.write("moveUp\n".getBytes());
			}
			else if(BtnID.equals("btn_move_down")) { 
				out.write("moveDown\n".getBytes());
			}
			 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void btnClearOnAction(ActionEvent event) {
		try {
			
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					labelFlag[i][j] = false;
					labelArr[i][j].setStyle("-fx-background-color: white;");
					
				}
			}
			
			out.write("dotClear \n".getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public void btnTXdotOnAction(ActionEvent event) {
		try {
			
			String strdotrow = "dotTrans ";
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					
					if(labelFlag[i][j]) {
						strdotrow += "0";
					}
					else {
						strdotrow += "1";
					}
				}
				if(!(i==7))strdotrow += "/";
			}
			strdotrow += "\n";
			//System.out.println(strdotrow);
			out.write(strdotrow.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
 
	public void dotlabelOnAction(MouseEvent event) {
		String LabelID = ((Label)event.getSource()).getId();
		
		for(int i = 0; i <8; i++) {
			for(int j = 0; j < 8; j++) {
				if(LabelID.equals(labelArr[i][j].getId())) {
					
					if(!labelFlag[i][j]) {
						labelFlag[i][j] = true;
						((Label)event.getSource()).setStyle("-fx-background-color: red;");
					}
					else {
						labelFlag[i][j] = false;
						((Label)event.getSource()).setStyle("-fx-background-color: white;");
					}
					
				}
			}
		}
	}
	//占시몌옙占쏙옙 占쏙옙占쏙옙占쏙옙 占싹깍옙占쏙옙占쏙옙 占쌉쇽옙 
	private void connect(String portName) throws Exception{
		
		System.out.printf("Port : %s\n", portName);
		
		
		CommPortIdentifier portIdentifier = 
					CommPortIdentifier.getPortIdentifier(portName);
		
		if(portIdentifier.isCurrentlyOwned()) { //currentlyowned : 占쏙옙占쏙옙 占쏙옙占쏙옙占쌍놂옙
			System.out.println("Error: Port is currently in use");
		}
		else {
			CommPort commPort = 
					portIdentifier.open(this.getClass().getName(), 2000);
			
			if(commPort instanceof SerialPort) {
				serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams( //占시몌옙占쏙옙 占쏙옙트 占쏙옙占쏙옙
						9600, //占쏙옙占� 占쌈듸옙
						SerialPort.DATABITS_8, //占쏙옙占싣� 占쏙옙占쏙옙寬占�, 
						SerialPort.STOPBITS_1,  
						SerialPort.PARITY_NONE);
				
				in = serialPort.getInputStream();
				
				out = serialPort.getOutputStream();
				
				(new Thread(new SerialReader(in))).start();
				(new Thread(new SerialWriter(out))).start();
			}
		}
	}

	public static class SerialReader implements Runnable {
		InputStream in;
		
		public SerialReader(InputStream in) {
			this.in = in;
		}

		@Override
		public void run() {
			byte[] buffer = new byte[1024];
			int len = -1;
			
			try {
				while((len = this.in.read(buffer)) > -1) {
					System.out.print(new String(buffer, 0, len));
				}
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			
		}
	}
	
	public static class SerialWriter implements Runnable{
		OutputStream out;
		
		public SerialWriter(OutputStream out) {
			this.out = out;
		}

		@Override
		public void run() {
			try {
				int c = 0;
				System.out.println("\n Keyboard Input Read!!!");//占싫놂옙 占쏙옙占쏙옙 占쏙옙占�
				while((c = System.in.read()) > -1) {
					this.out.write(c);
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
}
