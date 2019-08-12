package smarthome;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class RootContorller implements Initializable {

	@FXML Label lb_temper, lb_wetness; //온도, 습도
	@FXML Button btn_room_led_on, btn_room_led_off; //방 led on, off
	@FXML Button btn_kitchen_led_on, btn_kitchen_led_off; //주방 led on, off
	@FXML Button btn_door_led_on, btn_door_led_off, btn_door_led_auto; // 현관 led on, off, auto
	@FXML Button btn_living_led_on, btn_living_led_off; //거실 led on, off
	@FXML Button btn_bathroom_led_on, btn_bathroom_led_off, btn_bathroom_led_auto; //화장실 led on, off, auto
	@FXML Button btn_whole_led_on, btn_whole_led_off; //전체 led on, off
	

	static InputStream in;
	static OutputStream out;
	static SerialPort serialPort;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		try {
			new RootContorller().connect("COM7");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		btn_room_led_on.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				try {
					out.write("room_led_on\n".getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});

	}
	
	private void connect(String portName) throws Exception{
		
		System.out.printf("Port : %s\n", portName);
		
		
		CommPortIdentifier portIdentifier = 
					CommPortIdentifier.getPortIdentifier(portName);
		
		if(portIdentifier.isCurrentlyOwned()) { //currentlyowned : 누가 쓰고있냐
			System.out.println("Error: Port is currently in use");
		}
		else {
			CommPort commPort = 
					portIdentifier.open(this.getClass().getName(), 2000);
			
			if(commPort instanceof SerialPort) {
				serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams( //시리얼 포트 설정
						9600, //통신 속도
						SerialPort.DATABITS_8, //몇비트 통신인가, 
						SerialPort.STOPBITS_1,  
						SerialPort.PARITY_NONE);
				
				//in, out thread를 각각 만들어주는
				//송 수신 thread를 따로 
				//os는 delay를 써도 ㄱㅊ 
				//=> 멀티 thread이기 때문에 delay로 한 쓰레드가 멈춰도 다른 thread들은 동작하기 때문에 문제가 없음
				//in, out thread를 사용하면 main과 상관없는(interrupt 처럼)쓸 수 있음
				in = serialPort.getInputStream();
				
				out = serialPort.getOutputStream();
				
				(new Thread(new SerialReader(in))).start();
				(new Thread(new SerialWriter(out))).start();
			}
		}
	}

	//컴퓨터 입장에서의 수신
	public static class SerialReader implements Runnable {
		//수신 받은 data를 화면에 출력해주는 
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
					//this.in.read(buffer) : in을 읽어서 buffer에 저장하고 buffer의 길이 return 
					// 저장할게 없으면 return -1
					System.out.print(new String(buffer, 0, len));
				}
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			
		}
	}
	
	//컴퓨터 입장에서의 송신
	public static class SerialWriter implements Runnable{
		OutputStream out;
		
		public SerialWriter(OutputStream out) {
			this.out = out;
		}

		@Override
		public void run() {
			try {
				int c = 0;
				System.out.println("\n Keyboard Input Read!!!");//안내 문구 출력
				while((c = System.in.read()) > -1) {
					//System.in.read() : 키보드 입력 , 더이상 읽을 data가 없으면 return -1
					this.out.write(c); 
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
		

}
