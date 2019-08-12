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

	@FXML Label lb_temper, lb_wetness; //�µ�, ����
	@FXML Button btn_room_led_on, btn_room_led_off; //�� led on, off
	@FXML Button btn_kitchen_led_on, btn_kitchen_led_off; //�ֹ� led on, off
	@FXML Button btn_door_led_on, btn_door_led_off, btn_door_led_auto; // ���� led on, off, auto
	@FXML Button btn_living_led_on, btn_living_led_off; //�Ž� led on, off
	@FXML Button btn_bathroom_led_on, btn_bathroom_led_off, btn_bathroom_led_auto; //ȭ��� led on, off, auto
	@FXML Button btn_whole_led_on, btn_whole_led_off; //��ü led on, off
	

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
		
		if(portIdentifier.isCurrentlyOwned()) { //currentlyowned : ���� �����ֳ�
			System.out.println("Error: Port is currently in use");
		}
		else {
			CommPort commPort = 
					portIdentifier.open(this.getClass().getName(), 2000);
			
			if(commPort instanceof SerialPort) {
				serialPort = (SerialPort) commPort;
				serialPort.setSerialPortParams( //�ø��� ��Ʈ ����
						9600, //��� �ӵ�
						SerialPort.DATABITS_8, //���Ʈ ����ΰ�, 
						SerialPort.STOPBITS_1,  
						SerialPort.PARITY_NONE);
				
				//in, out thread�� ���� ������ִ�
				//�� ���� thread�� ���� 
				//os�� delay�� �ᵵ ���� 
				//=> ��Ƽ thread�̱� ������ delay�� �� �����尡 ���絵 �ٸ� thread���� �����ϱ� ������ ������ ����
				//in, out thread�� ����ϸ� main�� �������(interrupt ó��)�� �� ����
				in = serialPort.getInputStream();
				
				out = serialPort.getOutputStream();
				
				(new Thread(new SerialReader(in))).start();
				(new Thread(new SerialWriter(out))).start();
			}
		}
	}

	//��ǻ�� ���忡���� ����
	public static class SerialReader implements Runnable {
		//���� ���� data�� ȭ�鿡 ������ִ� 
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
					//this.in.read(buffer) : in�� �о buffer�� �����ϰ� buffer�� ���� return 
					// �����Ұ� ������ return -1
					System.out.print(new String(buffer, 0, len));
				}
			} catch (IOException e) {
		
				e.printStackTrace();
			}
			
		}
	}
	
	//��ǻ�� ���忡���� �۽�
	public static class SerialWriter implements Runnable{
		OutputStream out;
		
		public SerialWriter(OutputStream out) {
			this.out = out;
		}

		@Override
		public void run() {
			try {
				int c = 0;
				System.out.println("\n Keyboard Input Read!!!");//�ȳ� ���� ���
				while((c = System.in.read()) > -1) {
					//System.in.read() : Ű���� �Է� , ���̻� ���� data�� ������ return -1
					this.out.write(c); 
				}
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
		

}
